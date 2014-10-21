package com.insane.illuminatedbows.addons.thaumcraft.items;

import com.insane.illuminatedbows.Config;
import com.insane.illuminatedbows.EntityIlluminatedArrow;
import com.insane.illuminatedbows.addons.thaumcraft.blocks.TCBlocks;
import com.insane.illuminatedbows.blocks.IlluminatedBlocks;
import com.insane.illuminatedbows.items.IlluminatedItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.IWandFocus;

public class ItemFocusIlluminating extends Item implements IWandFocus {

	public ItemFocusIlluminating()
	{
		super();
		this.setUnlocalizedName("focusIlluminating");
	}

	@Override
	public int getFocusColor() {
		return 0;
	}

	@Override
	public IIcon getFocusDepthLayerIcon() {
		return IlluminatedItems.illuminatedArrow.getIconFromDamage(0);
	}

	@Override
	public IIcon getOrnament() {
		return IlluminatedItems.illuminatedStick.getIconFromDamage(0);
	}

	@Override
	public WandFocusAnimation getAnimation() {
		return WandFocusAnimation.CHARGE;
	}

	@Override
	public AspectList getVisCost() {
		return new AspectList().add(Aspect.AIR, 101).add(Aspect.FIRE, 101);
	}

	@Override
	public boolean isVisCostPerTick() {
		return false;
	}

	@Override
	public ItemStack onFocusRightClick(ItemStack itemstack, World world,
			EntityPlayer player, MovingObjectPosition movingobjectposition) {

		ArrowNockEvent event = new ArrowNockEvent(player, itemstack);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return event.result;
		}
		player.setItemInUse(itemstack, 72000);

		return itemstack;
	}

	@Override
	public void onUsingFocusTick(ItemStack itemstack, EntityPlayer player,
			int count) {

	}

	@Override
	public void onPlayerStoppedUsingFocus(ItemStack itemstack, World world,
			EntityPlayer player, int count) {
		int j = 72000 - count;

		ArrowLooseEvent event = new ArrowLooseEvent(player, itemstack, j);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return;
		}

		j = event.charge;


		if (player.capabilities.isCreativeMode||ThaumcraftApiHelper.consumeVisFromInventory(player,new AspectList().add(Aspect.AIR,101).add(Aspect.FIRE,101))) 
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
			EntityIlluminatedArrow arrow = new EntityIlluminatedArrow(world, player, f*2.0F);
			arrow.setDamage(0);
			arrow.setBlockToSet(TCBlocks.blockMagicalIllumination);
			arrow.setDeadOnLand(true);
			world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

			if (!world.isRemote) {
				world.spawnEntityInWorld(arrow);
			}
		}
		else
		{
			world.playSoundAtEntity(player, "thaumcraft:craftfail", 1.0F, 1.0F);
		}

	}

	@Override
	public String getSortingHelper(ItemStack itemstack) {
		return "IF00";
	}

	@Override
	public boolean onFocusBlockStartBreak(ItemStack itemstack, int x, int y,
			int z, EntityPlayer player) {
		return false;
	}

	@Override
	public boolean acceptsEnchant(int id) {
		return false;
	}

}
