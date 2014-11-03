package com.insane.illuminatedbows.addons.thaumcraft.tile;

import com.insane.illuminatedbows.IlluminatedBows;

import thaumcraft.common.Thaumcraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileColouredNitor extends TileEntity {

	private int colour;

	public TileColouredNitor()
	{
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		tag.setInteger(IlluminatedBows.MODID+"colour", this.colour);
		super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		this.colour=tag.getInteger(IlluminatedBows.MODID+"colour");
		if (this.worldObj!=null)
		{
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
		super.readFromNBT(tag);
	}

	public void setColour(int param)
	{
		this.colour=param;
	}


	public boolean canUpdate()
	{
		return true;
	}

	public void updateEntity() {

		super.updateEntity();

		if (this.worldObj.isRemote) {

			if (this.worldObj.rand.nextInt(9 - Thaumcraft.proxy.particleCount(2)) == 0) {
				IlluminatedBows.proxy.colourNitorEffects(this.worldObj, this.xCoord + 0.5F, this.yCoord + 0.5F, this.zCoord + 0.5F, this.xCoord + 0.3F + this.worldObj.rand.nextFloat() * 0.4F, this.yCoord + 0.5F, this.zCoord + 0.3F + this.worldObj.rand.nextFloat() * 0.4F, 0.5F, this.colour, true, -0.025F, this.colour);
			}

			if (this.worldObj.rand.nextInt(15 - Thaumcraft.proxy.particleCount(4)) == 0) {
				IlluminatedBows.proxy.colourNitorEffects(this.worldObj, this.xCoord + 0.5F, this.yCoord + 0.5F, this.zCoord + 0.5F, this.xCoord + 0.4F + this.worldObj.rand.nextFloat() * 0.2F, this.yCoord + 0.5F, this.zCoord + 0.4F + this.worldObj.rand.nextFloat() * 0.2F, 0.25F, this.colour, true, -0.02F, this.colour);
			}

		}

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
