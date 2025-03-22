package com.deepacat.WorldshaperCore.common.data.gregtech;


import com.deepacat.WorldshaperCore.WorldshaperCore;
import com.deepacat.WorldshaperCore.common.data.gregtech.machine.multiblock.ParallelMultiblock;
import com.deepacat.WorldshaperCore.common.data.gregtech.machine.singleblock.ULVMinerMachine;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.SimpleGeneratorMachine;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.steam.SimpleSteamMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.client.renderer.machine.MinerRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.SimpleGeneratorMachineRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.WorkableSteamMachineRenderer;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import static com.deepacat.WorldshaperCore.api.registries.WSRegistries.REGISTRATE;
import static com.deepacat.WorldshaperCore.common.data.gregtech.WSRecipeTypes.STEAM_QUARRY_RECIPES;
import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.DUMMY_RECIPES;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.*;


public class WSMachines {

    public static MachineDefinition[] WS_MACERATOR;
    public static Pair<MachineDefinition, MachineDefinition> WS_STEAM_MACERATOR;
    public static MultiblockMachineDefinition parallelwiremill;
    public static MultiblockMachineDefinition STEAM_QUARRY;
    public static MachineDefinition ULV_MINER;
    public static MachineDefinition[] ULV_COAL_BURNER;

    public static void init() {

        // macerator with seperate recipe type so it doesn't have gt's oreproc generation
        WS_MACERATOR = registerTieredMachines("ws_macerator",
                (holder, tier) -> new SimpleTieredMachine(holder, tier, defaultTankSizeFunction), (tier, builder) -> builder
                        .langValue("%s Macerator %s".formatted(VLVH[tier], VLVT[tier]))
                        .editableUI(SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(GTCEu.id("macerator"),
                                GTRecipeTypes.MACERATOR_RECIPES))
                        .rotationState(RotationState.NON_Y_AXIS)
                        .recipeType(GTRecipeTypes.MACERATOR_RECIPES)
                        .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
                        .workableTieredHullRenderer(GTCEu.id("block/machines/macerator"))
                        .tooltips(workableTiered(tier, V[tier], V[tier] * 64,
                                GTRecipeTypes.MACERATOR_RECIPES, defaultTankSizeFunction.apply(tier), true))
                        .register(),
                ELECTRIC_TIERS);

        // same thing as above but steam
        WS_STEAM_MACERATOR = registerSteamMachines(
                "ws_steam_macerator", SimpleSteamMachine::new, (pressure, builder) -> builder
                        .rotationState(RotationState.NON_Y_AXIS)
                        .recipeType(GTRecipeTypes.MACERATOR_RECIPES)
                        .recipeModifier(SimpleSteamMachine::recipeModifier)
                        .renderer(() -> new WorkableSteamMachineRenderer(pressure, GTCEu.id("block/machines/macerator")))
                        .register());


        // test parallel wiremill
        parallelwiremill = REGISTRATE
                .multiblock("idk", holder -> new ParallelMultiblock(holder, 4))
                .rotationState(RotationState.ALL)
                .recipeType(GTRecipeTypes.WIREMILL_RECIPES)
                .alwaysTryModifyRecipe(true)
                .recipeModifiers(WSRecipeModifiers.SETPARALLEL, GTRecipeModifiers.DEFAULT_ENVIRONMENT_REQUIREMENT,
                        GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK_SUBTICK)
                )
                .appearanceBlock(CASING_STEEL_SOLID)
                .pattern(definition -> FactoryBlockPattern.start()
                        .aisle("FF", "FF")
                        .aisle("FF", "FS")
                        .where('S', Predicates.controller(blocks(definition.getBlock())))
                        .where('F', blocks(CASING_TITANIUM_STABLE.get()).or(Predicates.autoAbilities(definition.getRecipeTypes())))
                        .build())
                .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_stable_titanium"),
                        GTCEu.id("block/multiblock/primitive_blast_furnace"))
                .register();


        STEAM_QUARRY = REGISTRATE
                .multiblock("steam_quarry", (holder) -> new SteamParallelMultiblockMachine(holder, 1, new Object[0]) {
                    @Override
                    public void setUpwardsFacing(@NotNull Direction upwardsFacing) {
                        super.setUpwardsFacing(Direction.NORTH);
                    }
                })
                .rotationState(RotationState.NON_Y_AXIS)
                .recipeType(STEAM_QUARRY_RECIPES)
                .alwaysTryModifyRecipe(true)
                .appearanceBlock(CASING_STEEL_SOLID)
                .pattern(definition -> FactoryBlockPattern.start()
                        .aisle("FFF", "FFF", "###", "###", "###")
                        .aisle("FCF", "FCF", "PCP", "PCP", "PXP")
                        .aisle("FFF", "FSF", "###", "###", "###")
                        .where('S', Predicates.controller(blocks(definition.getBlock())))
                        .where('F', blocks(CASING_STEEL_SOLID.get()).setMinGlobalLimited(8)
                                .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1))
                                .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(1))
                                .or(Predicates.abilities(PartAbility.STEAM).setExactLimit(1)))
                        .where('C', blocks(Blocks.CHAIN))
                        .where('P', blocks(CASING_STEEL_PIPE.get()))
                        .where('X', blocks(CASING_STEEL_SOLID.get()))
                        .where('#', Predicates.any())
                        .build())
                .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                        GTCEu.id("block/multiblock/primitive_blast_furnace"))
                .register();


        ULV_MINER = REGISTRATE.machine("ulv_miner",
                        holder -> new ULVMinerMachine(holder, ULV, 60, 16, 0)).tier(ULV)
                .rotationState(RotationState.ALL)
                .langValue("Shoddy Miner")
                .recipeType(DUMMY_RECIPES)
                .editableUI(ULVMinerMachine.EDITABLE_UI_CREATOR.apply(WorldshaperCore.id("ulv_miner"), 4))
                .renderer(() -> new MinerRenderer(ULV, GTCEu.id("block/machines/miner")))
                .tooltipBuilder((stack, tooltip) -> {
                    int maxArea = 33;
                    long energyPerTick = 2;
                    int tickSpeed = 100;
                    tooltip.add(Component.translatable("gtceu.machine.miner.tooltip", maxArea, maxArea));
                    tooltip.add(Component.translatable("gtceu.universal.tooltip.uses_per_tick", energyPerTick)
                            .append(Component.literal(", ").withStyle(ChatFormatting.GRAY))
                            .append(Component.translatable("gtceu.machine.miner.per_block", tickSpeed / 20)));
                    tooltip.add(Component.translatable("gtceu.universal.tooltip.voltage_in",
                            FormattingUtil.formatNumbers(2),
                            VNF[ULV]));
                    tooltip.add(Component.translatable("gtceu.universal.tooltip.energy_storage_capacity",
                            FormattingUtil.formatNumbers(512L)));
                    tooltip.add(
                            Component.translatable("gtceu.universal.tooltip.working_area_max", maxArea, maxArea));
                })
                .register();

        ULV_COAL_BURNER = registerTieredMachines("coal_burner",
                (holder, tier) -> new SimpleGeneratorMachine(holder, tier, 0.0f, defaultTankSizeFunction),
                (tier, builder) -> builder
                        .rotationState(RotationState.ALL)
                        .recipeType(WSRecipeTypes.COAL_BURNER_FUELS)
                        .editableUI(SimpleGeneratorMachine.EDITABLE_UI_CREATOR.apply(GTCEu.id("coal_burner"), WSRecipeTypes.COAL_BURNER_FUELS))
                        .recipeModifier(SimpleGeneratorMachine::recipeModifier, true)
                        .renderer(() -> new SimpleGeneratorMachineRenderer(tier, GTCEu.id("block/generators/" + "coal_burner")))
                        .tooltips(workableTiered(tier, V[tier], V[tier] * 64,
                                WSRecipeTypes.COAL_BURNER_FUELS, defaultTankSizeFunction.apply(tier), true))
                        .register(),
                ULV);
    }
}