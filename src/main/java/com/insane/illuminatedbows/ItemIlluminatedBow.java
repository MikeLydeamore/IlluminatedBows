package com.insane.illuminatedbows;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemIlluminatedBow extends ItemBow {

	public static final String[] bowPullIconNameArray = new String[] {"illuminatedbow_pulling_0", "illuminatedbow_pulling_1", "illuminatedbow_pulling_2"};
	@SideOnly(Side.CLIENT)
    private IIcon[] iconArray;
	
	public ItemIlluminatedBow() {
		super();
		this.setMaxDamage(100);
        this.setUnlocalizedName("illuminatedBow");
	}
	
	@SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("illuminatedbows:illuminatedbow_standby");
        this.iconArray = new IIcon[bowPullIconNameArray.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon("illuminatedbows:"+bowPullIconNameArray[i]);
        }
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
            if (usingItem == null) { return itemIcon; }
            int ticksInUse = stack.getMaxItemUseDuration() - useRemaining;

            if (ticksInUse > 18) {
                    return iconArray[2];
            } else if (ticksInUse > 14) {
                    return iconArray[1];
            } else if (ticksInUse > 0) {
                    return iconArray[0];
            } else {
                    return itemIcon;
            }
    }
	
	@Override
	   public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	   {
		 ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
	        MinecraftForge.EVENT_BUS.post(event);
	        if (event.isCanceled())
	        {
	            return event.result;
	        }

	        if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(IlluminatedBows.illuminatedArrow))
	        {
	            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
	        }

	        return par1ItemStack;
	   }
	
	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
		if (!par2World.isRemote) {
        int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

        ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        
        j = event.charge;
        
        
        if (par3EntityPlayer.capabilities.isCreativeMode||par3EntityPlayer.inventory.hasItem(IlluminatedBows.illuminatedArrow)) {
        	float f = (float)j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }
        	//par2World.spawnEntityInWorld(new EntityIlluminatedArrow(par2World, par3EntityPlayer));
            	par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            	if (!par3EntityPlayer.capabilities.isCreativeMode||!IlluminatedBows.bowTakesDamage) { 
            		par1ItemStack.damageItem(1, par3EntityPlayer);
            		par3EntityPlayer.inventory.consumeInventoryItem(IlluminatedBows.illuminatedArrow);
            	}
        		par2World.spawnEntityInWorld(new EntityIlluminatedArrow(par2World, par3EntityPlayer, f));
        	}
        }
    }

}
