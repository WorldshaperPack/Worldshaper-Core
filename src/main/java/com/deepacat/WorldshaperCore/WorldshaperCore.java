package com.deepacat.WorldshaperCore;

import com.deepacat.WorldshaperCore.api.registries.WSRegistries;
import com.deepacat.WorldshaperCore.common.data.WSPowerlines;
import com.deepacat.WorldshaperCore.common.data.customportalapi.CustomPortalCreateTrainCompat;
import com.deepacat.WorldshaperCore.common.data.customportalapi.WSPortals;
import com.deepacat.WorldshaperCore.common.data.gregtech.WSMachines;
import com.deepacat.WorldshaperCore.common.data.gregtech.WSRecipeTypes;
import com.deepacat.WorldshaperCore.data.WSDatagen;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.simibubi.create.content.trains.track.AllPortalTracks;
import net.createmod.catnip.data.Pair;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(WorldshaperCore.MOD_ID)
public class WorldshaperCore {
    public static final String MOD_ID = "worldshapercore";
    public static final Logger LOGGER = LogManager.getLogger();

    public static ResourceLocation id(String path) {
        return new ResourceLocation("worldshapercore", FormattingUtil.toLowerCaseUnder(path));
    }

    public WorldshaperCore() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        WorldshaperCore.init();
        WSPortals.earlyInit();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::addMaterialRegistries);
        modEventBus.addListener(this::addMaterials);
        modEventBus.addListener(this::modifyMaterials);
        modEventBus.addGenericListener(GTRecipeType.class, this::registerRecipeTypes);
        modEventBus.addGenericListener(MachineDefinition.class, this::registerMachines);

        // Most other events are fired on Forge's bus.
        // If we want to use annotations to register event listeners,
        // we need to register our object like this!
        MinecraftForge.EVENT_BUS.register(this);
    }

    private static void init() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WSConfig.COMMON_CONFIG);
        WSConfig.loadConfig(WSConfig.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-common.toml"));

        WSRegistries.REGISTRATE.registerRegistrate();
        WSDatagen.init();
        WSPowerlines.init();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        if (WSConfig.CUSTOM_PORTAL_API_WS_PORTALS.get()) {
            WSPortals.init();
//            event.enqueueWork(() -> {
//                CustomPortalApiRegistry.getAllPortalLinks().forEach(
//                        pl -> AllPortalTracks.tryRegisterIntegration(
//                                pl.getPortalBlock(), p -> CustomPortalCreateTrainCompat.createPortalTrackProvider(p, pl)));
//            });
        }
    }

    private void clientSetup(final FMLClientSetupEvent event) {
//        LOGGER.info("Hey, we're on Minecraft version {}!", Minecraft.getInstance().getLaunchedVersion());
    }

    // You MUST have this for custom materials.
    // Remember to register them not to GT's namespace, but your own.
    private void addMaterialRegistries(MaterialRegistryEvent event) {
        GTCEuAPI.materialManager.createRegistry(WorldshaperCore.MOD_ID);
    }

    // As well as this.
    private void addMaterials(MaterialEvent event) {
        //CustomMaterials.init();
    }

    // This is optional, though.
    private void modifyMaterials(PostMaterialEvent event) {
        //CustomMaterials.modify();
    }

    private void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        //CustomRecipeTypes.init();
        WSRecipeTypes.init();
    }

    private void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        //CustomMachines.init();
        WSMachines.init();
    }
}
