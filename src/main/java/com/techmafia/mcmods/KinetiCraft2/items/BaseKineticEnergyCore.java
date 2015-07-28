package com.techmafia.mcmods.KinetiCraft2.items;

import com.techmafia.mcmods.KinetiCraft2.utility.NBTHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;

/**
 * Created by Meng on 7/27/2015.
 */
public class BaseKineticEnergyCore extends KC2Item {
    public int maxEnergy                    = 0;
    protected int energyFromJumping         = 0;
    protected int energyFromUsing           = 0;
    protected int overChargeBuffer          = 0;
    protected int maxExtract                = 0;
    protected IIcon icons[]                 = new IIcon[6];
    protected boolean hasMultipleIcons      = false;
    protected float damageFromOvercharge    = 0;

    public BaseKineticEnergyCore() {
        super();
        this.setMaxStackSize(1);
    }

    public int getMaxExtract() { return this.maxExtract; }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLivingBase, ItemStack itemStack) {
        if (NBTHelper.hasTag(itemStack, "kineticEnergyStored"))
        {
            // Get stored energy level from item stack
            int energyStored = NBTHelper.getInt(itemStack, "kineticEnergyStored");

            // Adds energy from using if max not hit yet
            if (energyStored < this.maxEnergy)
            {
                energyStored += this.energyFromUsing;

                // play sound if max is hit
                if (energyStored >= this.maxEnergy)
                {
                    entityLivingBase.playSound("random.orb", 1, 1);
                }
            }
            else
            {
                // Handle over charging. Note: players can currently only over charge from swinging
                if ( ! NBTHelper.hasTag(itemStack, "overCharge"))
                {
                    NBTHelper.setInteger(itemStack, "overCharge", 0);
                }

                int overCharge = NBTHelper.getInt(itemStack, "overCharge");

                overCharge++;

                if (overCharge >= this.overChargeBuffer)
                {
                    // KaBOOM!
                    ((EntityPlayer)entityLivingBase).destroyCurrentEquippedItem();

                    entityLivingBase.attackEntityFrom(DamageSource.generic, this.damageFromOvercharge);
                    entityLivingBase.playSound("random.explode", 1, 1);
                }

                NBTHelper.setInteger(itemStack, "overCharge", overCharge);
            }

            if (energyStored > this.maxEnergy) { energyStored = this.maxEnergy; }

            // Save new energy level to item stack
            NBTHelper.setInteger(itemStack, "kineticEnergyStored", energyStored);

            // Set item damage
            this.setDamage(itemStack, this.maxEnergy - energyStored);
        }

        return false;
    }
}
