package com.techmafia.mcmods.KinetiCraft2.proxy;

import com.techmafia.mcmods.KinetiCraft2.init.KinetiCraft2Blocks;
import com.techmafia.mcmods.KinetiCraft2.renderers.HardenedKineticEnergyCubeTileEntityRenderer;
import com.techmafia.mcmods.KinetiCraft2.renderers.KC2EnergyCubeTileEntityItemRenderer;
import com.techmafia.mcmods.KinetiCraft2.renderers.KC2EnergyCubeTileEntityRenderer;
import com.techmafia.mcmods.KinetiCraft2.tileentities.HardenedKineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft2.tileentities.KineticEnergyCubeTileEntity;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * Created by Meng on 7/31/2015.
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void registerClientStuff() {
        TileEntitySpecialRenderer energyCubeSpecialRenderer = new KC2EnergyCubeTileEntityRenderer();
        TileEntitySpecialRenderer hardenedEnergyCubeSpecialRenderer = new HardenedKineticEnergyCubeTileEntityRenderer();

        ClientRegistry.bindTileEntitySpecialRenderer(KineticEnergyCubeTileEntity.class, energyCubeSpecialRenderer);
        ClientRegistry.bindTileEntitySpecialRenderer(HardenedKineticEnergyCubeTileEntity.class, hardenedEnergyCubeSpecialRenderer);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(KinetiCraft2Blocks.kineticEnergyCube), new KC2EnergyCubeTileEntityItemRenderer(energyCubeSpecialRenderer, new KineticEnergyCubeTileEntity()));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(KinetiCraft2Blocks.hardenedKineticEnergyCube), new KC2EnergyCubeTileEntityItemRenderer(energyCubeSpecialRenderer, new HardenedKineticEnergyCubeTileEntity()));
    }
}
