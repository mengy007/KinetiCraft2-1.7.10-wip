package com.techmafia.mcmods.KinetiCraft2.blocks;

import com.techmafia.mcmods.KinetiCraft2.init.KinetiCraft2Items;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Meng on 7/27/2015.
 */
public class KineticBlock extends KC2Block {
    public KineticBlock() {
        super(Material.ground);

        this.setHardness(0.5f);
        this.setStepSound(Block.soundTypeGravel);
        this.setBlockName("kineticBlock");
    }

    /**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
    public void onEntityWalking(World world, int x, int y, int z, Entity entity)
    {
        if ( ! world.isRemote && entity instanceof EntityPlayerMP)
        {
            this.makeKineticDust(world, x, y, z, 2);
        }
    }

    /**
     * Block's chance to react to an entity falling on it.
     */
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float speed)
    {
        if ( ! world.isRemote && entity instanceof EntityPlayerMP)
        {
            this.makeKineticDust(world, x, y, z, 2);
        }
    }

    /**
     * This makes simply breaking the block drop 1 dust
     * @param metadata
     * @param random
     * @param fortune
     * @return
     */
    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return KinetiCraft2Items.kineticDust;
    }


    /**
     * Drop some kinetic dust in the world
     * @param world
     * @param x
     * @param y
     * @param z
     * @param amount
     */
    public void makeKineticDust(World world, int x, int y, int z, int amount)
    {
        world.func_147480_a(x, y, z, false); // Destroy block
        world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(KinetiCraft2Items.kineticDust, amount)));
    }
}
