package com.deepacat.WorldshaperCore;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class WSportals {
	public static void init() {
		CustomPortalBuilder.beginPortal()
				.frameBlock(GTBlocks.COIL_TRINIUM.get())
				.destDimID(new ResourceLocation("minecraft:the_end"))
				.lightWithItem(Items.DIAMOND)
				.tintColor(33, 0, 33)
				.registerPortal();
	}
}
