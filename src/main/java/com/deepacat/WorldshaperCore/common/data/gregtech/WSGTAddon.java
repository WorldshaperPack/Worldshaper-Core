package com.deepacat.WorldshaperCore.common.data.gregtech;

import com.deepacat.WorldshaperCore.WSConfig;
import com.deepacat.WorldshaperCore.WorldshaperCore;
import com.deepacat.WorldshaperCore.api.registries.WSRegistries;
import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

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
        if(ModList.get().isLoaded("ad_astra") && WSConfig.AD_ASTRA_GEN_LAYERS.get()){
            TagPrefix.oreTagPrefix("moon", BlockTags.MINEABLE_WITH_PICKAXE)
                    .langValue("Moon %s Ore")
                    .registerOre(() -> BuiltInRegistries.BLOCK.get(new ResourceLocation("ad_astra", "moon_stone")).defaultBlockState(),
                            null, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                            new ResourceLocation("ad_astra", "block/moon_stone"));
            TagPrefix.oreTagPrefix("mars", BlockTags.MINEABLE_WITH_PICKAXE)
                    .langValue("Mars %s Ore")
                    .registerOre(() -> BuiltInRegistries.BLOCK.get(new ResourceLocation("ad_astra", "mars_stone")).defaultBlockState(),
                            null, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                            new ResourceLocation("ad_astra", "block/mars_stone"));
            TagPrefix.oreTagPrefix("mercury", BlockTags.MINEABLE_WITH_PICKAXE)
                    .langValue("Mercury %s Ore")
                    .registerOre(() -> BuiltInRegistries.BLOCK.get(new ResourceLocation("ad_astra", "mercury_stone")).defaultBlockState(),
                            null, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                            new ResourceLocation("ad_astra", "block/mercury_stone"));
            TagPrefix.oreTagPrefix("venus", BlockTags.MINEABLE_WITH_PICKAXE)
                    .langValue("Venus %s Ore")
                    .registerOre(() -> BuiltInRegistries.BLOCK.get(new ResourceLocation("ad_astra", "venus_stone")).defaultBlockState(),
                            null, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(3.0F, 3.0F),
                            new ResourceLocation("ad_astra", "block/venus_stone"));
        }
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
