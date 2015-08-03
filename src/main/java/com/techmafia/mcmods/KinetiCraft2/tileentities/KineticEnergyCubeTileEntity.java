package com.techmafia.mcmods.KinetiCraft2.tileentities;

import com.techmafia.mcmods.KinetiCraft2.containers.KineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft2.gui.GuiKineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft2.items.KineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft2.net.CommonPacketHandler;
import com.techmafia.mcmods.KinetiCraft2.tileentities.base.TileEntityInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Set;

/**
 * Created by Meng on 7/30/2015.
 */
public class KineticEnergyCubeTileEntity extends TileEntityInventory {
    private Set<EntityPlayer> updatePlayers;

    public static final int NUM_SLOTS = 6;

    public KineticEnergyCubeTileEntity() {
        super();
    }

    @Override
    public int getSizeInventory() {
        return NUM_SLOTS;
    }

    @Override
    public String getInventoryName() {
        return "Kinetic Energy Cube";
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        return (itemstack.getItem() instanceof KineticEnergyCore);
    }

    // IKC2GuiEntity
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
        if(this.worldObj.isRemote) { return; }
        if(this.worldObj.playerEntities.size() <= 0) { return; }

        for(EntityPlayer player : (List<EntityPlayer>)this.worldObj.playerEntities) {
            // Send update if player is within a certain range
            if (player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 128D) {
                CommonPacketHandler.INSTANCE.sendTo(getUpdatePacket(), (EntityPlayerMP)player);
            }
        }
    }
}
