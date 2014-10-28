package com.insane.illuminatedbows.tile;

import com.insane.illuminatedbows.IlluminatedBows;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileIllumination extends TileEntity {

	private Block camoBlock;

	public void setBlock(Block input)
	{
		camoBlock = input;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		tag.setInteger(IlluminatedBows.MODID+"camoID",Block.getIdFromBlock(camoBlock));
		super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		this.camoBlock = Block.getBlockById(tag.getInteger(IlluminatedBows.MODID+"camoID"));
		super.readFromNBT(tag);
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.func_148857_g());
	}

}
