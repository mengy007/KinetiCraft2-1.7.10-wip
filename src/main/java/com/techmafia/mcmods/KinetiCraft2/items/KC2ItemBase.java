package com.techmafia.mcmods.KinetiCraft2.items;

import com.techmafia.mcmods.KinetiCraft2.creativetab.CreativeTabKC2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

/**
 * Created by Meng on 7/31/2015.
 */
public class KC2ItemBase extends Item {
    protected IIcon[] icons;

    public KC2ItemBase(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabKC2.KC2_TAB);
        icons = new IIcon[getNumberOfSubItems()];
    }

    protected int getNumberOfSubItems() {
        return 0;
    }

    protected String[] getSubItemNames() {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        if (icons.length > damage && !this.isDamageable()) {
            return icons[damage];
        }

        return super.getIconFromDamage(damage);
    }

    @Override
    public int getMetadata(int metadata) {
        return metadata;
    }
}
