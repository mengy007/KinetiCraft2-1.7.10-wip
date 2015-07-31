package com.techmafia.mcmods.KinetiCraft2.slots;

import com.techmafia.mcmods.KinetiCraft2.items.KineticEnergyCore;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by myang on 7/30/15.
 */
public class KineticEnergyCoreSlot extends Slot {
    public KineticEnergyCoreSlot(IInventory inventory, int par2, int par3, int par4) {
        super(inventory, par2, par3, par4);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return itemStack.getItem() instanceof KineticEnergyCore;
    }
}
