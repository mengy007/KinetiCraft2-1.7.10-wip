package com.techmafia.mcmods.KinetiCraft2.proxy;

import com.techmafia.mcmods.KinetiCraft2.KinetiCraft2;
import com.techmafia.mcmods.KinetiCraft2.handlers.ConfigurationHandler;
import com.techmafia.mcmods.KinetiCraft2.handlers.KC2GuiHandler;
import com.techmafia.mcmods.KinetiCraft2.init.KinectiCraft2TileEntities;
import com.techmafia.mcmods.KinetiCraft2.init.KinetiCraft2Blocks;
import com.techmafia.mcmods.KinetiCraft2.init.KinetiCraft2Items;
import com.techmafia.mcmods.KinetiCraft2.net.CommonPacketHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.TextureStitchEvent;

/**
 * Created by Meng on 7/31/2015.
 */
public class CommonProxy {
    public void preInit() {
        /* Network stuff */
        CommonPacketHandler.init();

        /* Items */
        KinetiCraft2Items.init();

        /* Blocks */
        KinetiCraft2Blocks.init();

        /* Tile Entities */
        KinectiCraft2TileEntities.init();

        /* GUI */
        NetworkRegistry.INSTANCE.registerGuiHandler(KinetiCraft2.instance, new KC2GuiHandler());
    }

    public void registerClientStuff() {}
}
