package com.techmafia.mcmods.KinetiCraft2.init;

import com.techmafia.mcmods.KinetiCraft2.items.KineticDust;
import com.techmafia.mcmods.KinetiCraft2.items.KineticIngot;
import com.techmafia.mcmods.KinetiCraft2.items.WoodenKineticEnergyCore;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * Created by Meng on 7/27/2015.
 */
public class KinetiCraft2Items {
    public static final KineticDust kineticDust                         = new KineticDust();
    public static final KineticIngot kineticIngot                       = new KineticIngot();
    public static final WoodenKineticEnergyCore woodenKineticEnergyCore = new WoodenKineticEnergyCore();

    public static void init() {
        /* Empty Energy Cores */
        ItemStack woodenCoreEmpty = new ItemStack(woodenKineticEnergyCore, 1);
        woodenCoreEmpty.setItemDamage(woodenCoreEmpty.getMaxDamage());

        /* Register Items */
        GameRegistry.registerItem(kineticDust, "kineticDust");
        GameRegistry.registerItem(kineticIngot, "kinecticIngot");
        GameRegistry.registerItem(woodenKineticEnergyCore, "woodenKineticEnergyCore");

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
    }
}
