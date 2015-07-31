package com.techmafia.mcmods.KinetiCraft2.handlers;

import com.techmafia.mcmods.KinetiCraft2.KinetiCraft2;
import com.techmafia.mcmods.KinetiCraft2.containers.KineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft2.gui.GuiKineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft2.gui.KC2Gui;
import com.techmafia.mcmods.KinetiCraft2.tileentities.KineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft2.utility.LogHelper;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by myang on 7/30/15.
 */
public class KC2GuiHandler implements IGuiHandler {
    public KC2GuiHandler() {
        NetworkRegistry.INSTANCE.registerGuiHandler(KinetiCraft2.instance, this);
    }

    public Object getServerGuiElement(int ID, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity != null) {
            if (tileEntity instanceof KineticEnergyCubeTileEntity) {
                switch (ID) {
                    case 0:
                        return new KineticEnergyCubeContainer((KineticEnergyCubeTileEntity) tileEntity, entityPlayer);
                }
            }
        } else {
            LogHelper.error("Server: No tile entity found at " + x + "," + y + "," + z + ".");
        }

        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity != null) {
            if (tileEntity instanceof KineticEnergyCubeTileEntity) {
                switch (ID) {
                    case 0:
                        return new GuiKineticEnergyCube(new KineticEnergyCubeContainer((KineticEnergyCubeTileEntity) tileEntity, entityPlayer), (KineticEnergyCubeTileEntity) tileEntity);
                }
            }
        } else {
            LogHelper.error("Client: No tile entity found at " + x + "," + y + "," + z + ".");
        }

        return null;
    }
}
