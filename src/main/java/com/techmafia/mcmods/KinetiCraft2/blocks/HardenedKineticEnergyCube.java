package com.techmafia.mcmods.KinetiCraft2.blocks;

import com.techmafia.mcmods.KinetiCraft2.tileentities.HardenedKineticEnergyCubeTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Meng on 8/1/2015.
 */
public class HardenedKineticEnergyCube extends KineticEnergyCube {
    public HardenedKineticEnergyCube(String blockName, Material material, float blockHardness, int maxCoreCols, int maxCoreRows, int guiId) {
        super(blockName, material, blockHardness, maxCoreCols, maxCoreRows, guiId);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new HardenedKineticEnergyCubeTileEntity();
    }

}
