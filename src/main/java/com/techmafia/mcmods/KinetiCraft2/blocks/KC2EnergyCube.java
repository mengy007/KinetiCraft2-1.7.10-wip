package com.techmafia.mcmods.KinetiCraft2.blocks;

import cofh.core.block.BlockCoFHBase;
import cofh.core.util.CoreUtils;
import com.techmafia.mcmods.KinetiCraft2.creativetab.CreativeTabKC2;
import com.techmafia.mcmods.KinetiCraft2.reference.Reference;
import com.techmafia.mcmods.KinetiCraft2.tileentities.KineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft2.tileentities.base.KC2TileEntityBase;
import com.techmafia.mcmods.KinetiCraft2.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

/**
 * Created by Meng on 7/31/2015.
 */
public class KC2EnergyCube extends BlockCoFHBase {
    public static final int META_KINETIC_ENERGY_CUBE = 0;

    public static final String[] _subBlocks = {
            "kineticEnergyCube"
    };

    private IIcon[] _icons = new IIcon[_subBlocks.length];
    private IIcon[] _activeIcons = new IIcon[_subBlocks.length];

    public KC2EnergyCube(Material material) {
        super(material);

        setStepSound(soundTypeMetal);
        setHardness(0.1f);
        setBlockName("kc2EnergyCube");
        setBlockTextureName(Reference.MOD_NAME + ":kineticEnergyCube");
        this.setCreativeTab(CreativeTabKC2.KC2_TAB);
    }

    public static final int SIDE_FRONT = ForgeDirection.NORTH.ordinal();

    private IIcon safeGetIcon(IIcon[] list, int idx, int x, int y, int z) {
        if(idx < 0 || idx >= list.length) {
            LogHelper.error("Invalid metadata (" + idx + ") for block at " + x + "," + y + "," + z);
            return blockIcon;
        }
        else {
            return list[idx];
        }
    }

    public IIcon getIconFromTileEntity(TileEntity te, int metadata, int side) {
        if(metadata < 0) { return blockIcon; }

        // Tracks the actual index of the current side, after rotation
        int front = -1;

        if(side == front) {
            if(te instanceof KC2TileEntityBase) {
                KC2TileEntityBase beefTe = (KC2TileEntityBase)te;
                if(beefTe.isActive()) {
                    return safeGetIcon(_activeIcons, metadata, te.xCoord, te.yCoord, te.zCoord);
                }
            }
            return safeGetIcon(_icons, metadata, te.xCoord, te.yCoord, te.zCoord);
        }

        return blockIcon;
    }

    @Override
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        TileEntity te = blockAccess.getTileEntity(x, y, z);
        int metadata = blockAccess.getBlockMetadata(x, y, z);
        return this.getIconFromTileEntity(te, metadata, side);
    }

    @Override
    public IIcon getIcon(int side, int metadata)
    {
        // This is used when rendering in-inventory. 4 == front here.
        if(side == 4) {
            return _icons[metadata];
        }
        return this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(Reference.MOD_NAME + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(":") + 1));

        for(int i = 0; i < _subBlocks.length; ++i) {
            _icons[i] = par1IconRegister.registerIcon(Reference.MOD_NAME + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(":") + 1) + "." + _subBlocks[i]);
            _activeIcons[i] = par1IconRegister.registerIcon(Reference.MOD_NAME + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(":") + 1) + "." + _subBlocks[i] + ".active");
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        switch(metadata) {
            case META_KINETIC_ENERGY_CUBE:
                return new KineticEnergyCubeTileEntity();
            default:
                throw new IllegalArgumentException("Unknown metadata for tile entity");
        }
    }

    // IDismantleable
    @Override
    public ArrayList<ItemStack> dismantleBlock(EntityPlayer player, NBTTagCompound blockTag,
                                               World world, int x, int y, int z, boolean returnDrops, boolean simulate) {
        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
        int metadata = world.getBlockMetadata(x, y, z);
        stacks.add(new ItemStack(getItemDropped(metadata, world.rand, 0), 1, damageDropped(metadata)));

        if(returnDrops && !simulate)
        {
            TileEntity te = world.getTileEntity(x, y, z);

            if(te instanceof IInventory) {
                IInventory invTe = (IInventory)te;
                for(int i = 0; i < invTe.getSizeInventory(); i++) {
                    ItemStack stack = invTe.getStackInSlot(i);
                    if(stack != null) {
                        stacks.add(stack);
                        invTe.setInventorySlotContents(i, null);
                    }
                }
            }
        }

        if(!simulate) {
            world.setBlockToAir(x, y, z);

            if(!returnDrops) {
                for(ItemStack stack: stacks) {
                    CoreUtils.dropItemStackIntoWorldWithVelocity(stack, world, x, y, z);
                }
            }
        }

        return stacks;
    }

    // IInitializer (unused)
    @Override
    public boolean initialize() {
        return false;
    }

    @Override
    public boolean postInit() {
        return false;
    }
}
