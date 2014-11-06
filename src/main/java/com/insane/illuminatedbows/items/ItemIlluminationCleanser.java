package com.insane.illuminatedbows.items;

import com.insane.illuminatedbows.Config;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemIlluminationCleanser extends Item {
	
	public ItemIlluminationCleanser()
	{
		super();
		this.setUnlocalizedName("illuminationCleanser");
		this.setMaxDamage(64);
		this.setMaxStackSize(1);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (Config.cleanserTakesDamage)
			stack.damageItem(1, player);
		return stack;
	}
	
	 @SideOnly(Side.CLIENT)
     @Override
	  public void registerIcons(IIconRegister par1IconRegister)
	  {
	      this.itemIcon = par1IconRegister.registerIcon("illuminatedbows:illuminationcleanser");
	  }

}
