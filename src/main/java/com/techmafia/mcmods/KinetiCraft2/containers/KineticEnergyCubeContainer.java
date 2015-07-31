package com.techmafia.mcmods.KinetiCraft2.containers;

import com.techmafia.mcmods.KinetiCraft2.slots.KineticEnergyCoreSlot;
import com.techmafia.mcmods.KinetiCraft2.tileentities.KineticEnergyCubeTileEntity;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * Created by myang on 7/30/15.
 */
public class KineticEnergyCubeContainer extends KC2Container {
    public KineticEnergyCubeContainer(InventoryPlayer inventoryPlayer, KineticEnergyCubeTileEntity tileEntity, int slotCols, int slotRows) {
        super(inventoryPlayer, tileEntity);

        int currentSlot = 0;

        for (int y = 0; y < slotRows; y++) {
            for (int x = 0; x < slotCols; x++) {
                addSlotToContainer(new KineticEnergyCoreSlot(tileEntity, currentSlot++, 62 + (x * 18), 23 + (y * 18)));
            }
        }

        this.bindPlayerInventory(inventoryPlayer);
    }
}
