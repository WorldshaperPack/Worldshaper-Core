package com.deepacat.WorldshaperCore.common.data.gregtech;

import com.deepacat.WorldshaperCore.WSConfig;
import com.deepacat.WorldshaperCore.data.recipe.GeneratorFuelRecipes;
import com.deepacat.WorldshaperCore.data.recipe.SteamQuarryRecipes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class WSRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        SteamQuarryRecipes.init(provider);
        GeneratorFuelRecipes.init(provider);
    }
}
