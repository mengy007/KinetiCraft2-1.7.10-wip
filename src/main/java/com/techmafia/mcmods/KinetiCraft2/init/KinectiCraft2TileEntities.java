package com.techmafia.mcmods.KinetiCraft2.init;

import com.techmafia.mcmods.KinetiCraft2.tileentities.KineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft2.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by myang on 7/30/15.
 */
public class KinectiCraft2TileEntities {
    public static void init() {
        GameRegistry.registerTileEntity(KineticEnergyCubeTileEntity.class, "KineticEnergyCubeTileEntity");

        LogHelper.info("Done registering tile entities!");
    }
}
