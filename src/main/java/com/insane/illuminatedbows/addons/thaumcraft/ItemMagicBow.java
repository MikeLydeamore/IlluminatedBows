package com.insane.illuminatedbows.addons.thaumcraft;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import com.insane.illuminatedbows.items.ItemIlluminatedBow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMagicBow extends ItemIlluminatedBow {
	
	public ItemMagicBow()
	{
		super();
		this.setUnlocalizedName("magicBow");
		this.setMaxDamage(150);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack)
	{
		return EnumRarity.rare;
	}

}
