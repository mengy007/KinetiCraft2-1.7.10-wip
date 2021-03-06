package com.techmafia.mcmods.KinetiCraft2.tileentities.base;

import com.techmafia.mcmods.KinetiCraft2.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Meng on 7/30/2015.
 */
public abstract class TileEntityInventory extends KC2TileEntityBase implements IInventory {
    // Inventory
    protected ItemStack[] _inventories;
    protected int[][] invSlotExposures;

    // Direction
    // 2, 5, 3, 4
    protected int frontFace; // 0:NORTH, 1:EAST, 2:SOUTH, 3:WEST

    public TileEntityInventory() {
        super();

        _inventories = new ItemStack[getSizeInventory()];
        invSlotExposures = new int[getSizeInventory()][1];
        for(int i = 0; i < invSlotExposures.length; i++) {
            // Set up a cached array with all possible exposed inventory slots, so we don't have to alloc at runtime
            invSlotExposures[i][0] = i;
        }
    }

    public void setFrontFace(int dir) {
        frontFace = dir;
    }

    public int getFrontFace() {
        return frontFace;
    }

    public void dropInventory(World world, int x, int y, int z) {
        if (!world.isRemote) {
            for (ItemStack itemStack : _inventories) {
                if (itemStack != null) {
                    world.spawnEntityInWorld(new EntityItem(world, x, y, z, itemStack.copy()));
                }
            }
        }
    }

    @Override
    public void onNeighborBlockChange() {
        super.onNeighborBlockChange();
    }

    @Override
    public void onNeighborTileChange(int x, int y, int z) {
        super.onNeighborTileChange(x, y, z);
    }

    // TileEntity overrides
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        // Inventories
        _inventories = new ItemStack[getSizeInventory()];
        if(tag.hasKey("Items")) {
            NBTTagList tagList = tag.getTagList("Items", 10);
            for(int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound itemTag = (NBTTagCompound)tagList.getCompoundTagAt(i);
                int slot = itemTag.getByte("Slot") & 0xff;
                if(slot >= 0 && slot <= _inventories.length) {
                    ItemStack itemStack = new ItemStack((Block)null,0,0);
                    itemStack.readFromNBT(itemTag);
                    _inventories[slot] = itemStack;
                }
            }
        }

        // Front face
        if(tag.hasKey("FrontFace")) {
            frontFace = tag.getInteger("FrontFace");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        // Inventories
        NBTTagList tagList = new NBTTagList();
        for(int i = 0; i < _inventories.length; i++) {
            if((_inventories[i]) != null) {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte)i);
                _inventories[i].writeToNBT(itemTag);
                tagList.appendTag(itemTag);
            }
        }

        if(tagList.tagCount() > 0) {
            tag.setTag("Items", tagList);
        }

        // Front Face
        tag.setInteger("FrontFace", frontFace);
    }



    /**
     * Fill this NBT Tag Compound with your custom entity data.
     * @param updateTag The tag to which your data should be written
     */
    @Override
    protected void onSendUpdate(NBTTagCompound updateTag) {
        updateTag.setInteger("FrontFace", frontFace);
    }

    /**
     * Read your custom update data from this NBT Tag Compound.
     * @param updateTag The tag which should contain your data.
     */
    @Override
    public void onReceiveUpdate(NBTTagCompound updateTag) {
        if (updateTag.hasKey("FrontFace")) {
            frontFace = updateTag.getInteger("FrontFace");
        }
    }



    @Override
    public boolean isActive() {
        return true;
    }

    // IInventory
    @Override
    public abstract int getSizeInventory();

    @Override
    public ItemStack getStackInSlot(int slot) {
        return _inventories[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if(_inventories[slot] != null)
        {
            if(_inventories[slot].stackSize <= amount)
            {
                ItemStack itemstack = _inventories[slot];
                _inventories[slot] = null;
                return itemstack;
            }
            ItemStack newStack = _inventories[slot].splitStack(amount);
            if(_inventories[slot].stackSize == 0)
            {
                _inventories[slot] = null;
            }
            return newStack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack) {
        LogHelper.info("Setting slot: " + slot);

        _inventories[slot] = itemstack;
        if(itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public abstract String getInventoryName();

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        if(worldObj.getTileEntity(xCoord, yCoord, zCoord) != this)
        {
            return false;
        }
        return entityplayer.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public abstract boolean isItemValidForSlot(int slot, ItemStack itemstack);

    // IItemDuctConnection
    public boolean canConduitConnect(ForgeDirection from) {
        return from != ForgeDirection.UNKNOWN;
    }
}
