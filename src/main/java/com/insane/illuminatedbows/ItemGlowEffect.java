package com.insane.illuminatedbows;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemGlowEffect extends Item {

	public ItemGlowEffect() {
		super();
        this.setUnlocalizedName("glowEffect");
	}
	
	 @SideOnly(Side.CLIENT)
     @Override
	  public void registerIcons(IIconRegister par1IconRegister)
	  {
	      this.itemIcon = par1IconRegister.registerIcon("illuminatedbows:glow");
	  }


}
