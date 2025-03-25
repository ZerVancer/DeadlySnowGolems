package net.zervancer.deadlysnowgolems;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadlySnowGolems implements ModInitializer {
	public static final String MOD_ID = "deadly-snow-golems";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final net.zervancer.deadlysnowgolems.ModConfig CONFIG = net.zervancer.deadlysnowgolems.ModConfig.createAndLoad();

	@Override
	public void onInitialize() {}
}