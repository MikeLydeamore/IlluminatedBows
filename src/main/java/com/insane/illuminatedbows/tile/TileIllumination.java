package com.insane.illuminatedbows.tile;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import com.insane.illuminatedbows.IlluminatedBows;

public class TileIllumination extends TileEntity
{

    public Block camoBlock = Blocks.stone;
    public Set<Integer> sides = new HashSet<Integer>();

    public void init(Block block, int meta)
    {
        this.camoBlock = block;
        this.sides.add(meta);
    }

    public void addSide(int side)
    {
        this.sides.add(side);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        tag.setInteger(IlluminatedBows.MODID + "camoID", Block.getIdFromBlock(camoBlock));
        int[] sides = new int[this.sides.size()];
        Iterator<Integer> iter = this.sides.iterator();
        for (int i = 0; i < sides.length; i++)
        {
            sides[i] = iter.next();
        }
        tag.setIntArray("illuminatedSides", sides);
        super.writeToNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        this.camoBlock = Block.getBlockById(tag.getInteger(IlluminatedBows.MODID + "camoID"));
        this.sides = new HashSet<Integer>();
        for (int i : tag.getIntArray("illuminatedSides"))
        {
            this.sides.add(i);
        }

        if (worldObj != null)
        {
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
        
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
