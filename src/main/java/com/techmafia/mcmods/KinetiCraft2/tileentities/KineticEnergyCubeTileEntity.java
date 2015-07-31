package com.techmafia.mcmods.KinetiCraft2.tileentities;

import com.techmafia.mcmods.KinetiCraft2.items.KineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft2.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

/**
 * Created by Meng on 7/28/2015.
 */
public class KineticEnergyCubeTileEntity extends KC2TileEntity implements IInventory {
    public int slotCols, slotRows;

    protected ItemStack[] energyCores;

    public KineticEnergyCubeTileEntity() {
        super();
    }

    public KineticEnergyCubeTileEntity(int slotCols, int slotRows) {
        super();
        this.slotCols = slotCols;
        this.slotRows = slotRows;
        energyCores = new ItemStack[(slotCols * slotRows)];
    }

    @Override
    public int getSizeInventory() {
        return this.energyCores.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot < this.energyCores.length) {
            return this.energyCores[slot];
        }

        return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int j) {
        if (slot < this.energyCores.length && this.energyCores[slot] != null) {
            ItemStack itemStack = this.energyCores[slot];
            this.energyCores[slot] = null;

            return itemStack;
        }

        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {

        LogHelper.info("Setting slot: " + slot);

        if (slot < this.energyCores.length && this.energyCores[slot] == null) {
            this.energyCores[slot] = itemStack;

            /*
            if (itemStack.stackSize > this.getInventoryStackLimit()) {
                int overStack = itemStack.stackSize - this.getSizeInventory();
                this.energyCores[slot].stackSize = this.getInventoryStackLimit();
                itemStack.stackSize = overStack;
            }
            */
        }

        markForUpdate();
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    /**
     * Only allow instances of Kinetic Energy Cores
     * @param slot
     * @param itemStack
     * @return
     */
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return (itemStack.getItem() instanceof KineticEnergyCore) ? true : false;
    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void openInventory() { }

    @Override
    public void closeInventory() {
        markForUpdate();
    }

    @Override
    public void updateEntity() {
        //LogHelper.info("UPDATE!");
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
    }

    public void markForUpdate() {
        LogHelper.info("Marked for update!");
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
}