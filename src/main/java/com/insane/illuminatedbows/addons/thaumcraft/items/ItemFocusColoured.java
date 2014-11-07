package com.insane.illuminatedbows.addons.thaumcraft.items;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import com.insane.illuminatedbows.IlluminatedBows;
import com.insane.illuminatedbows.addons.thaumcraft.blocks.TCBlocks;
import com.insane.illuminatedbows.addons.thaumcraft.tile.TileColouredNitor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.IWandFocus;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.wands.ItemWandCasting;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemFocusColoured extends Item implements IWandFocus {

	private AspectList visCost = new AspectList().add(Aspect.FIRE, 250).add(Aspect.EARTH, 250);
	private Color focusColor;
	private float hue=0;

	public ItemFocusColoured()
	{
		super();
		this.setUnlocalizedName("focusColoured");
		this.setMaxStackSize(1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("illuminatedbows:focus_coloured");
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean blah)
	{
		NBTTagCompound tag;
		if (stack.hasTagCompound())
			tag = stack.getTagCompound();
		else
			tag = new NBTTagCompound();

		list.add(StatCollector.translateToLocal("item.focusColoured.currentcolour")+": "+StatCollector.translateToLocal("colour."+tag.getInteger(IlluminatedBows.MODID+"colour")));
		list.add(StatCollector.translateToLocal("item.focus.cost"));
		for (Aspect aspect : visCost.getAspectsSorted()) {
			float amount = visCost.getAmount(aspect) / 100.0F;
			list.add(" " + '\u00a7' + aspect.getChatcolor() + aspect.getName() + '\u00a7' + "r x " + amount);
		}
		list.add("");
		list.add(StatCollector.translateToLocal("colour.shapelesscraft"));
	}

	@Override
	public int getFocusColor() {
		focusColor = Color.getHSBColor(hue, 1, 1);
		hue+=0.001;
		
		return focusColor.getRGB();
	}


	@Override
	public IIcon getFocusDepthLayerIcon() {
		return null;
	}

	@Override
	public IIcon getOrnament() {
		return null;
	}

	@Override
	public WandFocusAnimation getAnimation() {
		return WandFocusAnimation.CHARGE;
	}

	@Override
	public AspectList getVisCost() {
		return visCost.copy();
	}

	@Override
	public boolean isVisCostPerTick() {
		return false;
	}

	@Override
	public ItemStack onFocusRightClick(ItemStack itemstack, World world,
			EntityPlayer player, MovingObjectPosition mop) {
		player.swingItem();
		if (mop != null && ThaumcraftApiHelper.consumeVisFromWand(itemstack, player, visCost.copy(), true, false)) {
			int x = mop.blockX;
			int y = mop.blockY;
			int z = mop.blockZ;

			switch (mop.sideHit) {
			case (0): {
				y--;
				break;
			}
			case (1): {
				y++;
				break;
			}
			case (2): {
				z--;
				break;
			}
			case (3): {
				z++;
				break;
			}
			case (4): {
				x--;
				break;
			}
			case (5): {
				x++;
				break;
			}
			}

			if (world.getBlock(x, y, z).isReplaceable(world, x, y, z) || world.isAirBlock(x, y, z))
			{
				ItemStack focusStack = ((ItemWandCasting) itemstack.getItem()).getFocusItem(itemstack);
				NBTTagCompound tag;
				if (focusStack.hasTagCompound())
					tag = focusStack.getTagCompound();
				else
					tag = new NBTTagCompound();



				world.setBlock(x, y, z, TCBlocks.nitorColour);
				TileColouredNitor te = (TileColouredNitor) world.getTileEntity(x, y, z);
				te.setColour(getColour(focusStack));
				world.playSoundEffect(mop.blockX + 0.5D, mop.blockY + 0.5D, mop.blockZ + 0.5D, "thaumcraft:zap", 0.25F, 1.0F);
			}
		}

		return itemstack;
	}

	@Override
	public void onUsingFocusTick(ItemStack itemstack, EntityPlayer player,
			int count) {	
	}

	@Override
	public void onPlayerStoppedUsingFocus(ItemStack itemstack, World world,
			EntityPlayer player, int count) {

	}

	public int getColour(ItemStack stack)
	{
		NBTTagCompound tag;
		if (stack.hasTagCompound())
			tag=stack.getTagCompound();
		else
			tag = new NBTTagCompound();

		return tag.getInteger(IlluminatedBows.MODID+"colour");
	}

	@Override
	public String getSortingHelper(ItemStack itemstack) {
		return "CL0"+getColour(itemstack);
	}

	@Override
	public boolean onFocusBlockStartBreak(ItemStack itemstack, int x, int y,
			int z, EntityPlayer player) {
		return false;
	}

	@Override
	public boolean acceptsEnchant(int id) {
		return id==Config.enchFrugal.effectId;
	}

	@Override
	public boolean isItemTool(ItemStack stack) {
		return true;
	}

}
