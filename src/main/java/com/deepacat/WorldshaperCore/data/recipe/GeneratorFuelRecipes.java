package com.deepacat.WorldshaperCore.data.recipe;

import com.gregtechceu.gtceu.api.GTValues;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.deepacat.WorldshaperCore.common.data.gregtech.WSRecipeTypes.*;
import static com.gregtechceu.gtceu.api.GTValues.*;

public class GeneratorFuelRecipes {
    public static final TagKey<Item> coals = ItemTags.create(new ResourceLocation("minecraft", "coals"));

    public static void init(Consumer<FinishedRecipe> provider) {
        COAL_BURNER_FUELS.recipeBuilder("coal")
                .inputItems(Items.COAL)
                .duration(200)
                .EUt(-GTValues.V[ULV])
                .save(provider);

        COAL_BURNER_FUELS.recipeBuilder("charcoal")
                .inputItems(Items.CHARCOAL)
                .duration(160)
                .EUt(-GTValues.V[ULV])
                .save(provider);
    }
}
