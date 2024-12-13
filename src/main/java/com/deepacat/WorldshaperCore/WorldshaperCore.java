package com.deepacat.WorldshaperCore;

import com.deepacat.WorldshaperCore.api.registries.WSRegistries;
import com.deepacat.WorldshaperCore.common.data.WSMachines;
import com.deepacat.WorldshaperCore.common.data.WSRecipeTypes;
import com.deepacat.WorldshaperCore.data.WSDatagen;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.simibubi.create.content.trains.track.AllPortalTracks;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
        WSRegistries.REGISTRATE.registerRegistrate();
        WSDatagen.init();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        WSPortals.init();
        event.enqueueWork(() -> {
            CustomPortalApiRegistry.getAllPortalLinks().forEach(
                    pl -> AllPortalTracks.registerIntegration(
                            pl.getPortalBlock(), p -> CustomPortalCreateTrainCompat.createPortalTrackProvider(p, pl)));
        });
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
