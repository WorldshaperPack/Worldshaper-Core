package com.deepacat.WorldshaperCore.data.recipe;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.deepacat.WorldshaperCore.common.data.gregtech.WSRecipeTypes.STEAM_QUARRY_RECIPES;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class SteamQuarryRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        STEAM_QUARRY_RECIPES.recipeBuilder("bronzedrillrecipe")
                .chancedInput(ChemicalHelper.get(toolHeadDrill, Bronze), 400, 0)
                .chancedOutput(ore, Iron, 4000, 0)
                .chancedOutput(ore, Coal, 6000, 0)
                .chancedOutput(ore, Copper, 2000, 0)
                .chancedOutput(ore, Tin, 3000, 0)
                .chancedOutput(ore, Gold, 1500, 0)
                .chancedOutput(ore, Redstone, 2000, 0)
                .EUt(32)
                .duration(600)
                .save(provider);
    }
}
