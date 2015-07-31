package com.techmafia.mcmods.KinetiCraft2.gui;

import com.techmafia.mcmods.KinetiCraft2.reference.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by myang on 7/30/15.
 */
public class KC2Gui extends GuiContainer {
    protected String inventoryName = "";
    protected String inventoryTexture = "";

    public KC2Gui(Container container, String inventoryName)
    {
        super(container);

        this.inventoryName = inventoryName;
        this.inventoryTexture = Reference.MOD_NAME + ":textures/gui/" + inventoryName + ".png";
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString(this.inventoryName, 7, 6, 4210752);
        this.fontRendererObj.drawString("Inventory", 7, 71, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(this.inventoryTexture));

        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 6 * 18 + 17);
        this.drawTexturedModalRect(k, l + 6 * 18 + 17, 0, 125, this.xSize, 96);
    }
}
