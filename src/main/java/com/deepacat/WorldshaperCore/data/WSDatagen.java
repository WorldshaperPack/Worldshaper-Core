package com.deepacat.WorldshaperCore.data;

import com.deepacat.WorldshaperCore.api.registries.WSRegistries;
import com.deepacat.WorldshaperCore.data.lang.WSLangHandler;
import com.tterrag.registrate.providers.ProviderType;


public class WSDatagen {
	public static void init() {
		WSRegistries.REGISTRATE.addDataGenerator(ProviderType.LANG, WSLangHandler::init);
	}
}
