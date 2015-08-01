package com.techmafia.mcmods.KinetiCraft2.renderers;

import com.techmafia.mcmods.KinetiCraft2.items.KineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft2.reference.Reference;
import com.techmafia.mcmods.KinetiCraft2.tileentities.KineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft2.utility.LogHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Meng on 7/31/2015.
 */
public class KC2EnergyCubeTileEntityRenderer extends TileEntitySpecialRenderer {
    private ResourceLocation textureSide = new ResourceLocation(Reference.MOD_NAME + ":textures/blocks/kineticEnergyCube.png");
    private ResourceLocation textureFront = new ResourceLocation(Reference.MOD_NAME + ":textures/blocks/kineticEnergyCubeFront.png");
    private ResourceLocation textureGray = new ResourceLocation(Reference.MOD_NAME + ":textures/blocks/gray.png");
    private ResourceLocation[] textureStatus = new ResourceLocation[4];
    private float pixel = 1f/16f;
    private float texturePixel = 1f/32f;

    // 0:NORTH, 1:EAST, 2:SOUTH, 3:WEST
    public static final int NORTHFACE   = 0;
    public static final int EASTFACE    = 1;
    public static final int SOUTHFACE   = 2;
    public static final int WESTFACE    = 3;

    public KC2EnergyCubeTileEntityRenderer() {
        textureStatus[0] = new ResourceLocation(Reference.MOD_NAME + ":textures/blocks/red.png");
        textureStatus[1] = new ResourceLocation(Reference.MOD_NAME + ":textures/blocks/yellow.png");
        textureStatus[2] = new ResourceLocation(Reference.MOD_NAME + ":textures/blocks/orange.png");
        textureStatus[3] = new ResourceLocation(Reference.MOD_NAME + ":textures/blocks/green.png");
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double translationX, double translationY, double translationZ, float f) {
        GL11.glTranslated(translationX, translationY, translationZ);
        renderFrame();
        renderSides(tileEntity);
        renderFront(tileEntity);
        GL11.glTranslated(-translationX, -translationY, -translationZ);
    }

    public void renderFrame() {
        Tessellator tes = Tessellator.instance;
        this.bindTexture(textureSide);
        tes.startDrawingQuads();
        {
            // north face, full top, full bottom, sides

            // NORTH TOP FRONT
            /*
            tes.addVertexWithUV(0, 1-(pixel*2), 0, 0, pixel*2);
            tes.addVertexWithUV(0, 1, 0, 0, 0);
            tes.addVertexWithUV(1, 1, 0, 1, 0);
            tes.addVertexWithUV(1, 1-(pixel*2), 0, 1, pixel*2);

            // NORTH BOTTOM FRONT
            tes.addVertexWithUV(0, 0, 0, 0, 1);
            tes.addVertexWithUV(0, pixel*2, 0, 0, 1-(pixel*2));
            tes.addVertexWithUV(1, pixel*2, 0, 1, 1-(pixel*2));
            tes.addVertexWithUV(1, 0, 0, 1, 1);
            */

        }
        tes.draw();
    }

    public void renderSides(TileEntity tileEntity) {

    }

    public void renderFront(TileEntity tileEntity) {
        Tessellator tes = Tessellator.instance;
        int frontFace = ((KineticEnergyCubeTileEntity)tileEntity).getFrontFace();

        // Do translations and rotations
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
        if (frontFace == NORTHFACE) {
        } else if (frontFace == EASTFACE) {
            GL11.glRotatef(-90, 0, 1, 0);
        } else if (frontFace == SOUTHFACE) {
            GL11.glRotatef(-180, 0, 1, 0);
        } else if (frontFace == WESTFACE) {
            GL11.glRotatef(-270, 0, 1, 0);
        }

        for (int i = 0; i < ((KineticEnergyCubeTileEntity)tileEntity).getSizeInventory(); i++) {
            ItemStack itemStack = ((KineticEnergyCubeTileEntity)tileEntity).getStackInSlot(i);

            if (itemStack != null) {
                int energyStored = KineticEnergyCore.getEnergyStored(itemStack);
                int maxEnergy = ((KineticEnergyCore)itemStack.getItem()).maxEnergy;
                int iconIndex = energyStored/(maxEnergy/4);
                float xOffset = ((i)*3*pixel) - 4.5f*((i/3)*2*pixel);
                float yOffset = (i/3)*2*pixel;

                iconIndex = iconIndex > 3 ? 3 : iconIndex;
                iconIndex = iconIndex < 0 ? 0 : iconIndex;

                // Draw core background first
                this.bindTexture(this.textureGray);
                tes.startDrawingQuads();
                {
                    tes.addVertexWithUV(2*pixel-xOffset, 4*pixel-yOffset, -0.501, 0, 1);
                    tes.addVertexWithUV(2*pixel-xOffset, 5*pixel-yOffset, -0.501, 0, 0);
                    tes.addVertexWithUV(4*pixel-xOffset, 5*pixel-yOffset, -0.501, 1, 0);
                    tes.addVertexWithUV(4*pixel-xOffset, 4*pixel-yOffset, -0.501, 1, 1);
                }
                tes.draw();

                this.bindTexture(this.textureStatus[iconIndex]);
                tes.startDrawingQuads();
                {
                    tes.addVertexWithUV(2.25*pixel-xOffset, 4.25*pixel-yOffset, -0.502, 0, 1);
                    tes.addVertexWithUV(2.25*pixel-xOffset, 4.75*pixel-yOffset, -0.502, 0, 0);
                    tes.addVertexWithUV(3*pixel-xOffset, 4.75*pixel-yOffset, -0.502, 1, 0);
                    tes.addVertexWithUV(3*pixel-xOffset, 4.25*pixel-yOffset, -0.502, 1, 1);
                }

                tes.draw();
            } else {
                //LogHelper.info("FAIL");
            }
        }

        // Undo translations and rotations
        if (frontFace == NORTHFACE) {
        } else if (frontFace == EASTFACE) {
            GL11.glRotatef(90, 0, 1, 0);
        } else if (frontFace == SOUTHFACE) {
            GL11.glRotatef(180, 0, 1, 0);
        } else if (frontFace == WESTFACE) {
            GL11.glRotatef(270, 0, 1, 0);
        }
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
    }
}
