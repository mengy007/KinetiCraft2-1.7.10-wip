package com.techmafia.mcmods.KinetiCraft2.items;

import com.techmafia.mcmods.KinetiCraft2.creativetab.CreativeTabKC2;
import com.techmafia.mcmods.KinetiCraft2.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Meng on 7/27/2015.
 */
public class KineticIngot extends KC2Item {
    public KineticIngot() {
        super();
        this.setUnlocalizedName("kineticIngot");
        this.setMaxStackSize(64);
        this.setNoRepair();
    }
}
