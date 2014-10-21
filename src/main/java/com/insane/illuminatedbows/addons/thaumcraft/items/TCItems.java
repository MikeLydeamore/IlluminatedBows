package com.insane.illuminatedbows.addons.thaumcraft.items;


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class TCItems {

	public static Item itemMagicBow;
	public static Item itemFocusIlluminating;
	
	public static void preInit()
	{
		itemMagicBow = new ItemMagicBow();
		GameRegistry.registerItem(itemMagicBow, "magicBow");
		
		itemFocusIlluminating = new ItemFocusIlluminating();
		GameRegistry.registerItem(itemFocusIlluminating, "focusIlluminating");
	}
}
