package com.insane.illuminatedbows.addons.thaumcraft.items;

import java.awt.Color;
import java.util.List;
import com.insane.illuminatedbows.IlluminatedBows;
import com.insane.illuminatedbows.addons.thaumcraft.blocks.TCBlocks;
import com.insane.illuminatedbows.addons.thaumcraft.tile.TileColouredNitor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.items.wands.ItemWandCasting;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemFocusColoured extends ItemFocusBasic {

	private int fireCost = 250;
	private int earthCost = 250;
	private AspectList visCost = new AspectList().add(Aspect.FIRE, fireCost).add(Aspect.EARTH, earthCost);
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

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int par1) {
		return this.itemIcon;
	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int pass)
	{
		int colour = this.getColour(stack);
		if (colour < 16)
			return ItemDye.field_150922_c[colour];
		else
			return Color.YELLOW.getRGB();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean blah)
	{
		NBTTagCompound tag;
		if (stack.hasTagCompound())
			tag = stack.getTagCompound();
		else
			tag = new NBTTagCompound();

		list.add(StatCollector.translateToLocal("item.focusColoured.currentcolour")+": "+StatCollector.translateToLocal("colour."+tag.getInteger(IlluminatedBows.MODID+"colour")));
		super.addInformation(stack, player, list, blah);
		list.add(StatCollector.translateToLocal("colour.shapelesscraft"));
	}

	@Override
	public int getFocusColor(ItemStack focusStack) {
		focusColor = Color.getHSBColor(hue, 1, 1);
		hue+=0.001;

		return focusColor.getRGB();
	}


	@Override
	public IIcon getFocusDepthLayerIcon(ItemStack focusStack) {
		return null;
	}

	@Override
	public IIcon getOrnament(ItemStack focusStack) {
		return null;
	}

	@Override
	public WandFocusAnimation getAnimation(ItemStack focusStack) {
		return WandFocusAnimation.CHARGE;
	}

	@Override
	public AspectList getVisCost(ItemStack focusStack) 
	{

		return visCost.copy();
	}

	@Override
	public boolean isVisCostPerTick(ItemStack focusStack) {
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
	public boolean isItemTool(ItemStack stack) {
		return true;
	}


	@Override
	public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack focusstack,
			int rank) {
		if (rank < 5)
			return new FocusUpgradeType[]{FocusUpgradeType.frugal};
		
		return null;
	}

}
