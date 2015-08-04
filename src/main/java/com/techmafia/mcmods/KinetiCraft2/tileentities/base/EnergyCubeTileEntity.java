package com.techmafia.mcmods.KinetiCraft2.tileentities.base;

import com.techmafia.mcmods.KinetiCraft2.containers.KineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft2.gui.GuiKineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft2.items.KineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft2.net.CommonPacketHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

import java.util.Set;

/**
 * Created by Meng on 8/3/2015.
 */
public class EnergyCubeTileEntity extends TileEntityInventory {
    private Set<EntityPlayer> updatePlayers;

    public static int NUM_SLOTS = 0;

    public EnergyCubeTileEntity() {
        super();
    }

    @Override
    public int getSizeInventory() {
        return NUM_SLOTS;
    }

    @Override
    public String getInventoryName() {
        return "Energy Cube Base";
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return (itemStack.getItem() instanceof KineticEnergyCore);
    }

    // IKCGuiEntity
    @SideOnly(Side.CLIENT)
    @Override
    public GuiScreen getGUI(EntityPlayer player) {
        return new GuiKineticEnergyCube(getContainer(player), this);
    }

    @Override
    public Container getContainer(EntityPlayer player) {
        return new KineticEnergyCubeContainer(this, player, NUM_SLOTS);
    }

    private void sendUpdatePacket() {
        if (this.worldObj.isRemote) { return; }
        if (this.updatePlayers.size() <= 0) { return; }

        for (EntityPlayer player : updatePlayers) {
            CommonPacketHandler.INSTANCE.sendTo(getUpdatePacket(), (EntityPlayerMP)player);
        }
    }
}
