package com.techmafia.mcmods.KinetiCraft2.tileentities;

import com.techmafia.mcmods.KinetiCraft2.containers.KineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft2.gui.GuiKineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft2.gui.KC2Gui;
import com.techmafia.mcmods.KinetiCraft2.items.KineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft2.tileentities.base.TileEntityInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

/**
 * Created by Meng on 7/30/2015.
 */
public class KineticEnergyCubeTileEntity extends TileEntityInventory {
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
    //@Override
    public GuiScreen getGUI(EntityPlayer player) {
        return new GuiKineticEnergyCube(getContainer(player), this);
    }

    //@Override
    public Container getContainer(EntityPlayer player) {
        return new KineticEnergyCubeContainer(this, player);
    }
}
