package com.deepacat.WorldshaperCore.common.data;


import com.deepacat.WorldshaperCore.WorldshaperCore;
import com.deepacat.WorldshaperCore.common.machine.multiblock.ParallelMultiblock;
import com.deepacat.WorldshaperCore.common.machine.singleblock.ULVMinerMachine;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
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
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import static com.deepacat.WorldshaperCore.api.registries.WSRegistries.REGISTRATE;
import static com.deepacat.WorldshaperCore.common.data.WSRecipeTypes.COAL_BURNER_FUELS;
import static com.deepacat.WorldshaperCore.common.data.WSRecipeTypes.STEAM_QUARRY_RECIPES;
import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.DUMMY_RECIPES;

public class WSMachines {

    public static final MachineDefinition[] WS_MACERATOR = registerTieredMachines("ws_macerator",
            (holder, tier) -> new SimpleTieredMachine(holder, tier, defaultTankSizeFunction), (tier, builder) -> builder
                    .langValue("%s Macerator %s".formatted(VLVH[tier], VLVT[tier]))
                    .editableUI(SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(GTCEu.id("macerator"),
                            GTRecipeTypes.MACERATOR_RECIPES))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(GTRecipeTypes.MACERATOR_RECIPES)
                    .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
                    .workableTieredHullRenderer(GTCEu.id("block/machines/macerator"))
                    .tooltips(workableTiered(tier, GTValues.V[tier], GTValues.V[tier] * 64,
                            GTRecipeTypes.MACERATOR_RECIPES, defaultTankSizeFunction.apply(tier), true))
                    .compassNode("macerator")
                    .register(),
            ELECTRIC_TIERS);

    public static final Pair<MachineDefinition, MachineDefinition> WS_STEAM_MACERATOR = registerSteamMachines(
            "ws_steam_macerator", SimpleSteamMachine::new, (pressure, builder) -> builder
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(GTRecipeTypes.MACERATOR_RECIPES)
                    .recipeModifier(SimpleSteamMachine::recipeModifier)
                    .renderer(() -> new WorkableSteamMachineRenderer(pressure, GTCEu.id("block/machines/macerator")))
                    .register());

    public static final MultiblockMachineDefinition parallelwiremill = REGISTRATE
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

    public static final MultiblockMachineDefinition STEAM_QUARRY = REGISTRATE
            .multiblock("steam_quarry",  (holder) -> new SteamParallelMultiblockMachine(holder, 1, new Object[0]){
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

    public static final MachineDefinition ULVMINER = REGISTRATE.machine("ulv_miner",
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
                        GTValues.VNF[ULV]));
                tooltip.add(Component.translatable("gtceu.universal.tooltip.energy_storage_capacity",
                        FormattingUtil.formatNumbers(512L)));
                tooltip.add(
                        Component.translatable("gtceu.universal.tooltip.working_area_max", maxArea, maxArea));
            })
            .register();

    public static final Int2IntFunction ULVTanksizeFunction = (tier) -> {
        return 8000;
    };

    public static final MachineDefinition[] ULV_COMBUSTION = registerSimpleGenerator("combustion",
            GTRecipeTypes.COMBUSTION_GENERATOR_FUELS, ULVTanksizeFunction, 0.1f, ULV);
    public static final MachineDefinition[] ULV_STEAM_TURBINE = registerSimpleGenerator("steam_turbine",
            GTRecipeTypes.STEAM_TURBINE_FUELS, ULVTanksizeFunction , 0.0f,ULV);
    public static final MachineDefinition[] ULV_GAS_TURBINE = registerSimpleGenerator("gas_turbine",
            GTRecipeTypes.GAS_TURBINE_FUELS, ULVTanksizeFunction, 0.1f, ULV);
//    public static final MachineDefinition[] ULV_COAL_BURNER = registerSimpleGenerator("ulv_coal_burner",
//            WSRecipeTypes.COAL_BURNER_FUELS, i -> 0, 0.1f, ULV);

    public static final MachineDefinition ULV_COAL_BURNER = REGISTRATE.machine("ulv_coal_burner",
                holder -> new SimpleGeneratorMachine(holder, ULV, i -> 0)).tier(ULV)
            .rotationState(RotationState.ALL)
            .langValue("Shoddy Coal Burning Generator")
            .recipeType(COAL_BURNER_FUELS)
            .editableUI(SimpleGeneratorMachine.EDITABLE_UI_CREATOR.apply(WorldshaperCore.id("ulv_coal_burner"), COAL_BURNER_FUELS))
            .renderer(() -> new SimpleGeneratorMachineRenderer(ULV, GTCEu.id("block/generators/combustion")))
//            .tooltipBuilder((stack, tooltip) -> {
//                tooltip.add(Component.literal(""));
//            })
            .tooltips(workableTiered(ULV, GTValues.V[ULV], GTValues.V[ULV] * 64, COAL_BURNER_FUELS, 0, false))
            .register();

    public static void init(){

    }
}