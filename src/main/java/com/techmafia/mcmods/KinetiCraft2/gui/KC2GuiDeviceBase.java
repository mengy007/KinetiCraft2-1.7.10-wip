package com.techmafia.mcmods.KinetiCraft2.gui;

import com.techmafia.mcmods.KinetiCraft2.tileentities.base.KC2TileEntityBase;
import net.minecraft.inventory.Container;

/**
 * Created by Meng on 7/30/2015.
 */
public abstract class KC2GuiDeviceBase extends KC2GuiBase {
    KC2TileEntityBase _entity;

    public KC2GuiDeviceBase(Container container, KC2TileEntityBase tileEntity) {
        super(container);
        _entity = tileEntity;
    }
}
