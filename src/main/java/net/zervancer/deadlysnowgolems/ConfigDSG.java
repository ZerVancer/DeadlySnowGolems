package net.zervancer.deadlysnowgolems;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RestartRequired;

@Modmenu(modId = "deadly-snow-golems")
@Config(name = "deadly-snow-golem", wrapperName = "ModConfig")
public class ConfigDSG {
    @RestartRequired
    public int dmgSnowBall = 1;
}
