package com.techmafia.mcmods.KinetiCraft2.init;

import com.techmafia.mcmods.KinetiCraft2.items.KC2Item;
import com.techmafia.mcmods.KinetiCraft2.items.KineticEnergyCore;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by Meng on 7/27/2015.
 */
public class KinetiCraft2Items {
    public static final KC2Item kineticDust                         = new KC2Item("kineticDust", 64, false);
    public static final KC2Item kineticIngot                        = new KC2Item("kineticIngot", 64, false);
    public static final KC2Item kineticGear                         = new KC2Item("kineticGear", 64, false);
    public static final KC2Item kineticFrame                        = new KC2Item("kineticFrame", 64, false);
    public static final KineticEnergyCore woodenKineticEnergyCore   = new KineticEnergyCore("woodenKineticEnergyCore",  1,      1,      4,      2,  1000,   5,      1.0f);
    public static final KineticEnergyCore stoneKineticEnergyCore    = new KineticEnergyCore("stoneKineticEnergyCore",   5,      10,     20,     4,  10000,  20,     1.5f);
    public static final KineticEnergyCore ironKineticEnergyCore     = new KineticEnergyCore("ironKineticEnergyCore",    25,     50,     100,    8,  50000,  100,    2.0f);
    public static final KineticEnergyCore goldKineticEnergyCore     = new KineticEnergyCore("goldKineticEnergyCore",    100,    200,    500,    10, 100000, 500,    4.0f);
    public static final KineticEnergyCore diamondKineticEnergyCore  = new KineticEnergyCore("diamondKineticEnergyCore", 500,    200,    500,    50, 500000, 2500,   6.0f);
    public static final KineticEnergyCore enderKineticEnergyCore    = new KineticEnergyCore("enderKineticEnergyCore",   1000,   500,   1000,    99, 500000, 10000,  6.0f);

    public static void init() {
        /* Register Items */
        GameRegistry.registerItem(kineticDust, "kineticDust");
        GameRegistry.registerItem(kineticIngot, "kineticIngot");
        GameRegistry.registerItem(kineticGear, "kineticGear");
        GameRegistry.registerItem(kineticFrame, "kineticFrame");
        GameRegistry.registerItem(woodenKineticEnergyCore, "woodenKineticEnergyCore");
        GameRegistry.registerItem(stoneKineticEnergyCore, "stoneKineticEnergyCore");
        GameRegistry.registerItem(ironKineticEnergyCore, "ironKineticEnergyCore");
        GameRegistry.registerItem(goldKineticEnergyCore, "goldKineticEnergyCore");
        GameRegistry.registerItem(diamondKineticEnergyCore, "diamondKineticEnergyCore");
        GameRegistry.registerItem(enderKineticEnergyCore, "enderKineticEnergyCore");

        /* Empty Energy Cores */
        ItemStack woodenCoreEmpty = new ItemStack(woodenKineticEnergyCore, 1);
        ItemStack stoneCoreEmpty = new ItemStack(stoneKineticEnergyCore, 1);
        ItemStack ironCoreEmpty = new ItemStack(ironKineticEnergyCore, 1);
        ItemStack goldCoreEmpty = new ItemStack(goldKineticEnergyCore, 1);
        ItemStack diamondCoreEmpty = new ItemStack(diamondKineticEnergyCore, 1);
        ItemStack enderCoreEmpty = new ItemStack(enderKineticEnergyCore, 1);

        woodenCoreEmpty.setItemDamage(woodenCoreEmpty.getMaxDamage());
        stoneCoreEmpty.setItemDamage(stoneCoreEmpty.getMaxDamage());
        ironCoreEmpty.setItemDamage(ironCoreEmpty.getMaxDamage());
        goldCoreEmpty.setItemDamage(goldCoreEmpty.getMaxDamage());
        diamondCoreEmpty.setItemDamage(diamondCoreEmpty.getMaxDamage());
        enderCoreEmpty.setItemDamage(enderCoreEmpty.getMaxDamage());

        /* Register furnace smelts */
        GameRegistry.addSmelting(kineticDust, new ItemStack(kineticIngot, 1, 0), 0.1f);

        /* Register Item Recipes */
        GameRegistry.addRecipe(new ItemStack(kineticGear, 1), new Object[]{
                " K ",
                "KKK",
                " K ",
                'K', KinetiCraft2Items.kineticIngot
        });
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(kineticFrame, 1), new Object[]{
                "CKC",
                "K K",
                "CKC",
                'K', KinetiCraft2Items.kineticIngot,
                'C', Blocks.cobblestone
        }));
        GameRegistry.addRecipe(woodenCoreEmpty, new Object[]{
                "WWW",
                "WGW",
                "WWW",
                'W', Blocks.planks,
                'G', kineticGear
        });
        GameRegistry.addRecipe(stoneCoreEmpty, new Object[]{
                "SSS",
                "SGS",
                "SSS",
                'S', Blocks.stone,
                'G', kineticGear
        });
        GameRegistry.addRecipe(ironCoreEmpty, new Object[]{
                "III",
                "IGI",
                "III",
                'I', Items.iron_ingot,
                'G', kineticGear
        });
        GameRegistry.addRecipe(goldCoreEmpty, new Object[]{
                "GGG",
                "RKR",
                "GGG",
                'G', Items.gold_ingot,
                'R', Items.redstone,
                'K', kineticGear
        });
        GameRegistry.addRecipe(diamondCoreEmpty, new Object[]{
                "DRD",
                "RGR",
                "DRD",
                'D', Items.diamond,
                'R', Items.redstone,
                'G', kineticGear
        });
        GameRegistry.addRecipe(enderCoreEmpty, new Object[]{
                "ERE",
                "RGR",
                "ERE",
                'E', Items.ender_pearl,
                'R', Items.redstone,
                'G', kineticGear
        });
    }
}