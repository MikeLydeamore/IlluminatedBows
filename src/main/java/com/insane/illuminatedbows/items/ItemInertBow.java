package com.insane.illuminatedbows.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Michael on 19/08/2014.
 */
public class ItemInertBow extends Item {

    public ItemInertBow() {
        super();
        this.setUnlocalizedName("inertBow");
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("illuminatedbows:inertbow_standby");
    }
}
