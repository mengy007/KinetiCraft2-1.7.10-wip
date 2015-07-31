package com.techmafia.mcmods.KinetiCraft2.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by Meng on 7/31/2015.
 */
public class ItemBlockKC2 extends ItemBlock {
    public ItemBlockKC2(Block block){
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int i) {
        return i;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        if(this.hasSubtypes) {
            int metadata = itemStack.getItemDamage();
            return super.getUnlocalizedName(itemStack) + "." + Integer.toString(metadata);
        }
        else {
            return super.getUnlocalizedName(itemStack);
        }
    }

    @Override
    public String getUnlocalizedName() {
        if(this.hasSubtypes) {
            return super.getUnlocalizedName() + ".0";
        }
        else {
            return super.getUnlocalizedName();
        }
    }
}
