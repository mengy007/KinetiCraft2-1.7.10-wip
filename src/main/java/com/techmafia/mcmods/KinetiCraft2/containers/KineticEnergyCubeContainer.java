package com.techmafia.mcmods.KinetiCraft2.containers;

import com.techmafia.mcmods.KinetiCraft2.slots.KineticEnergyCoreSlot;
import com.techmafia.mcmods.KinetiCraft2.tileentities.KineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft2.tileentities.base.EnergyCubeTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by myang on 7/30/15.
 */
public class KineticEnergyCubeContainer extends Container {
    protected EnergyCubeTileEntity _entity;
    protected int slots;

    public KineticEnergyCubeContainer(EnergyCubeTileEntity entity, EntityPlayer player, int slots) {
        super();
        _entity = entity;
        this.slots = slots;
        addSlots();
        bindPlayerInventory(player.inventory);
        _entity.beginUpdatingPlayer(player);
    }

    protected void addSlots() {
        int currentSlot = 0;
        int rows = slots / 3;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < 3; x++) {
                addSlotToContainer(new KineticEnergyCoreSlot(_entity, currentSlot++, 62 + (x * 18), 23 + (y * 18)));
            }
        }
    }

    protected int getPlayerInventoryVerticalOffset() {
        return 84;
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, getPlayerInventoryVerticalOffset() + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, getPlayerInventoryVerticalOffset() + 58));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);
        int numSlots = _entity.getSizeInventory();

        if(slotObject != null && slotObject.getHasStack())
        {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            if(slot < numSlots)
            {
                if(!mergeItemStack(stackInSlot, numSlots, inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if(!mergeItemStack(stackInSlot, 0, numSlots, false))
            {
                return null;
            }

            if(stackInSlot.stackSize == 0)
            {
                slotObject.putStack(null);
            }
            else
            {
                slotObject.onSlotChanged();
            }

            if(stackInSlot.stackSize == stack.stackSize)
            {
                return null;
            }

            slotObject.onPickupFromSlot(player, stackInSlot);
        }

        return stack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return _entity.isUseableByPlayer(player);
    }

    // Update subscription
    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);

        _entity.stopUpdatingPlayer(player);
    }
}
