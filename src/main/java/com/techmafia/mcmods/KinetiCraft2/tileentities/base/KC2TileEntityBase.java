package com.techmafia.mcmods.KinetiCraft2.tileentities.base;

import cofh.core.block.TileCoFHBase;
import com.techmafia.mcmods.KinetiCraft2.gui.IKC2GuiEntity;
import com.techmafia.mcmods.KinetiCraft2.net.CommonPacketHandler;
import com.techmafia.mcmods.KinetiCraft2.net.messages.DeviceUpdateMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Meng on 7/28/2015.
 */
public abstract class KC2TileEntityBase extends TileCoFHBase implements IKC2GuiEntity {
    private Set<EntityPlayer> updatePlayers;
    private int ticksSinceLastUpdate;
    private static final int ticksBetweenUpdates = 3;

    public KC2TileEntityBase() {
        super();

        ticksSinceLastUpdate = 0;
        updatePlayers = new HashSet<EntityPlayer>();
    }

    // Save/Load
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
    }

    @Override
    public void onDataPacket(NetworkManager network, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if(!this.worldObj.isRemote && this.updatePlayers.size() > 0) {
            ticksSinceLastUpdate++;
            if(ticksSinceLastUpdate >= ticksBetweenUpdates) {
                sendUpdatePacket();
                ticksSinceLastUpdate = 0;
            }
        }
    }

    // Return true if this machine is active.
    public abstract boolean isActive();

    // Player updates via IKC2GuiEntity
    @Override
    public void beginUpdatingPlayer(EntityPlayer player) {
        updatePlayers.add(player);
        sendUpdatePacketToClient(player);
    }

    @Override
    public void stopUpdatingPlayer(EntityPlayer player) {
        updatePlayers.remove(player);
    }

    protected IMessage getUpdatePacket() {
        NBTTagCompound childData = new NBTTagCompound();
        onSendUpdate(childData);

        return new DeviceUpdateMessage(xCoord, yCoord, zCoord, childData);
    }

    private void sendUpdatePacketToClient(EntityPlayer recipient) {
        if(this.worldObj.isRemote) { return; }

        CommonPacketHandler.INSTANCE.sendTo(getUpdatePacket(), (EntityPlayerMP)recipient);

    }

    private void sendUpdatePacket() {
        if(this.worldObj.isRemote) { return; }
        if(this.updatePlayers.size() <= 0) { return; }

        for(EntityPlayer player : updatePlayers) {
            CommonPacketHandler.INSTANCE.sendTo(getUpdatePacket(), (EntityPlayerMP)player);
        }
    }

    /**
     * Fill this NBT Tag Compound with your custom entity data.
     * @param updateTag The tag to which your data should be written
     */
    protected void onSendUpdate(NBTTagCompound updateTag) {}

    /**
     * Read your custom update data from this NBT Tag Compound.
     * @param updateTag The tag which should contain your data.
     */
    public void onReceiveUpdate(NBTTagCompound updateTag) {}

    // Weird shit from TileCoFHBase
    public String getName() {
        return this.getBlockType().getUnlocalizedName();
    }

    public int getType() {
        return getBlockMetadata();
    }
}
