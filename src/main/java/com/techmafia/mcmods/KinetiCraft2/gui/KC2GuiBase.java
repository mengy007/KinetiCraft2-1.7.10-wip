package com.techmafia.mcmods.KinetiCraft2.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Meng on 7/30/2015.
 */
@SideOnly(Side.CLIENT)
public abstract class KC2GuiBase extends GuiContainer {
    public KC2GuiBase(Container container) {
        super(container);
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    public FontRenderer getFontRenderer() {
        return this.fontRendererObj;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float gameTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.renderEngine.bindTexture(getGuiBackground());
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        /*
        this.mc.getTextureManager().bindTexture(getGuiBackground());
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 6 * 18 + 17);
        this.drawTexturedModalRect(k, l + 6 * 18 + 17, 0, 125, this.xSize, 96);
        */
    }

    public abstract ResourceLocation getGuiBackground();
}
