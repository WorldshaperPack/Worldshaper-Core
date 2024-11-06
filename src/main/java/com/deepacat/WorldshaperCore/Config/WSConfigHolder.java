package com.deepacat.WorldshaperCore.Config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WSConfigHolder {
	private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

	public static final String CATEGORY_PACK = "pack";
	public static final String CATEGORY_GREGTECH = "gregtech";


	public static ForgeConfigSpec COMMON_CONFIG;

	public static ForgeConfigSpec.BooleanValue REMOVE_GT_MATERIALS;

	static {
		COMMON_BUILDER.comment("Make sure config changes are duplicated on both Clients and the Server when running a dedicated Server,")
				.comment(" as the config isnt synced between Clients and Server.");
		COMMON_BUILDER.comment("General Settings").push(CATEGORY_PACK);
		COMMON_BUILDER.comment("General Settings").push(CATEGORY_GREGTECH);

		REMOVE_GT_MATERIALS = COMMON_BUILDER.comment("If gt materials should be removed, for worldshaper pack")
				.define("remove_gt_materials", false);

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
