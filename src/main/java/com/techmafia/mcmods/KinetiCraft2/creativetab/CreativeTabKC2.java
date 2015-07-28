package com.techmafia.mcmods.KinetiCraft2.creativetab;

import com.techmafia.mcmods.KinetiCraft2.init.KinetiCraft2Items;
import com.techmafia.mcmods.KinetiCraft2.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Meng on 7/27/2015.
 */
public class CreativeTabKC2 {
    public static final CreativeTabs KC2_TAB = new CreativeTabs(Reference.MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return KinetiCraft2Items.kineticIngot;
        }

        @Override
        public String getTranslatedTabLabel() {
            return "KinetiCraft 2";
        }
    };
}
