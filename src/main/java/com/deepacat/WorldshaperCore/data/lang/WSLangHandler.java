package com.deepacat.WorldshaperCore.data.lang;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.tterrag.registrate.providers.RegistrateLangProvider;

import static com.tterrag.registrate.providers.RegistrateLangProvider.toEnglishName;

public class WSLangHandler extends com.gregtechceu.gtceu.data.lang.LangHandler {
	public static void init(RegistrateLangProvider provider) {
		WSMachineLang.init(provider);

		for (var recipeType : GTRegistries.RECIPE_TYPES) {
			provider.add(recipeType.registryName.toLanguageKey(), toEnglishName(recipeType.registryName.getPath()));
		}

		for (TagPrefix tagPrefix : TagPrefix.values()) {
			provider.add(tagPrefix.getUnlocalizedName(), tagPrefix.langValue);
		}
	}
}
