package com.techmafia.mcmods.KinetiCraft2.containers;

import com.techmafia.mcmods.KinetiCraft2.slots.KineticEnergyCoreSlot;
import com.techmafia.mcmods.KinetiCraft2.tileentities.KineticEnergyCubeTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by myang on 7/30/15.
 */
public class KineticEnergyCubeContainer extends Container {
    protected KineticEnergyCubeTileEntity _entity;

    public KineticEnergyCubeContainer(KineticEnergyCubeTileEntity entity, EntityPlayer player) {
        super();
        _entity = entity;
        addSlots();
        bindPlayerInventory(player.inventory);
        _entity.beginUpdatingPlayer(player);
    }

    protected void addSlots() {
        int currentSlot = 0;

        for (int y = 0; y < 2; y++) {
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
