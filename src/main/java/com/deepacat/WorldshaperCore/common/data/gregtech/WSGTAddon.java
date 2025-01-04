package com.deepacat.WorldshaperCore.common.data.gregtech;

import com.deepacat.WorldshaperCore.WSConfig;
import com.deepacat.WorldshaperCore.WorldshaperCore;
import com.deepacat.WorldshaperCore.api.registries.WSRegistries;
import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import net.minecraft.data.recipes.FinishedRecipe;

import net.minecraftforge.fml.ModList;

import java.util.function.Consumer;

@GTAddon
public class WSGTAddon implements IGTAddon {
    @Override
    public GTRegistrate getRegistrate() {
        return WSRegistries.REGISTRATE;
    }

    @Override
    public void initializeAddon() {

    }
    @Override
    public String addonModId() {
        return WorldshaperCore.MOD_ID;
    }

    @Override
    public void registerTagPrefixes() {

    }

    @Override
    public void registerWorldgenLayers() {
        if(ModList.get().isLoaded("ad_astra") && WSConfig.AD_ASTRA_GEN_LAYERS.get())
            WSWorldGenLayers.init();
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        WSRecipes.init(provider);
    }
}
