package com.insane.illuminatedbows;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemIlluminatedBlock extends ItemBlock {

	public ItemIlluminatedBlock(Block par1) {
		super(par1);
		this.setHasSubtypes(true);
        this.setUnlocalizedName("illuminatedItemBlock");
	}
	
	public String getUnlocalizedName(ItemStack itemstack) {
		String name="";
		switch(itemstack.getItemDamage()) {
		case 0:	{
			name="left";
			break;
		} case 1: {
			name="right";
			break;
		} default:
			name="broken";
		}
		return getUnlocalizedName()+"."+name;
	}
	
	public int getMetadata(int par1) {
		return par1;
	}

}
