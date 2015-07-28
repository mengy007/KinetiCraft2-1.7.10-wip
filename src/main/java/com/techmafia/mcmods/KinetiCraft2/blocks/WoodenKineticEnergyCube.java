package com.techmafia.mcmods.KinetiCraft2.blocks;

import com.techmafia.mcmods.KinetiCraft2.tileentities.WoodenKineticEnergyCubeTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Meng on 7/27/2015.
 */
public class WoodenKineticEnergyCube extends BaseKineticEnergyCube {
    public WoodenKineticEnergyCube (){
        super(Material.wood);

        this.setHardness(0.1f);
        this.setBlockName("woodenKineticEnergyCube");
        this.maxCores = 1;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new WoodenKineticEnergyCubeTileEntity();
    }
}
