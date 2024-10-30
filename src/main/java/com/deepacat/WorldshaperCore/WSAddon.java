package com.deepacat.WorldshaperCore;

import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

public class WSAddon implements IGTAddon {
    @Override
    public GTRegistrate getRegistrate() {
        return WorldshaperCore.WSCRegistrate;
    }
    @Override
    public void initializeAddon() {

    }
    @Override
    public String addonModId() {
        return WorldshaperCore.MOD_ID;
    }
}
