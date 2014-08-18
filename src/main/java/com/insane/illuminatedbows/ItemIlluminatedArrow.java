package com.insane.illuminatedbows;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemIlluminatedArrow extends Item {

	public ItemIlluminatedArrow() {
		super();
		this.setUnlocalizedName("illuminatedArrow");
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.setMaxStackSize(64);
	}
	
	 @SideOnly(Side.CLIENT)
     @Override
	  public void registerIcons(IIconRegister par1IconRegister)
	  {
	      this.itemIcon = par1IconRegister.registerIcon("illuminatedbows:illuminatedarrow");
	  }

}
