package com.deepacat.WorldshaperCore.common.data.gregtech;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.GENERATOR;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MULTIBLOCK;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;

public class WSRecipeTypes {

    public static final GTRecipeType STEAM_QUARRY_RECIPES = GTRecipeTypes.register("steam_quarry", MULTIBLOCK)
            .setMaxIOSize(1, 9, 0, 0);
    public static final GTRecipeType COAL_BURNER_FUELS = GTRecipeTypes.register("coal_burner", GENERATOR)
            .setMaxIOSize(1, 0, 0, 0).setEUIO(IO.OUT)
            .setSlotOverlay(false, false, true, GuiTextures.FURNACE_OVERLAY_2)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COMBUSTION);

    public static void init() {

    }
}
