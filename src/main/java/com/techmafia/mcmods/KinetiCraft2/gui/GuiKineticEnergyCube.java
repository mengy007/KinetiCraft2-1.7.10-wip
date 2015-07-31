package com.techmafia.mcmods.KinetiCraft2.gui;

import com.techmafia.mcmods.KinetiCraft2.reference.Reference;
import com.techmafia.mcmods.KinetiCraft2.tileentities.KineticEnergyCubeTileEntity;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Meng on 7/30/2015.
 */
public class GuiKineticEnergyCube extends KC2GuiDeviceBase {
    private KineticEnergyCubeTileEntity _entity;

    public GuiKineticEnergyCube(Container container, KineticEnergyCubeTileEntity entity) {
        super(container, entity);

        _entity = entity;
        xSize = 176;
        ySize = 166;
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString("Kinetic Energy Cube", 7, 6, 4210752);
        this.fontRendererObj.drawString("Inventory", 7, 71, 4210752);
    }

    @Override
    public ResourceLocation getGuiBackground() {
        return new ResourceLocation(Reference.MOD_NAME + ":textures/gui/Kinetic Energy Cube.png");
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float gameTicks) {
        super.drawScreen(mouseX, mouseY, gameTicks);
    }
}
