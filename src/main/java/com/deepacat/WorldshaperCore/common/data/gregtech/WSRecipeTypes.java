package com.deepacat.WorldshaperCore.common.data.gregtech;

import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MULTIBLOCK;

public class WSRecipeTypes {

    public static final GTRecipeType STEAM_QUARRY_RECIPES = GTRecipeTypes.register("steam_quarry", MULTIBLOCK).setMaxIOSize(1, 9, 0, 0);

    public static void init(){

    }
}
