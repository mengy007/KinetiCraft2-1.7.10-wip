package com.techmafia.mcmods.KinetiCraft2.renderers;

import com.techmafia.mcmods.KinetiCraft2.reference.Reference;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Meng on 8/1/2015.
 */
public class HardenedKineticEnergyCubeTileEntityRenderer extends KC2EnergyCubeTileEntityRenderer {
    public HardenedKineticEnergyCubeTileEntityRenderer() {
        super();
        textureFull = new ResourceLocation(Reference.MOD_NAME + ":textures/blocks/hardenedKineticEnergyCube.png");
        textureFrame = new ResourceLocation(Reference.MOD_NAME + ":textures/blocks/hardenedKineticEnergyCubeFrame.png");
        textureSide = new ResourceLocation(Reference.MOD_NAME + ":textures/blocks/hardenedKineticEnergyCubeSide.png");
    }
}
