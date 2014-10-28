package com.insane.illuminatedbows.addons.nei;

import codechicken.nei.api.API;
import net.minecraft.item.ItemStack;

public class NEICompat {

	public static void hideItem(ItemStack stack)
	{
		API.hideItem(stack);
	}

}
