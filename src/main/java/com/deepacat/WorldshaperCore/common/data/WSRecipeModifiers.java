package com.deepacat.WorldshaperCore.common.data;

import com.deepacat.WorldshaperCore.api.capability.IParallelMultiblock;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;

import org.jetbrains.annotations.NotNull;

public class WSRecipeModifiers extends GTRecipeModifiers {
    public static final RecipeModifier SETPARALLEL = WSRecipeModifiers::Parallel;

    public static ModifierFunction Parallel(MetaMachine machine, @NotNull GTRecipe recipe) {
        if (machine instanceof IMultiController controller && controller.isFormed()) {
            if (machine instanceof IParallelMultiblock pmb) {
                int maxParallels = pmb.getParallelAmount();
                var parallels = ParallelLogic.getParallelAmount(machine, recipe, maxParallels);
                return ModifierFunction.builder()
                        .modifyAllContents(ContentModifier.multiplier(parallels))
                        .eutMultiplier(parallels)
                        .parallels(parallels)
                        .build();
            }
        }
        return ModifierFunction.IDENTITY;
    }
}