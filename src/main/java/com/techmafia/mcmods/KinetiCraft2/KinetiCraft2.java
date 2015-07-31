package com.techmafia.mcmods.KinetiCraft2;

import com.techmafia.mcmods.KinetiCraft2.handlers.ConfigurationHandler;
import com.techmafia.mcmods.KinetiCraft2.handlers.KC2GuiHandler;
import com.techmafia.mcmods.KinetiCraft2.init.KinectiCraft2TileEntities;
import com.techmafia.mcmods.KinetiCraft2.init.KinetiCraft2Blocks;
import com.techmafia.mcmods.KinetiCraft2.init.KinetiCraft2Items;
import com.techmafia.mcmods.KinetiCraft2.reference.Reference;
import com.techmafia.mcmods.KinetiCraft2.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)

public class KinetiCraft2
{
    @Mod.Instance(Reference.MOD_ID)
    public static KinetiCraft2 instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent preInitializationEvent) {
        /* Config */
        ConfigurationHandler.init(preInitializationEvent.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        /* Items */
        KinetiCraft2Items.init();

        /* Blocks */
        KinetiCraft2Blocks.init();

        /* Tile Entities */
        KinectiCraft2TileEntities.init();

        /* GUI */
        NetworkRegistry.INSTANCE.registerGuiHandler(KinetiCraft2.instance, new KC2GuiHandler());

        LogHelper.info("Pre Init Complete!");
    }
}
