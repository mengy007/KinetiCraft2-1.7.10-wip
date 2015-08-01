package com.techmafia.mcmods.KinetiCraft2.blocks;

import com.techmafia.mcmods.KinetiCraft2.KinetiCraft2;
import com.techmafia.mcmods.KinetiCraft2.reference.Reference;
import com.techmafia.mcmods.KinetiCraft2.tileentities.KineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft2.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Meng on 7/27/2015.
 */
public class KineticEnergyCube extends KC2Block {
    protected int maxCoreCols, maxCoreRows;
    protected int maxCores = 0;
    protected IIcon frontIcon;
    protected int guiId;

    public KineticEnergyCube(String blockName, Material material, float blockHardness, int maxCoreCols, int maxCoreRows, int guiId) {
        super(material);

        this.setBlockName(blockName);
        this.setHardness(blockHardness);
        this.maxCoreCols = maxCoreCols;
        this.maxCoreRows = maxCoreRows;
        this.maxCores = maxCoreCols * maxCoreRows;
        this.guiId = guiId;
    }

    public int getRenderType() {
        return 0;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * Registers the block's icons
     * @param iconRegister
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(Reference.MOD_NAME + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(":") + 1));
        this.frontIcon = iconRegister.registerIcon(Reference.MOD_NAME + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(":") + 1) + "Front");
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        if (metadata > 0)
        {
            return side == 1 ? this.blockIcon : (side == 0 ? this.blockIcon : (side != metadata ? this.blockIcon : this.frontIcon));
        }
        else
        {
            return side == 1 ? this.blockIcon : (side == 0 ? this.blockIcon : (side != 3 ? this.blockIcon : this.frontIcon));
        }
    }

    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
    {
        int l = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        KineticEnergyCubeTileEntity tileEntity = (KineticEnergyCubeTileEntity)world.getTileEntity(x, y, z);

        tileEntity.setFrontFace(l);

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new KineticEnergyCubeTileEntity();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9) {
        TileEntity te = world.getTileEntity(x, y, z);

        entityPlayer.openGui(KinetiCraft2.instance, this.guiId, world, x, y, z);

        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        // Drop inventory
        if (tileEntity != null && tileEntity instanceof KineticEnergyCubeTileEntity) {
            ((KineticEnergyCubeTileEntity)tileEntity).dropInventory(world, x, y, z);
            world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(tileEntity.blockType, 1)));
            world.removeTileEntity(x, y, z);
        }
    }
}
