package com.techmafia.mcmods.KinetiCraft2.tileentities;

import net.minecraft.item.ItemStack;

/**
 * Created by Meng on 7/30/2015.
 */
public class KineticEnergyCubeTileEntity extends BaseKineticEnergyCubeTileEntity {
    public KineticEnergyCubeTileEntity() {
        super();

        this.slotCols = 3;
        this.slotRows = 2;
        this.energyCores = new ItemStack[(this.slotCols * this.slotRows)];
    }
}
