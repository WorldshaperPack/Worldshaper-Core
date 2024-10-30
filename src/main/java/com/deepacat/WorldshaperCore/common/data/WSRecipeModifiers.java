package com.deepacat.WorldshaperCore.common.data;

import com.deepacat.WorldshaperCore.api.capability.IParallelMultiblock;
import com.gregtechceu.gtceu.api.capability.IParallelHatch;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.logic.OCParams;
import com.gregtechceu.gtceu.api.recipe.logic.OCResult;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class WSRecipeModifiers extends GTRecipeModifiers {
    public static final RecipeModifier SETPARALLEL = (machine, recipe, params, result) -> WSRecipeModifiers
            .Parallel(machine, recipe, false, params, result);


    public static GTRecipe Parallel(MetaMachine machine, @NotNull GTRecipe recipe,
                                    boolean modifyDuration,
                                    @NotNull OCParams params, @NotNull OCResult result) {
        if (machine instanceof IMultiController controller && controller.isFormed()) {

            if (machine instanceof IParallelMultiblock) {
                var recipeEU = RecipeHelper.getInputEUt(recipe);
                var parallelRecipe = ParallelLogic.applyParallel(machine, recipe, ((IParallelMultiblock) machine).getParallelAmount(),
                        modifyDuration);
                if (parallelRecipe.getSecond() == 0)
                    return null;
                result.init(recipeEU, recipe.duration, parallelRecipe.getSecond(), params.getOcAmount());
                return parallelRecipe.getFirst();
            }
        }
        return recipe;
    }
}
