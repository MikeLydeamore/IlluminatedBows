package com.insane.illuminatedbows;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockIlluminatedWood extends BlockRotatedPillar {

	private IIcon[] blockIcon;
	public BlockIlluminatedWood() {
		super(Material.wood);
		this.setBlockName("illuminatedWood");
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setLightLevel(0.86F);
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(2.0F);
	}

	@SideOnly(Side.CLIENT)
    @Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = new IIcon[2];
		this.blockIcon[0] = par1IconRegister.registerIcon("illuminatedbows:illuminatedlogtop");
		this.blockIcon[1]=par1IconRegister.registerIcon("illuminatedbows:illuminatedlogside");
	}


	protected IIcon getSideIcon(int par1)
	{
		return this.blockIcon[1];
	}

	@SideOnly(Side.CLIENT)
    @Override
	/**
	 * The icon for the tops and bottoms of the block.
	 */
	protected IIcon getTopIcon(int par1)
	{
		return this.blockIcon[0];
	}

	public boolean canSustainLeaves(World world, int x, int y, int z)
	{
		return true;
	}

	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		byte b0 = 4;
		int j1 = b0 + 1;

		if (par1World.checkChunksExist(par2 - j1, par3 - j1, par4 - j1, par2 + j1, par3 + j1, par4 + j1))
		{
			for (int k1 = -b0; k1 <= b0; ++k1)
			{
				for (int l1 = -b0; l1 <= b0; ++l1)
				{
					for (int i2 = -b0; i2 <= b0; ++i2)
					{
						Block j2 = par1World.getBlock(par2 + k1, par3 + l1, par4 + i2);

						if (j2.isLeaves(par1World, par2+k1, par3+l1, par4+i2))
						{
							j2.beginLeavesDecay(par1World, par2 + k1, par3 + l1, par4 + i2);
						}
					}
				}
			}
		}
	}
	@Override
	public boolean isWood(IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

}
