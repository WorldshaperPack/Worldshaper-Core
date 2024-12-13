package com.deepacat.WorldshaperCore;

import com.deepacat.WorldshaperCore.api.registries.WSRegistries;
import com.deepacat.WorldshaperCore.common.data.WSRecipes;
import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import net.minecraft.data.recipes.FinishedRecipe;

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
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        //CustomRecipes.init(provider);
        WSRecipes.init(provider);
    }
}
