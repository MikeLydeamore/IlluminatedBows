package com.insane.illuminatedbows;

import java.util.List;
import java.util.Random;

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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockIlluminatedBlock extends Block {

	public BlockIlluminatedBlock() {
		super(Material.glass);
		this.setBlockName("illuminatedBlock");
		this.setLightLevel(0.9375F);
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
	}

	@SideOnly(Side.CLIENT)
    @Override
	public IIcon getIcon(int par1, int par2) {
		return this.blockIcon;
	}

	@SideOnly(Side.CLIENT)
    @Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i=0; i<6; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}

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
		switch (meta) {
		case 0: {
			this.setBlockBounds(0,0.9F,0,1,1,1);
			break; }
		case 1: {
			this.setBlockBounds(0,0,0,1,0.1F,1);
			break; }
		case 2: {
			this.setBlockBounds(0,0,0.9F,1,1,1);
			break; }
		case 3: {
			this.setBlockBounds(0,0,0,1,1,0.1F);
			break; }
		case 4: {
			this.setBlockBounds(0.9F,0,0,1,1,1);
			break; }
		case 5: {
			this.setBlockBounds(0,0,0,0.1F,1,1);
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


	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
    @Override
	 public int quantityDropped(Random par1Random)
	 {
		 return par1Random.nextInt(2);
	 }

	 /**
	  * Returns the ID of the items to drop on destruction.
	  */
     @Override
	 public Item getItemDropped(int par1, Random par2Random, int par3)
	 {
		 return Items.glowstone_dust;
     }


}
