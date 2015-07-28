package com.techmafia.mcmods.KinetiCraft2.blocks;

import net.minecraft.block.material.Material;

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
}
