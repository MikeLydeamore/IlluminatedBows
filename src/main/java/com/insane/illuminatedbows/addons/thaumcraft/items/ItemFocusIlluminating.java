package com.insane.illuminatedbows.addons.thaumcraft.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import com.insane.illuminatedbows.EntityIlluminatedArrow;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFocusIlluminating extends ItemFocusBasic {

	public static AspectList visCost = new AspectList().add(Aspect.AIR, (int)(100*com.insane.illuminatedbows.Config.illuminatingFocusAerCost)).add(Aspect.FIRE, (int)(100*com.insane.illuminatedbows.Config.illuminatingFocusFireCost));

	public ItemFocusIlluminating()
	{
		super();
		this.setUnlocalizedName("focusIlluminating");
		this.setMaxStackSize(1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("illuminatedbows:focus_illuminating");
	}

	@Override
	public int getFocusColor(ItemStack focusStack) {
		return 0xFFFF00;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return EnumRarity.rare;
	}

	@Override
	public IIcon getFocusDepthLayerIcon(ItemStack stack) {
		return null;
	}

	@Override
	public IIcon getOrnament(ItemStack stack) {
		return null;
	}

	@Override
	public WandFocusAnimation getAnimation(ItemStack stack) {
		return WandFocusAnimation.CHARGE;
	}


	@Override
	public AspectList getVisCost(ItemStack stack) {
		return visCost.copy();
	}

	@Override
	public boolean isVisCostPerTick(ItemStack stack) {
		return false;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean blah)
	{
		list.add(StatCollector.translateToLocal("item.focus.cost"));
		for (Aspect aspect : visCost.getAspectsSorted()) {
			float amount = visCost.getAmount(aspect) / 100.0F;
			list.add(" " + '\u00a7' + aspect.getChatcolor() + aspect.getName() + '\u00a7' + "r x " + amount);
		}
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


		if (player.capabilities.isCreativeMode||ThaumcraftApiHelper.consumeVisFromWand(itemstack, player, visCost, true,false)) 
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

			EntityIlluminatedArrow arrow = new EntityIlluminatedArrow(world, player, 2.0F*f);
			arrow.setDamage(0);
			arrow.setDeadOnLand(true);
			arrow.isMagic(true);
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
	public int getItemEnchantability() {
		return 5;
	}

	@Override
	public boolean isItemTool(ItemStack stack) {
		return true;
	}



	@Override
	public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack focusstack,
			int rank) {
		// TODO Auto-generated method stub
		return null;
	}

}
