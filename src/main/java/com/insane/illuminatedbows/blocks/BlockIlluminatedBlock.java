package com.insane.illuminatedbows.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.insane.illuminatedbows.tile.TileIllumination;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockIlluminatedBlock extends Block {
	private IIcon[] iconArray;
	public BlockIlluminatedBlock() {
		super(Material.glass);
		this.setBlockName("illuminatedBlock");
		this.setLightLevel(1F);
		this.isOpaqueCube();
		this.setHardness(0.2F);
		this.setStepSound(Block.soundTypeGlass);
	}

	public String getItemStackDisplayName() {
		return "Illumination";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("illuminatedbows:illuminatedblock");
		this.iconArray = new IIcon[2];
		iconArray[0] = this.blockIcon;
		iconArray[1] = par1IconRegister.registerIcon("illuminatedbows:illumination_vis");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2) {
		if (par2 >=6)
			return this.iconArray[1];
		else
			return this.iconArray[0];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i=0; i<12; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}

	}

	@Override
	public boolean hasTileEntity(int meta)
	{
		return true;
	}
	
	public TileEntity createNewTitleEntity(World world)
	{
		return new TileIllumination();
	}

	public int damageDropped(int par1) {
		return 0;
	}

	@Override
	public int getMobilityFlag() {
		return 1;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x,y,z);
		switch (meta%6) {
		case 0: {
			this.setBlockBounds(0,0.99F,0,1,1,1);
			break; }
		case 1: {
			this.setBlockBounds(0,0,0,1,0.01F,1);
			break; }
		case 2: {
			this.setBlockBounds(0,0,0.99F,1,1,1);
			break; }
		case 3: {
			this.setBlockBounds(0,0,0,1,1,0.01F);
			break; }
		case 4: {
			this.setBlockBounds(0.99F,0,0,1,1,1);
			break; }
		case 5: {
			this.setBlockBounds(0,0,0,0.01F,1,1);
			break; }
		/*default:
			this.setBlockBounds(0,0,0,1,1,1);
			break;*/
		}
	}

	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		boolean flag=false;
		//System.out.println(meta);
		switch (meta) {
		case 0: {
			if (par1World.isAirBlock(par2, par3+1, par4)) {
				flag=true;
			}
		} case 1: {
			if (par1World.isAirBlock(par2, par3-1, par4)) {
				flag=true;
			}
			break;
		} case 2: {
			if (par1World.isAirBlock(par2, par3, par4+1)) {
				flag=true;
			}
			break;
		} case 3: {
			if (par1World.isAirBlock(par2, par3, par4-1)) {
				flag=true;
			}
			break;
		} case 4: {
			if (par1World.isAirBlock(par2+1, par3, par4)) {
				flag=true;
			}
			break;
		} case 5: {
			if (par1World.isAirBlock(par2-1, par3, par4)) {
				flag=true;
			}
			break;
		}
		}

		if (flag) {
			par1World.setBlockToAir(par2,par3,par4);
		}

	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}


	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		if (metadata<6 && world.rand.nextInt(2)==1)
		{
			ret.add(new ItemStack(Items.glowstone_dust,1));
		}
		return ret;
	}


}
