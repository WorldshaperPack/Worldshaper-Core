package com.deepacat.WorldshaperCore;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class WSPortals {
	public static void init() {
//		All portal frames, tint, and light items are placeholder! replaced later

		// semiflat
		CustomPortalBuilder.beginPortal()
				.frameBlock(new ResourceLocation("minecraft:stone"))
				.destDimID(new ResourceLocation("worldshaper:semiflat"))
				.lightWithItem(Items.DIAMOND)
				.tintColor(255, 255, 255)
				.forcedSize(3,3)
				.registerPortal();
		// Limbo
		CustomPortalBuilder.beginPortal()
				.frameBlock(new ResourceLocation("dimdoors:solid_static"))
				.destDimID(new ResourceLocation("dimdoors:limbo"))
				.lightWithItem(Items.DIAMOND)
				.tintColor(255, 255, 255)
				.forcedSize(3,3)
				.registerPortal();
		// Moon
		CustomPortalBuilder.beginPortal()
				.frameBlock(new ResourceLocation("ad_astra:desh_factory_block"))
				.destDimID(new ResourceLocation("ad_astra:moon"))
				.lightWithItem(Items.DIAMOND)
				.tintColor(255, 255, 255)
				.forcedSize(3,3)
				.registerPortal();
		// Primordial Caves
		CustomPortalBuilder.beginPortal()
				.frameBlock(new ResourceLocation("alexscaves:limestone"))
				.destDimID(new ResourceLocation("worldshaper:primordial"))
				.lightWithItem(Items.DIAMOND)
				.tintColor(255, 255, 255)
				.forcedSize(3,3)
				.registerPortal();
		// Candy
		CustomPortalBuilder.beginPortal()
				.frameBlock(new ResourceLocation("alexscaves:block_of_polished_chocolate"))
				.destDimID(new ResourceLocation("worldshaper:candy"))
				.lightWithItem(Items.DIAMOND)
				.tintColor(255, 255, 255)
				.forcedSize(3,3)
				.registerPortal();
		// Mars
		CustomPortalBuilder.beginPortal()
				.frameBlock(new ResourceLocation("ad_astra:ostrum_factory_block"))
				.destDimID(new ResourceLocation("ad_astra:mars"))
				.lightWithItem(Items.DIAMOND)
				.tintColor(255, 255, 255)
				.forcedSize(3,3)
				.registerPortal();
		// Magnetic Caves
		CustomPortalBuilder.beginPortal()
				.frameBlock(new ResourceLocation("alexscaves:galena"))
				.destDimID(new ResourceLocation("worldshaper:magnetic"))
				.lightWithItem(Items.DIAMOND)
				.tintColor(255, 255, 255)
				.forcedSize(3,3)
				.registerPortal();
		// Venus
		CustomPortalBuilder.beginPortal()
				.frameBlock(new ResourceLocation("ad_astra:calorite_factory_block"))
				.destDimID(new ResourceLocation("ad_astra:venus"))
				.lightWithItem(Items.DIAMOND)
				.tintColor(255, 255, 255)
				.forcedSize(3,3)
				.registerPortal();
		// Forlorn Hollows
		CustomPortalBuilder.beginPortal()
				.frameBlock(new ResourceLocation("alexscaves:coprolith"))
				.destDimID(new ResourceLocation("worldshaper:forlorn"))
				.lightWithItem(Items.DIAMOND)
				.tintColor(255, 255, 255)
				.forcedSize(3,3)
				.registerPortal();
		// Abyssal Depths
		CustomPortalBuilder.beginPortal()
				.frameBlock(new ResourceLocation("alexscaves:abyssmarine"))
				.destDimID(new ResourceLocation("worldshaper:abyssal"))
				.lightWithItem(Items.DIAMOND)
				.tintColor(255, 255, 255)
				.forcedSize(3,3)
				.registerPortal();
		// Glacio
		CustomPortalBuilder.beginPortal()
				.frameBlock(new ResourceLocation("ad_astra:etrium_factory_block"))
				.destDimID(new ResourceLocation("ad_astra:glacio"))
				.lightWithItem(Items.DIAMOND)
				.tintColor(255, 255, 255)
				.forcedSize(3,3)
				.registerPortal();
		// Toxic Caves
		CustomPortalBuilder.beginPortal()
				.frameBlock(new ResourceLocation("alexscaves:block_of_uranium"))
				.destDimID(new ResourceLocation("worldshaper:toxic"))
				.lightWithItem(Items.DIAMOND)
				.tintColor(255, 255, 255)
				.forcedSize(3,3)
				.registerPortal();

	}
}
