package com.deepacat.WorldshaperCore.common.data;

import com.gregtechceu.gtceu.api.data.worldgen.IWorldGenLayer;
import com.gregtechceu.gtceu.api.data.worldgen.SimpleWorldGenLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.Set;

public class WSWorldGenLayers {
	private static final TagKey<Block> moonstone = BlockTags.create(new ResourceLocation("ad_astra","moon_stone_replaceables"));
	private static final TagKey<Block> marsstone = BlockTags.create(new ResourceLocation("ad_astra","mars_stone_replaceables"));
	private static final TagKey<Block> mercurystone = BlockTags.create(new ResourceLocation("ad_astra","mercury_stone_replaceables"));
	private static final TagKey<Block> venusstone = BlockTags.create(new ResourceLocation("ad_astra","venus_stone_replaceables"));

	public static IWorldGenLayer MOON = new SimpleWorldGenLayer("moon", () -> new TagMatchTest(moonstone), Set.of(new ResourceLocation("ad_astra:moon")));
	public static IWorldGenLayer MARS = new SimpleWorldGenLayer("mars", () -> new TagMatchTest(marsstone), Set.of(new ResourceLocation("ad_astra:mars")));
	public static IWorldGenLayer MERCURY = new SimpleWorldGenLayer("mercury", () -> new TagMatchTest(mercurystone), Set.of(new ResourceLocation("ad_astra:mercury")));
	public static IWorldGenLayer VENUS = new SimpleWorldGenLayer("venus", () -> new TagMatchTest(venusstone), Set.of(new ResourceLocation("ad_astra:venus")));

	public static void init() {

	}

}
