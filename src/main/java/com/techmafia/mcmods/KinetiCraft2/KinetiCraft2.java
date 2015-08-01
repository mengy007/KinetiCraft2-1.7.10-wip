package com.techmafia.mcmods.KinetiCraft2;

import com.techmafia.mcmods.KinetiCraft2.handlers.ConfigurationHandler;
import com.techmafia.mcmods.KinetiCraft2.handlers.KC2GuiHandler;
import com.techmafia.mcmods.KinetiCraft2.init.KinectiCraft2TileEntities;
import com.techmafia.mcmods.KinetiCraft2.init.KinetiCraft2Blocks;
import com.techmafia.mcmods.KinetiCraft2.init.KinetiCraft2Items;
import com.techmafia.mcmods.KinetiCraft2.net.CommonPacketHandler;
import com.techmafia.mcmods.KinetiCraft2.proxy.CommonProxy;
import com.techmafia.mcmods.KinetiCraft2.reference.Reference;
import com.techmafia.mcmods.KinetiCraft2.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)

public class KinetiCraft2
{
    @Mod.Instance(Reference.MOD_ID)
    public static KinetiCraft2 instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent preInitializationEvent) {
        /* Config */
        ConfigurationHandler.init(preInitializationEvent.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        /* proxy */
        proxy.preInit();

        proxy.registerClientStuff();

        LogHelper.info("Pre Init Complete!");
    }
}
