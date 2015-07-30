package com.techmafia.mcmods.KinetiCraft2.init;

import com.techmafia.mcmods.KinetiCraft2.blocks.KineticBlock;
import com.techmafia.mcmods.KinetiCraft2.blocks.KineticEnergyCube;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * Created by Meng on 7/27/2015.
 */
public class KinetiCraft2Blocks {
    public static final KineticBlock kineticBlock = new KineticBlock();
    public static final KineticEnergyCube woodenKineticEnergyCube = new KineticEnergyCube("woodenKineticEnergyCube", Material.wood, 0.1f, 1);

    public static void init() {
        /* Register Blocks */
        GameRegistry.registerBlock(kineticBlock, "kineticBlock");
        GameRegistry.registerBlock(woodenKineticEnergyCube, "woodenKineticEnergyCube");

        /* Crafting Recipes */
        GameRegistry.addShapelessRecipe(new ItemStack(kineticBlock, 2), new Object[]{
                Blocks.sand,
                Blocks.dirt
        });
        GameRegistry.addRecipe(new ItemStack(woodenKineticEnergyCube, 1), new Object[]{
                "WKW",
                "KLK",
                "WKW",
                'W', Blocks.planks,
                'L', Blocks.lever,
                'K', KinetiCraft2Items.kineticIngot
        });

        /* Register furnace smelts */
        GameRegistry.addSmelting(kineticBlock, new ItemStack(KinetiCraft2Items.kineticIngot, 1, 0), 0.1f);
    }
}
