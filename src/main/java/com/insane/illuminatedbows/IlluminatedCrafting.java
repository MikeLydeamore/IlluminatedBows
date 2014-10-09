package com.insane.illuminatedbows;

import com.insane.illuminatedbows.blocks.IlluminatedBlocks;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class IlluminatedCrafting {
	
	public IlluminatedCrafting() {}
	
	public static void doCrafting() {
		GameRegistry.addRecipe(new ItemStack(IlluminatedBlocks.illuminatedStairs, 4), new Object[] { "x  ","xx ", "xxx", Character.valueOf('x'),IlluminatedBlocks.illuminatedPlanks}); 
	}

}
