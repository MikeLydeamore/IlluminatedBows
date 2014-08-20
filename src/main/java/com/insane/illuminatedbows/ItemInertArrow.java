package com.insane.illuminatedbows;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Michael on 19/08/2014.
 */
public class ItemInertArrow extends Item {
    public ItemInertArrow() {
        this.setUnlocalizedName("inertArrow");
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("illuminatedbows:inertarrow");
    }
}
