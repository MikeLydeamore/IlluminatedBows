package com.insane.illuminatedbows;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BlockIlluminatedPlanks extends Block {

	public BlockIlluminatedPlanks() {
		super(Material.wood);
		this.setLightLevel(0.86F);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setBlockName("illuminatedWood");
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(2.0F);
	}
	
	@SideOnly(Side.CLIENT)
    @Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("illuminatedbows:illuminatedplanks");
	}
	
	@SideOnly(Side.CLIENT)
    @Override
	public IIcon getIcon(int par1, int par2) {
		return this.blockIcon;
	}
	

}
