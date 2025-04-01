package net.zervancer.deadlysnowgolems.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;

import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import net.zervancer.deadlysnowgolems.DeadlySnowGolems;

public class YACLIntegration {
    public static ConfigSettings getConfig() {
        return CONFIG_HANDLER.instance();
    }

    protected static final ConfigClassHandler<ConfigSettings> CONFIG_HANDLER = ConfigClassHandler.createBuilder(ConfigSettings.class)
        .id(Identifier.of(DeadlySnowGolems.MOD_ID, "configsettings"))
        .serializer(config -> GsonConfigSerializerBuilder.create(config)
            .setPath(FabricLoader.getInstance().getConfigDir().resolve(DeadlySnowGolems.MOD_ID + ".json"))
            .build())
        .build();
    

    protected static Screen createScreen(Screen parent) {
        load();
        return YetAnotherConfigLib.create(CONFIG_HANDLER, (defaults, config, builder) -> builder
            .title(Text.literal("Deadly Snow Golems"))
            .category(ConfigCategory.createBuilder()
                .name(Text.literal("Deadly Snow Golems"))
                .option(Option.<Integer>createBuilder()
                    .name(Text.literal("Snow Golem Damage"))
                    .description(OptionDescription.of(Text.literal("The general damage snow golems do when hitting an entity")))
                    .binding(1, () -> config.defaultDMG, val -> config.defaultDMG = val)
                    .controller(opt -> IntegerFieldControllerBuilder.create(opt).min(0).max(Integer.MAX_VALUE))
                    .build()
                )
                .build()
            )
            .save(YACLIntegration::save)
        )
        .generateScreen(parent);
    }

    public static void load() {
        CONFIG_HANDLER.load();
    }

    public static void save() {
        CONFIG_HANDLER.save();
    }

}
