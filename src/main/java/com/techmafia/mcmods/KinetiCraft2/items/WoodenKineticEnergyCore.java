package com.techmafia.mcmods.KinetiCraft2.items;

/**
 * Created by Meng on 7/27/2015.
 */
public class WoodenKineticEnergyCore extends BaseKineticEnergyCore {
    public WoodenKineticEnergyCore() {
        super();

        this.setUnlocalizedName("woodenKineticEnergyCore");

        this.energyFromJumping      = 1;
        this.energyFromUsing        = 4;
        this.overChargeBuffer       = 2;
        this.maxEnergy              = 1000;
        this.hasMultipleIcons       = true;
        this.damageFromOvercharge   = 1.0f;
        this.maxExtract             = 5;

        this.setMaxDamage(this.maxEnergy);
    }
}
