package com.insane.illuminatedbows.addons.thaumcraft.items;

import com.insane.illuminatedbows.addons.thaumcraft.blocks.TCBlocks;
import com.insane.illuminatedbows.addons.thaumcraft.tile.TileColouredNitor;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.IWandFocus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemFocusColoured extends Item implements IWandFocus {

	private AspectList visCost = new AspectList().add(Aspect.FIRE, 100).add(Aspect.EARTH, 100);

	public ItemFocusColoured()
	{
		super();
		this.setUnlocalizedName("focusColoured");
		this.setMaxStackSize(1);
	}
	@Override
	public int getFocusColor() {
		return 0;
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
		if (mop != null) {
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
				world.setBlock(x, y, z, TCBlocks.nitorColour);
				TileColouredNitor te = (TileColouredNitor) world.getTileEntity(x, y, z);
				te.setColour(1);
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

	@Override
	public String getSortingHelper(ItemStack itemstack) {
		return "CL00";
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
