package com.deepacat.WorldshaperCore.common.data;

import com.deepacat.WorldshaperCore.data.recipe.SteamQuarryRecipes;
import static com.deepacat.WorldshaperCore.common.data.WSRecipeTypes.COAL_BURNER_FUELS;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import net.minecraft.data.recipes.FinishedRecipe;
import java.util.function.Consumer;

public class WSRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        SteamQuarryRecipes.init(provider);
        COAL_BURNER_FUELS.recipeBuilder("coal_burning")
                .inputItems(TagPrefix.gem, Coal)
                .EUt(-8)
                .duration(200)
                .save(provider);
    }
}
