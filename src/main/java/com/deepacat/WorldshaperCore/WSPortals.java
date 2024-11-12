package com.deepacat.WorldshaperCore;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.kyrptonaught.customportalapi.CustomPortalsMod;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;
import org.intellij.lang.annotations.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class WSPortals {
    private static final int NUM_PORTAL_TYPES = 13;
    private static final Iterator<RegistryObject<CustomPortalBlock>> PORTAL_BLOCKS;

    static {
        RegistryObject<CustomPortalBlock>[] portalBlocks = new RegistryObject[NUM_PORTAL_TYPES];
        for (int i = 0; i != NUM_PORTAL_TYPES; ++i) {
            portalBlocks[i] = CustomPortalsMod.BLOCKS.register("portal_" + i, () ->
                    new CustomPortalBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_PORTAL)
                            .noCollission().strength(-1.0F).sound(SoundType.GLASS).lightLevel((state) -> 11))
            );
        }
        PORTAL_BLOCKS = Arrays.stream(portalBlocks).iterator();
    }

    public static void earlyInit() {
    }

    public static void init() {
//		All portal frames, tint, and light items are placeholder! replaced later

        // test portal
        CustomPortalBuilder.beginPortal()
                .frameBlock(GTBlocks.COIL_TRINIUM.get())
                .customPortalBlock(PORTAL_BLOCKS.next())
                .destDimID(new ResourceLocation("minecraft:the_end"))
                .lightWithItem(Items.DIAMOND)
                .tintColor(33, 0, 33)
                .registerPortal();
        // semiflat
        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.STONE)
                .customPortalBlock(PORTAL_BLOCKS.next())
                .destDimID(new ResourceLocation("worldshaper:semiflat"))
                .lightWithItem(Items.DIAMOND)
                .tintColor(255, 255, 255)
                .registerPortal();
        // Limbo
        CustomPortalBuilder.beginPortal()
                .frameBlock(new ResourceLocation("dimdoors:solid_static"))
                .customPortalBlock(PORTAL_BLOCKS.next())
                .destDimID(new ResourceLocation("dimdoors:limbo"))
                .lightWithItem(Items.DIAMOND)
                .tintColor(255, 255, 255)
                .registerPortal();
        // Moon
        CustomPortalBuilder.beginPortal()
                .frameBlock(new ResourceLocation("ad_astra:desh_factory_block"))
                .customPortalBlock(PORTAL_BLOCKS.next())
                .destDimID(new ResourceLocation("ad_astra:moon"))
                .lightWithItem(Items.DIAMOND)
                .tintColor(255, 255, 255)
                .registerPortal();
        // Primordial Caves
        CustomPortalBuilder.beginPortal()
                .frameBlock(new ResourceLocation("alexscaves:limestone"))
                .customPortalBlock(PORTAL_BLOCKS.next())
                .destDimID(new ResourceLocation("worldshaper:primordial"))
                .lightWithItem(Items.DIAMOND)
                .tintColor(255, 255, 255)
                .registerPortal();
        // Candy
        CustomPortalBuilder.beginPortal()
                .frameBlock(new ResourceLocation("alexscaves:block_of_polished_chocolate"))
                .customPortalBlock(PORTAL_BLOCKS.next())
                .destDimID(new ResourceLocation("worldshaper:candy"))
                .lightWithItem(Items.DIAMOND)
                .tintColor(255, 255, 255)
                .registerPortal();
        // Mars
        CustomPortalBuilder.beginPortal()
                .frameBlock(new ResourceLocation("ad_astra:ostrum_factory_block"))
                .customPortalBlock(PORTAL_BLOCKS.next())
                .destDimID(new ResourceLocation("ad_astra:mars"))
                .lightWithItem(Items.DIAMOND)
                .tintColor(255, 255, 255)
                .registerPortal();
        // Magnetic Caves
        CustomPortalBuilder.beginPortal()
                .frameBlock(new ResourceLocation("alexscaves:galena"))
                .customPortalBlock(PORTAL_BLOCKS.next())
                .destDimID(new ResourceLocation("worldshaper:magnetic"))
                .lightWithItem(Items.DIAMOND)
                .tintColor(255, 255, 255)
                .registerPortal();
        // Venus
        CustomPortalBuilder.beginPortal()
                .frameBlock(new ResourceLocation("ad_astra:calorite_factory_block"))
                .customPortalBlock(PORTAL_BLOCKS.next())
                .destDimID(new ResourceLocation("ad_astra:venus"))
                .lightWithItem(Items.DIAMOND)
                .tintColor(255, 255, 255)

                .registerPortal();
        // Forlorn Hollows
        CustomPortalBuilder.beginPortal()
                .frameBlock(new ResourceLocation("alexscaves:coprolith"))
                .customPortalBlock(PORTAL_BLOCKS.next())
                .destDimID(new ResourceLocation("worldshaper:forlorn"))
                .lightWithItem(Items.DIAMOND)
                .tintColor(255, 255, 255)
                .registerPortal();
        // Abyssal Depths
        CustomPortalBuilder.beginPortal()
                .frameBlock(new ResourceLocation("alexscaves:abyssmarine"))
                .customPortalBlock(PORTAL_BLOCKS.next())
                .destDimID(new ResourceLocation("worldshaper:abyssal"))
                .lightWithItem(Items.DIAMOND)
                .tintColor(255, 255, 255)
                .registerPortal();
        // Glacio
        CustomPortalBuilder.beginPortal()
                .frameBlock(new ResourceLocation("ad_astra:etrium_factory_block"))
                .customPortalBlock(PORTAL_BLOCKS.next())
                .destDimID(new ResourceLocation("ad_astra:glacio"))
                .lightWithItem(Items.DIAMOND)
                .tintColor(255, 255, 255)
                .registerPortal();
        // Toxic Caves
        CustomPortalBuilder.beginPortal()
                .frameBlock(new ResourceLocation("alexscaves:block_of_uranium"))
                .customPortalBlock(PORTAL_BLOCKS.next())
                .destDimID(new ResourceLocation("worldshaper:toxic"))
                .lightWithItem(Items.DIAMOND)
                .tintColor(255, 255, 255)
                .registerPortal();

    }
}
