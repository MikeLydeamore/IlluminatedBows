package com.insane.illuminatedbows.addons.thaumcraft.blocks;

import com.insane.illuminatedbows.addons.thaumcraft.tile.TileColouredNitor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockNitorColour extends BlockContainer {

	public BlockNitorColour()
	{
		super(Material.cloth);
		this.setBlockName("nitorColour");
		this.setBlockBounds(0.3F, 0.3F, 0.3F, 0.7F, 0.7F, 0.7F);
	}

	@Override
	public boolean hasTileEntity()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {

		blockIcon = register.registerIcon("thaumcraft:blank");

	}

	@Override
	public int getRenderType() {return -1;}

	@Override
	public int getLightValue() {return 15;}

	public boolean renderAsNormalBlock() {return false;}

	public boolean isOpaqueCube() {return false;}


	@Override
	public TileEntity createTileEntity(World world, int meta) 
	{
		return new TileColouredNitor();
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) 
	{
		TileColouredNitor te = new TileColouredNitor();
		te.setColour(1);
		return new TileColouredNitor();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float a, float b, float c)
	{
		ItemStack held = player.getCurrentEquippedItem();
		TileColouredNitor te = (TileColouredNitor) world.getTileEntity(x,y,z);
		if (held != null && held.getItem() == Items.dye)
		{
			if (held.getItemDamage() == 1) //Red
			{
				te.setColour(1);
				held.stackSize--;
				
				return true;
			}

		}
		return false;
	}

}
