package com.insane.illuminatedbows.blocks;

import com.insane.illuminatedbows.Config;
import com.insane.illuminatedbows.EntityIlluminatedArrow;
import com.insane.illuminatedbows.IlluminatedBows;
import com.insane.illuminatedbows.items.ItemBlockSlab;
import com.insane.illuminatedbows.items.ItemIlluminatedArrow;
import com.insane.illuminatedbows.items.ItemIlluminatedBlock;
import com.insane.illuminatedbows.items.ItemIlluminatedBow;
import com.insane.illuminatedbows.items.ItemIlluminatedStick;
import com.insane.illuminatedbows.items.ItemInertArrow;
import com.insane.illuminatedbows.items.ItemInertBow;
import com.insane.illuminatedbows.tile.TileIllumination;
import com.insane.illuminatedbows.util.ThermalExpansionUtil;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class IlluminatedBlocks {


	public static Block illuminatedSapling;
	public static Block illuminatedWood;
	public static Block illuminatedLeaves;
	public static Block illuminatedPlanks;
	public static Block illuminatedStairs;
	public static Block illuminatedBlock;
	public static Block illuminatedSlab;

	public IlluminatedBlocks() {}

	public static void preInit()
	{		
		illuminatedBlock = new BlockIlluminatedBlock();
		GameRegistry.registerBlock(illuminatedBlock, ItemIlluminatedBlock.class, "illuminatedBlock");
		GameRegistry.registerTileEntity(TileIllumination.class, "illumination");
		
		illuminatedSapling = new BlockIlluminatedSapling();
		MinecraftForge.EVENT_BUS.register(illuminatedSapling);
		GameRegistry.registerBlock(illuminatedSapling, "illuminatedSapling");

		illuminatedWood = new BlockIlluminatedWood();
		GameRegistry.registerBlock(illuminatedWood, "illuminatedWood");

		illuminatedLeaves = new BlockIlluminatedLeaves();
		GameRegistry.registerBlock(illuminatedLeaves, "illuminatedLeaves");

		illuminatedPlanks = new BlockIlluminatedPlanks();
		GameRegistry.registerBlock(illuminatedPlanks, "illuminatedPlanks");

		illuminatedSlab = new BlockIlluminatedSlab(false);
		GameRegistry.registerBlock(illuminatedSlab, ItemBlockSlab.class, "illuminatedSlab");

		illuminatedStairs = new BlockIlluminatedStairs(illuminatedPlanks, 0);
		GameRegistry.registerBlock(illuminatedStairs, "illuminatedStairs");


	}


}
