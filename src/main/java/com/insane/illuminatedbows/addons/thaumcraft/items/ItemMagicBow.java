package com.insane.illuminatedbows.addons.thaumcraft.items;

import thaumcraft.api.IRepairable;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import com.insane.illuminatedbows.Config;
import com.insane.illuminatedbows.EntityIlluminatedArrow;
import com.insane.illuminatedbows.addons.thaumcraft.blocks.TCBlocks;
import com.insane.illuminatedbows.blocks.IlluminatedBlocks;
import com.insane.illuminatedbows.items.IlluminatedItems;
import com.insane.illuminatedbows.items.ItemIlluminatedBow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMagicBow extends ItemIlluminatedBow implements IRepairable {

	public ItemMagicBow()
	{
		super();
		this.setUnlocalizedName("magicBow");
		this.setMaxDamage(150);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack)
	{
		return EnumRarity.rare;
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
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));

		return par1ItemStack;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

		ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return;
		}

		j = event.charge;


		if (par3EntityPlayer.capabilities.isCreativeMode||ThaumcraftApiHelper.consumeVisFromInventory(par3EntityPlayer,new AspectList().add(Aspect.AIR,100).add(Aspect.FIRE,100))) 
		{
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
			EntityIlluminatedArrow arrow = new EntityIlluminatedArrow(par2World, par3EntityPlayer, 2.0F*f);
			arrow.setDamage(0);
			arrow.setBlockToSet(TCBlocks.blockMagicalIllumination);
			arrow.setDeadOnLand(true);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
			if (Config.bowTakesDamage) {
				par1ItemStack.damageItem(1, par3EntityPlayer);
			}
			if (!par2World.isRemote) {
				par2World.spawnEntityInWorld(arrow);
			}
		}
		else
		{
			par2World.playSoundAtEntity(par3EntityPlayer, "thaumcraft:craftfail", 1.0F, 1.0F);
		}
	}

}
