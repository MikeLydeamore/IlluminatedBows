package com.insane.illuminatedbows.addons.thaumcraft;

import java.util.ArrayList;

import com.insane.illuminatedbows.IlluminatedBows;
import com.insane.illuminatedbows.addons.thaumcraft.items.TCItems;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class FocusColourCrafting implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting crafting, World world) {
		ItemStack stack = null;
		ArrayList array = new ArrayList();
		for (int i=0; i< crafting.getSizeInventory(); ++i)
		{
			ItemStack stack1 = crafting.getStackInSlot(i);
			if (stack1 != null)
			{
				if (stack1.getItem() == TCItems.itemFocusColoured)
				{
					stack = stack1;
				}
				else
				{
					if (stack1.getItem() != Items.dye && stack1.getItem() != Items.gunpowder)
						return false;
					
					array.add(stack1);
				}
			}
		}
		return stack!=null && !array.isEmpty();
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting crafting) {
		ItemStack result = null;
		ItemStack dyeStack = null;
		for (int k=0; k<crafting.getSizeInventory(); ++k)
		{
			ItemStack stack1 = crafting.getStackInSlot(k);
			if (stack1 !=null)
			{
				if (stack1.getItem() == TCItems.itemFocusColoured)
					result = stack1.copy();
				
				else
				{
					if (stack1.getItem() == Items.gunpowder || stack1.getItem() == Items.dye)
						dyeStack = stack1.copy();
					else
					{
						return null;
					}
				}
			}	
		}
		
		if (result==null)
			return null;
		else
		{
			NBTTagCompound tag;
			if (result.hasTagCompound())
				tag = result.getTagCompound();
			else
				tag = new NBTTagCompound();
			
			if (dyeStack.getItem() == Items.gunpowder)
				tag.setInteger(IlluminatedBows.MODID+"colour", 16);
			else
				tag.setInteger(IlluminatedBows.MODID+"colour", dyeStack.getItemDamage());
			result.setTagCompound(tag);
			
			return result;
		}
	}

	@Override
	public int getRecipeSize() {
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return null;
	}

}
