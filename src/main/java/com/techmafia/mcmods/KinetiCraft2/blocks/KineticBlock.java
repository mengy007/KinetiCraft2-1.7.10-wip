package com.techmafia.mcmods.KinetiCraft2.blocks;

import com.techmafia.mcmods.KinetiCraft2.init.KinetiCraft2Items;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Meng on 7/27/2015.
 */
public class KineticBlock extends KC2Block {
    public KineticBlock() {
        super(Material.sand);

        this.setHardness(0.5f);
        this.setBlockName("kineticBlock");
    }

    /**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
    public void onEntityWalking(World world, int x, int y, int z, Entity entity)
    {
        if ( ! world.isRemote && entity instanceof EntityPlayerMP)
        {
            this.makeKineticDust(world, x, y, z, entity);
        }
    }

    /**
     * Block's chance to react to an entity falling on it.
     */
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float speed)
    {
        if ( ! world.isRemote && entity instanceof EntityPlayerMP)
        {
            this.makeKineticDust(world, x, y, z, entity);
        }
    }

    public void makeKineticDust(World world, int x, int y, int z, Entity entity)
    {
        world.func_147480_a(x, y, z, false); // Destroy block
        world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(KinetiCraft2Items.kineticDust, 2)));
    }
}
