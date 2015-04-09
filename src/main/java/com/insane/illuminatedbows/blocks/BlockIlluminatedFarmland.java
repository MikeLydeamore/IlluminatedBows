package com.insane.illuminatedbows.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockIlluminatedFarmland extends BlockFarmland {

	public BlockIlluminatedFarmland()
	{
		super();
		this.setBlockName("illuminatedFarmland");
		this.setLightLevel(0.875f);
		this.setStepSound(soundTypeGravel);
		this.setHarvestLevel("shovel", 0);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return Blocks.farmland.getIcon(side, meta);
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
	{
		Block plant = plantable.getPlant(world, x, y + 1, z);
		EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);

		switch (plantType)
		{
		case Desert:
			return false;
		case Nether:
			return false;
		case Crop:
			return true;
		case Cave:
			return false;
		case Plains:
			return false;
		case Water:
			return false;
		case Beach:
			return false;
		}

		return false;
	}

}
