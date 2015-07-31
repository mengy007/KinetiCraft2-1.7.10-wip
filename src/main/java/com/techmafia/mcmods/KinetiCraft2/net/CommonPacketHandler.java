package com.techmafia.mcmods.KinetiCraft2.net;

import com.techmafia.mcmods.KinetiCraft2.net.messages.DeviceUpdateMessage;
import com.techmafia.mcmods.KinetiCraft2.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * Created by Meng on 7/30/2015.
 */
public class CommonPacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.CHANNEL.toLowerCase());

    /**
     * Initialize the messages. Note that all messages (server>client and client>server)
     * must be initialized on _both_ the client and the server.
     */
    // Be careful not to reference any client code in your message handlers, such as WorldClient!
    public static void init() {
        // Server >> Client Messages
        INSTANCE.registerMessage(DeviceUpdateMessage.Handler.class, DeviceUpdateMessage.class, 1, Side.CLIENT);
    }
}
