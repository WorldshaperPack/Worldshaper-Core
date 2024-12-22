package com.deepacat.WorldshaperCore.data.lang;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.tterrag.registrate.providers.RegistrateLangProvider;

public class LangHandler extends com.gregtechceu.gtceu.data.lang.LangHandler {
	public static void init(RegistrateLangProvider provider) {
		provider.add("tagprefix.moon", TagPrefix.get("moon").langValue());
		provider.add("tagprefix.mars", TagPrefix.get("mars").langValue());
		provider.add("tagprefix.venus", TagPrefix.get("venus").langValue());
		provider.add("tagprefix.mercury", TagPrefix.get("mercury").langValue());

	}
}
