package com.insane.illuminatedbows;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class stairCrafting implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting inventorycrafting, World world) {
		ItemStack[][] invStacks = new ItemStack[3][3];
		for( int row = 0, col = 0, i = 0; i < 9; ++i, row = i / 3, col = i % 3 ) {
			invStacks[row][col] = inventorycrafting.getStackInRowAndColumn(col, row);
		}
		
		for (int row=0; row<3; row++) {
			for (int col=0; col<= row; col++) {
				if (invStacks[row][col]==null) {
					return false;
				}
					if (invStacks[row][col].getItem()!=IlluminatedBows.illuminatedPlanks.getItem(null, 0,0,0)) {
						return false;
					}
			}
		}
		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventorycrafting) {
		if (inventorycrafting.getStackInRowAndColumn(0,0).getItem()==IlluminatedBows.illuminatedPlanks.getItem(null, 0,0,0)) {
			return new ItemStack(IlluminatedBows.illuminatedStairs,4);
		}
		else {
			return null;
		}
	}

	@Override
	public int getRecipeSize() {
		return 9;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(IlluminatedBows.illuminatedStairs,4);
	}

}
