package com.deepacat.WorldshaperCore.data.recipe;

import com.gregtechceu.gtceu.api.GTValues;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.deepacat.WorldshaperCore.common.data.gregtech.WSRecipeTypes.*;
import static com.gregtechceu.gtceu.api.GTValues.*;

public class GeneratorFuelRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        COAL_BURNER_FUELS.recipeBuilder("coal")
                .inputItems(Items.COAL)
                .duration(200)
                .EUt(-GTValues.V[ULV])
                .save(provider);
    }
}
