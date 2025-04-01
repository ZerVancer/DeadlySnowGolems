package net.zervancer.deadlysnowgolems;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import net.zervancer.deadlysnowgolems.config.ConfigHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadlySnowGolems implements ModInitializer {
	public static final String MOD_ID = "deadly-snow-golems";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static boolean isYACLPresent() {
        if (FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3")) { return true; }
		else { return false; }
	}

	@Override
	public void onInitialize() {
		if (!isYACLPresent()) {
			ConfigHandler.init();
		}
	}
}