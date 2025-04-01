package net.zervancer.deadlysnowgolems.config;

import java.net.URI;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import net.zervancer.deadlysnowgolems.DeadlySnowGolems;

public class ModMenuIntegration implements ModMenuApi{
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (DeadlySnowGolems.isYACLPresent()) {
            return YACLIntegration::createScreen;
        }
        String link = "https://modrinth.com/mod/yacl";
        return parent ->
            new ConfirmLinkScreen(result -> {
                if (result) {
                    Util.getOperatingSystem().open(URI.create(link));
                }
                MinecraftClient.getInstance().setScreen(parent);
            },
                Text.literal("YACL is required to view config in ModMenu"),
                Text.literal(link).append("\n\nConfig can still be edited in the config file, \"deadly-snow-golems\", \n in the config folder but requires restarting"),
                link,
                ScreenTexts.CANCEL,
                true
            );
    }
}
