package com.techmafia.mcmods.KinetiCraft2.init;

import com.techmafia.mcmods.KinetiCraft2.items.KineticDust;
import com.techmafia.mcmods.KinetiCraft2.items.KineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft2.items.KineticIngot;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Meng on 7/27/2015.
 */
public class KinetiCraft2Items {
    public static final KineticDust kineticDust                         = new KineticDust();
    public static final KineticIngot kineticIngot                       = new KineticIngot();
    public static final KineticEnergyCore woodenKineticEnergyCore       = new KineticEnergyCore("woodenKineticEnergyCore",  1,  1,  4,  2,  1000,   5,  1.0f);
    public static final KineticEnergyCore stoneKineticEnergyCore        = new KineticEnergyCore("stoneKineticEnergyCore",   5,  10, 20, 4,  10000,  20, 1.5f);

    public static void init() {
        /* Register Items */
        GameRegistry.registerItem(kineticDust, "kineticDust");
        GameRegistry.registerItem(kineticIngot, "kinecticIngot");
        GameRegistry.registerItem(woodenKineticEnergyCore, "woodenKineticEnergyCore");
        GameRegistry.registerItem(stoneKineticEnergyCore, "stoneKineticEnergyCore");

        /* Empty Energy Cores */
        ItemStack woodenCoreEmpty = new ItemStack(woodenKineticEnergyCore, 1);
        ItemStack stoneCoreEmpty = new ItemStack(stoneKineticEnergyCore, 1);

        woodenCoreEmpty.setItemDamage(woodenCoreEmpty.getMaxDamage());

        /* Register furnace smelts */
        GameRegistry.addSmelting(kineticDust, new ItemStack(kineticIngot, 1, 0), 0.1f);

        /* Register Item Recipes */
        GameRegistry.addRecipe(woodenCoreEmpty, new Object[]{
                " W ",
                "WRW",
                "KWK",
                'W', Blocks.planks,
                'R', Blocks.stone_button,
                'K', kineticIngot
        });
        GameRegistry.addRecipe(stoneCoreEmpty, new Object[]{
                " S ",
                "WCW",
                "KSK",
                'S', Blocks.stone,
                'C', new ItemStack(woodenKineticEnergyCore, 1 , OreDictionary.WILDCARD_VALUE),
                'K', kineticIngot

        });
    }
}