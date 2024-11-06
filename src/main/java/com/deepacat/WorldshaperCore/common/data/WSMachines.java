package com.deepacat.WorldshaperCore.common.data;


import com.deepacat.WorldshaperCore.common.machine.multiblock.ParallelMultiblock;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.common.data.GTCompassSections;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;
import net.minecraft.world.level.block.Blocks;


import static com.deepacat.WorldshaperCore.api.registries.WSRegistries.REGISTRATE;
import static com.deepacat.WorldshaperCore.common.data.WSRecipeTypes.STEAM_QUARRY_RECIPES;
import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.*;


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




    public static final MultiblockMachineDefinition parallelwiremill = REGISTRATE
            .multiblock("idk", holder -> new ParallelMultiblock(holder, 8))
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
            .compassSections(GTCompassSections.TIER[IV])
            .compassNodeSelf()
            .register();

    public static final MultiblockMachineDefinition STEAM_QUARRY = REGISTRATE
            .multiblock("steam_quarry", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(STEAM_QUARRY_RECIPES)
            .alwaysTryModifyRecipe(true)
            .appearanceBlock(CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("FFF", "FFF", "###", "###", "###")
                    .aisle("FCF", "FCF", "PCP", "PCP", "PXP")
                    .aisle("FFF", "FSF", "###", "###", "###")
                    .where('S', Predicates.controller(blocks(definition.getBlock())))
                    .where('F', blocks(CASING_STEEL_SOLID.get()).or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where('C', blocks(Blocks.CHAIN))
                    .where('P', blocks(CASING_STEEL_PIPE.get()))
                    .where('X', blocks(CASING_STEEL_SOLID.get()))
                    .where('#', Predicates.air())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/multiblock/primitive_blast_furnace"))
            .compassSections(GTCompassSections.TIER[IV])
            .compassNodeSelf()
            .register();



    public static void init(){

    }
}