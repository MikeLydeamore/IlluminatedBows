package com.insane.illuminatedbows.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

import com.insane.illuminatedbows.IlluminatedBows;

/**
 * Created by Michael on 1/09/2014.
 */
public class BlockIlluminatedSlab extends Block {

    private boolean isHalfSlab;

    public BlockIlluminatedSlab(boolean par1) {
        super(Material.wood);
        this.setBlockName("illuminatedSlab");
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setLightLevel(1F);
        this.setStepSound(Block.soundTypeWood);
        this.setHardness(2.0F);
        this.isHalfSlab = true;
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
    public boolean isNormalCube()
    {
    	return false;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (player.getHeldItem().getItem().equals((new ItemStack(this)).getItem())) {
            if (side == 1 && world.getBlock(x, y, z) == IlluminatedBlocks.illuminatedSlab && world.getBlockMetadata(x, y, z) == 0) {
                world.setBlockMetadataWithNotify(x, y, z, 1, 2);
                world.playSoundEffect((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "dig.wood", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
                this.isHalfSlab = false;
                if (!player.capabilities.isCreativeMode) {player.inventory.decrStackSize(player.inventory.currentItem, 1); }
                return true;
            } else if (side == 0 && world.getBlock(x, y, z) == IlluminatedBlocks.illuminatedSlab && world.getBlockMetadata(x, y, z) == 2) {
                world.setBlockMetadataWithNotify(x, y, z, 1, 2);
                world.playSoundEffect((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "dig.wood", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
                this.isHalfSlab = false;
                if (!player.capabilities.isCreativeMode) {player.inventory.decrStackSize(player.inventory.currentItem, 1); }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        if (side==0) {
            this.isHalfSlab = true;
            return 2;
        }
        else if (hitY >= 0.5 && hitY < 1.0) {
            this.isHalfSlab=true;
            return 2;
        }
        else {
            this.isHalfSlab=true;
            return 0;
        }
    }

    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0,0,0,1,0.5F,1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i=0; i<1; i++) {
            par3List.add(new ItemStack(par1, 1, i));
        }

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

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x,y,z);
        switch (meta) {
            case 0: {
                this.setBlockBounds(0,0,0,1,0.5F,1);
                break; }
            case 1: {
                this.setBlockBounds(0,0,0,1,1,1);
                break; }
            case 2: {
                this.setBlockBounds(0,0.5F,0,1,1,1);
                break; }
		/*default:
			this.setBlockBounds(0,0,0,1,1,1);
			break;*/
        }
    }

    @Override
    public int quantityDropped(Random p_149745_1_)
    {
        if (this.isHalfSlab) {
            return 1;
        } else {
            return 2;
        }
    }
}
