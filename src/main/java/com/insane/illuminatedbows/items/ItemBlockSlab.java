package com.insane.illuminatedbows.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by Michael on 1/09/2014.
 */
public class ItemBlockSlab extends ItemBlock {

    private final static String[] subNames = {"halfSlab", "doubleSlab", "topSlab"};

    public ItemBlockSlab(Block block) {
        super(block);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("slabMultiBlock");
    }

    @Override
    public int getMetadata(int damageValue) {
        return 0;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return getUnlocalizedName()+"."+subNames[itemstack.getItemDamage()];
    }
}
