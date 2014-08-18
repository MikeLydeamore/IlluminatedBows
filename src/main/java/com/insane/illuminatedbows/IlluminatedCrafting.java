package com.insane.illuminatedbows;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class IlluminatedCrafting {
	
	public IlluminatedCrafting() {}
	
	public static void doCrafting() {
		GameRegistry.addRecipe(new ItemStack(IlluminatedBows.illuminatedStairs, 4), new Object[] { "x  ","xx ", "xxx", Character.valueOf('x'),IlluminatedBows.illuminatedPlanks}); 
	}

}
