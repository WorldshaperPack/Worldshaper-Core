package com.deepacat.WorldshaperCore.common.data;

import com.deepacat.WorldshaperCore.data.recipe.serialized.chemistry.SteamQuarryRecipes;

import net.minecraft.data.recipes.FinishedRecipe;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTRecipes.RECIPE_FILTERS;

public class WSRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {

        SteamQuarryRecipes.init(provider);
    }
}
