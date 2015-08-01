package com.techmafia.mcmods.KinetiCraft2.items;

import com.techmafia.mcmods.KinetiCraft2.reference.Reference;
import com.techmafia.mcmods.KinetiCraft2.utility.LogHelper;
import com.techmafia.mcmods.KinetiCraft2.utility.NBTHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ibxm.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Meng on 7/27/2015.
 */
public class KineticEnergyCore extends KC2Item {
    public int maxEnergy                        = 0;
    protected int energyFromJumping             = 0;
    protected int energyFromMoving              = 0;
    protected int energyFromUsing               = 0;
    protected int overChargeBuffer              = 0;
    protected int maxExtract                    = 0;
    protected float prevDistanceWalkedModified  = 0;
    protected IIcon icons[]                     = new IIcon[6];
    protected boolean hasMultipleIcons          = false;
    protected float damageFromOvercharge        = 0;

    public KineticEnergyCore(String itemName, int energyFromJumping, int energyFromMoving, int energyFromUsing, int overChargeBuffer, int maxEnergy, int maxExtract, float damageFromOvercharge) {
        super(itemName, 64, true);

        this.energyFromJumping      = energyFromJumping;
        this.energyFromMoving       = energyFromMoving;
        this.energyFromUsing        = energyFromUsing;
        this.overChargeBuffer       = overChargeBuffer;
        this.maxEnergy              = maxEnergy;
        this.maxExtract             = maxExtract;
        this.damageFromOvercharge   = damageFromOvercharge;
        this.hasMultipleIcons       = true;

        //this.setMaxDamage(this.maxEnergy);
    }

    public int getMaxExtract() { return this.maxExtract; }

    @Override
    //@SideOnly(Side.SERVER)
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
            //this.setDamage(itemStack, this.maxEnergy - energyStored);
        } else {
            // First swing!
            NBTHelper.setInteger(itemStack, "kineticEnergyStored", this.energyFromUsing);
        }

        return false;
    }

    /**
     * Used to track player movement and jumping
     */
    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean inPlayerInv)
    {
        super.onUpdate(itemStack, world, entity, par4, inPlayerInv);

        EntityPlayer ep = (EntityPlayer)entity;

        if (prevDistanceWalkedModified == 0) {
            prevDistanceWalkedModified = ep.distanceWalkedModified;
        }

        boolean isMoving = ep.getAIMoveSpeed() > 0.11f ? true : false;
        boolean isJumping = (ep.fallDistance > 0.0f) ? true : false;
        boolean energyGained = false;
        int energyStored = getEnergyStored(itemStack);

        if ( ! world.isRemote)
        {
            if (energyStored <= maxEnergy)
            {
                if (isMoving)
                {
                    energyStored += energyFromMoving;
                    energyGained = true;
                }

                if (isJumping)
                {
                    energyStored += energyFromJumping;
                    energyGained = true;
                }

                if (energyGained)
                {
                    if (energyStored > this.maxEnergy) { energyStored = this.maxEnergy; }

                    NBTHelper.setInteger(itemStack, "kineticEnergyStored", energyStored);

                    // play sound if max is hit
                    if (energyStored >= this.maxEnergy)
                    {
                        entity.playSound("random.orb", 1, 1);
                    }
                }
            }
            else
            {
                if (energyStored > this.maxEnergy)
                {
                    energyStored = this.maxEnergy;
                    NBTHelper.setInteger(itemStack, "kineticEnergyStored", energyStored);
                }
            }
        }

        prevDistanceWalkedModified = ep.distanceWalkedModified;

        //this.setDamage(itemStack, this.maxEnergy - energyStored);

        // Set icon based on energy stored.
        if (this.hasMultipleIcons) {
            int level = (getEnergyStored(itemStack)) / (this.maxEnergy / 5);
            // this.itemIcon = level > 5 ? this.icons[5] : level < 0 ? this.icons[0] : this.icons[level];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        if (this.hasMultipleIcons)
        {
            for (int i = 0; i < 6; i++)
            {
                this.icons[i] = iconRegister.registerIcon(Reference.MOD_NAME + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(":") + 1) + i);
            }
            this.itemIcon = icons[0];
        }
        else
        {
            this.itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(ItemStack itemStack, int pass) {
        if (this.hasMultipleIcons)
        {
            int level = getEnergyStored(itemStack) / (this.maxEnergy / 6);
            int iconIndex = level > 5 ? 5 : level < 0 ? 0 : level;

            this.itemIcon = this.icons[iconIndex];

            return this.icons[iconIndex];
        }
        else
        {
            return this.itemIcon;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(ItemStack itemStack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (this.hasMultipleIcons)
        {
            int level = getEnergyStored(itemStack) / (this.maxEnergy / 6);
            int iconIndex = level > 5 ? 5 : level < 0 ? 0 : level;

            return this.icons[iconIndex];
        }
        else
        {
            return this.itemIcon;
        }
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
    {
        super.addInformation(itemStack, player, list, par4);

        int energyStored = getEnergyStored(itemStack);
        list.add(EnumChatFormatting.GREEN + "" + energyStored + " / " + this.maxEnergy + " RF");
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!world.isRemote) {
            int energyStored = getEnergyStored(itemStack);
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "" + energyStored + " / " + this.maxEnergy + " RF"));
        }

        return itemStack;
    }

    public static int getEnergyStored(ItemStack itemStack) {
        if (itemStack.stackTagCompound != null) {
            return itemStack.stackTagCompound.getInteger("kineticEnergyStored");
        } else {
            itemStack.stackTagCompound = new NBTTagCompound();

            return 0;
        }
    }
}
