package com.techmafia.mcmods.KinetiCraft2.init;

import com.techmafia.mcmods.KinetiCraft2.items.KineticDust;
import com.techmafia.mcmods.KinetiCraft2.items.KineticIngot;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

/**
 * Created by Meng on 7/27/2015.
 */
public class KinetiCraft2Items {
    public static final KineticDust kineticDust     = new KineticDust();
    public static final KineticIngot kineticIngot   = new KineticIngot();

    public static void init() {
        /* Register Items */
        GameRegistry.registerItem(kineticDust, "kineticDust");
        GameRegistry.registerItem(kineticIngot, "kinecticIngot");

        /* Register furnace smelts */
        GameRegistry.addSmelting(kineticDust, new ItemStack(kineticIngot, 1, 0), 0.1f);
    }
}
