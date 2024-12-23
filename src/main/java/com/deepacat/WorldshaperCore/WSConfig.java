package com.deepacat.WorldshaperCore;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WSConfig {
	private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

	public static final String CATEGORY_PACK = "pack";
	public static final String CATEGORY_GREGTECH = "gregtech";

	public static ForgeConfigSpec COMMON_CONFIG;

	public static ForgeConfigSpec.BooleanValue REGISTER_STEAM_QUARRY;
	public static ForgeConfigSpec.BooleanValue REGISTER_WS_MACERATORS;
	public static ForgeConfigSpec.BooleanValue REGISTER_PARALLEL_MULTIS;
	public static ForgeConfigSpec.BooleanValue REGISTER_ULV_MINER;

	public static ForgeConfigSpec.BooleanValue AD_ASTRA_GEN_LAYERS;
	public static ForgeConfigSpec.BooleanValue CUSTOM_PORTAL_API_WS_PORTALS;


	static {
		COMMON_BUILDER.comment("Make sure config changes are duplicated on both Clients and the Server when running a dedicated Server,")
				.comment(" as the config isnt synced between Clients and Server.");
		COMMON_BUILDER.comment("General Pack Configs").push(CATEGORY_PACK);

		CUSTOM_PORTAL_API_WS_PORTALS = COMMON_BUILDER.comment("If customportalapi portals register, for worldshaper pack")
				.define("Custom Portal API Worldshaper Portals", false);

		COMMON_BUILDER.comment("GT Related Configs").push(CATEGORY_GREGTECH);

		REGISTER_STEAM_QUARRY = COMMON_BUILDER.comment("If testing quarry multi should be registered")
				.define("Testing Quarry Multiblock", false);
		AD_ASTRA_GEN_LAYERS = COMMON_BUILDER.comment("If gt worldgen layers should be generated for ad astra stones, for kjs to use ore tagprefix's")
				.define("Generate Ad Astra GT layers", false);
		REGISTER_WS_MACERATORS = COMMON_BUILDER.define("register worldshaper unique macerators", false);
		REGISTER_PARALLEL_MULTIS = COMMON_BUILDER.define("register worldshaper parallel multis", false);
		REGISTER_ULV_MINER = COMMON_BUILDER.define("register worldshaper ulv miner", false);



		COMMON_BUILDER.pop();
		COMMON_CONFIG = COMMON_BUILDER.build();
	}

	public static void loadConfig(ForgeConfigSpec spec, java.nio.file.Path path) {
		final CommentedFileConfig configData = CommentedFileConfig.builder(path)
				.sync()
				.autosave()
				.writingMode(WritingMode.REPLACE)
				.build();
		configData.load();
		spec.setConfig(configData);
	}
}
