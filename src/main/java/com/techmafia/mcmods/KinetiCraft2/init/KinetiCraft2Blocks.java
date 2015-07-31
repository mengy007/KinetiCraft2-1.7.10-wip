package com.techmafia.mcmods.KinetiCraft2.init;

import cofh.thermalfoundation.item.TFItems;
import com.techmafia.mcmods.KinetiCraft2.blocks.KC2Block;
import com.techmafia.mcmods.KinetiCraft2.blocks.KC2EnergyCube;
import com.techmafia.mcmods.KinetiCraft2.blocks.KineticBlock;
import com.techmafia.mcmods.KinetiCraft2.blocks.KineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft2.items.ItemBlockKC2;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by Meng on 7/27/2015.
 */
public class KinetiCraft2Blocks {
    public static final KineticBlock kineticBlock = new KineticBlock();
    public static final KineticEnergyCube kineticEnergyCube = new KineticEnergyCube("kineticEnergyCube", Material.rock, 0.1f, 3, 1, 0);
    public static final KineticEnergyCube hardenedKineticEnergyCube = new KineticEnergyCube("hardenedKineticEnergyCube", Material.rock, 0.2f, 3, 2, 1);
    public static final KineticEnergyCube reinforcedKineticEnergyCube = new KineticEnergyCube("reinforcedKineticEnergyCube", Material.rock, 0.3f, 3, 3, 2);
    public static final KineticEnergyCube resonantKineticEnergyCube = new KineticEnergyCube("resonantKineticEnergyCube", Material.rock, 0.5f, 3, 5, 3);

    //public static Block testBlock = new KC2EnergyCube(Material.rock);

    public static void init() {
        /* Register Blocks */
        GameRegistry.registerBlock(kineticBlock, "kineticBlock");
        GameRegistry.registerBlock(kineticEnergyCube, "kineticEnergyCube");
        GameRegistry.registerBlock(hardenedKineticEnergyCube, "hardenedKineticEnergyCube");
        GameRegistry.registerBlock(reinforcedKineticEnergyCube, "reinforcedKineticEnergyCube");
        GameRegistry.registerBlock(resonantKineticEnergyCube, "resonantKineticEnergyCube");

        //GameRegistry.registerBlock(testBlock, ItemBlockKC2.class, "testBlock");

        /* Crafting Recipes */
        GameRegistry.addShapelessRecipe(new ItemStack(kineticBlock, 2), new Object[]{
                Blocks.sand,
                Blocks.dirt
        });
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(kineticEnergyCube, 1), new Object[]{
                "CGC",
                " M ",
                "C C",
                'C', "ingotCopper",
                'M', KinetiCraft2Items.kineticFrame,
                'G', KinetiCraft2Items.kineticGear
        }));

        /* Register furnace smelts */
        GameRegistry.addSmelting(kineticBlock, new ItemStack(KinetiCraft2Items.kineticIngot, 1, 0), 0.1f);
    }
}
