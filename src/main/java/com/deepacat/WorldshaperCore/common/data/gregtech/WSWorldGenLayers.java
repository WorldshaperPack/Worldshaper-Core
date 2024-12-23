package com.deepacat.WorldshaperCore.common.data.gregtech;

import com.deepacat.WorldshaperCore.WSConfig;
import com.gregtechceu.gtceu.api.data.worldgen.IWorldGenLayer;
import com.gregtechceu.gtceu.api.data.worldgen.SimpleWorldGenLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.fml.ModList;

import java.util.Set;

public class WSWorldGenLayers {
	private static TagKey<Block> moonstone;
	private static TagKey<Block> marsstone;
	private static TagKey<Block> mercurystone;
	private static TagKey<Block> venusstone;

	public static IWorldGenLayer MOON;
	public static IWorldGenLayer MARS;
	public static IWorldGenLayer MERCURY;
	public static IWorldGenLayer VENUS;

	public static void init() {
		if(ModList.get().isLoaded("ad_astra") && WSConfig.AD_ASTRA_GEN_LAYERS.get()){
			moonstone = BlockTags.create(new ResourceLocation("ad_astra","moon_stone_replaceables"));
			marsstone = BlockTags.create(new ResourceLocation("ad_astra","mars_stone_replaceables"));
			mercurystone = BlockTags.create(new ResourceLocation("ad_astra","mercury_stone_replaceables"));
			venusstone = BlockTags.create(new ResourceLocation("ad_astra","venus_stone_replaceables"));

			 MOON = new SimpleWorldGenLayer("moon", () -> new TagMatchTest(moonstone), Set.of(new ResourceLocation("ad_astra:moon")));
			 MARS = new SimpleWorldGenLayer("mars", () -> new TagMatchTest(marsstone), Set.of(new ResourceLocation("ad_astra:mars")));
			 MERCURY = new SimpleWorldGenLayer("mercury", () -> new TagMatchTest(mercurystone), Set.of(new ResourceLocation("ad_astra:mercury")));
			 VENUS = new SimpleWorldGenLayer("venus", () -> new TagMatchTest(venusstone), Set.of(new ResourceLocation("ad_astra:venus")));
		}
	}

}
