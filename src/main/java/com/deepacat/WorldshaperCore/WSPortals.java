package com.deepacat.WorldshaperCore;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class WSPortals {
	public static void init() {
//		A placeholder portal needs to be registered for the train compat to work with Kubejs so I guess this is here
		CustomPortalBuilder.beginPortal()
				.frameBlock(GTBlocks.MACHINE_CASING_UHV.get())
				.destDimID(new ResourceLocation("minecraft:the_nether"))
				.lightWithItem(Items.DIAMOND)
				.tintColor(33, 0, 33)
				.forcedSize(3, 3)
				.registerPortal();
	}
}
