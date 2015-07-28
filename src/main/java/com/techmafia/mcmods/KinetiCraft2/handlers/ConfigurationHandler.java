package com.techmafia.mcmods.KinetiCraft2.handlers;

import com.techmafia.mcmods.KinetiCraft2.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Meng on 7/27/2015.
 */
public class ConfigurationHandler {
    public static Configuration configuration;
    public static boolean testValue = false;

    public static void init(File configFile) {
        /* Create the config file if it does not exist */
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    public static void loadConfiguration() {
        testValue = configuration.getBoolean("configValue", Configuration.CATEGORY_GENERAL, false, "This is a config test value");

        if (configuration.hasChanged()) {
            configuration.load();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            this.loadConfiguration();
        }
    }
}
