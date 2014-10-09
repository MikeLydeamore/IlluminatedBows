package com.insane.illuminatedbows.blocks;

import java.util.List;
import java.util.Random;

import com.insane.illuminatedbows.Config;
import com.insane.illuminatedbows.IlluminatedBows;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockIlluminatedLeaves extends BlockLeaves {

    public static final String[] displayName = new String[] {"illuminated"};
    IIcon[] icons = new IIcon[2];
	public BlockIlluminatedLeaves() {
		super();
		this.setBlockName("illuminatedLeaves");
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setLightLevel(0.933F);
		this.isOpaqueCube();
		this.setTickRandomly(true);
		this.setHardness(0.2F);
        this.setStepSound(Block.soundTypeGrass);
	}
	
	@SideOnly(Side.CLIENT)
    @Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.icons[0] = par1IconRegister.registerIcon("illuminatedbows:illuminatedleaves");
        this.icons[1] = par1IconRegister.registerIcon("illuminatedbows:illuminatedleavesopaque");
	}
	
	@SideOnly(Side.CLIENT)
    @Override
	public IIcon getIcon(int par1, int par2) {
		if (Minecraft.getMinecraft().gameSettings.fancyGraphics) {
            return this.icons[0];
        } else {
            return this.icons[1];
        }
	}

    @Override
    public String[] func_150125_e() {
        return displayName;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		par3List.add(new ItemStack(par1, 1, 0));
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		//return ColorizerFoliage.getFoliageColorBasic();
		return 16777215;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return 16777215;
    }
	
    public int getRenderColor(int par1)
    {
        return 16777215;
    }
    
	public int quantityDropped(Random par1Random)
    {
        return par1Random.nextInt(50) == 0 ? 1 : 0;
    }
	
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        if (Config.illuminatedSapling) {
            return Item.getItemFromBlock(IlluminatedBlocks.illuminatedSapling);
        } else {
            return Item.getItemFromBlock(Blocks.sapling);
        }
    }
    
    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, 0.7f, par7);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        return true;
    }

}
