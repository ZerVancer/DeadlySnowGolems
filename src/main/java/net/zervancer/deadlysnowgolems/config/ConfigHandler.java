package net.zervancer.deadlysnowgolems.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import net.fabricmc.loader.api.FabricLoader;

public class ConfigHandler {
    private static File configFolder = FabricLoader.getInstance().getConfigDir().toFile();
    private static File configFile = new File(configFolder, "deadly-snow-golem.json");
    private static Gson gson = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

    
    public static ConfigSettings readConfig() {
        try {
            JsonReader reader = new JsonReader(new FileReader(configFile));
            ConfigSettings configSettings = gson.fromJson(reader, ConfigSettings.class);
            reader.close();
            return configSettings;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void createConfig() {
        try {
            FileWriter fileWriter = new FileWriter(configFile);
            fileWriter.write(gson.toJson(new ConfigSettings()));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    public static void init() {
        if (configFile.exists()) { readConfig(); }
        else { createConfig(); }
    }
}
