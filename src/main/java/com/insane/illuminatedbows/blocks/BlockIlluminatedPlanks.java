package com.insane.illuminatedbows.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public class BlockIlluminatedPlanks extends Block {

	public BlockIlluminatedPlanks() {
		super(Material.wood);
		this.setLightLevel(1F);
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



    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        int meta = world.getBlockMetadata(x,y,z);
        if (meta==1) {
            world.setBlockMetadataWithNotify(x,y,z,2,2);
            return true;
        }
        else {
            return false;
        }
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x,y,z);
        switch (meta) {
            case 0: {
                this.setBlockBounds(0,0,0,1,1,1);
                break; }
            case 1: {
                this.setBlockBounds(0,0,0,1,0.5F,1);
                break; }
            case 2: {
                this.setBlockBounds(0,0,0,1,1,1);
                break; }
            case 3: {
                this.setBlockBounds(0,0.5F,0,1,1,1);
                break; }
        }
    }


}
