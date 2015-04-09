package com.insane.illuminatedbows;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.insane.illuminatedbows.blocks.IlluminatedBlocks;
import com.insane.illuminatedbows.items.IlluminatedItems;
import com.insane.illuminatedbows.util.ThermalExpansionUtil;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class Crafting {

	public static void addCraftingRecipesAndRegisterOres()
	{
		ItemStack plankStack = new ItemStack(IlluminatedBlocks.illuminatedPlanks);
		GameRegistry.addShapelessRecipe(new ItemStack(IlluminatedBlocks.illuminatedPlanks, 4), new ItemStack(IlluminatedBlocks.illuminatedWood));
		GameRegistry.addRecipe(new ItemStack(IlluminatedBlocks.illuminatedStairs,4), new Object[] {"x  ", "xx ", "xxx", 'x', plankStack});
		ItemStack stickStack = new ItemStack(IlluminatedItems.illuminatedStick);
		GameRegistry.addRecipe(new ItemStack(IlluminatedItems.illuminatedStick,4), new Object[]{"x "," x", 'x', plankStack});
		GameRegistry.addRecipe(new ItemStack(IlluminatedItems.inertBow,1), new Object[] {" xy", "x y"," xy", 'x',IlluminatedItems.illuminatedStick,'y',new ItemStack(Items.string)});
		GameRegistry.addRecipe(new ItemStack(IlluminatedItems.inertArrow,4), new Object[] {"x","y","z", 'x', Items.flint, 'y', stickStack, 'z', Items.feather});
		GameRegistry.addRecipe(new ItemStack(IlluminatedBlocks.illuminatedSlab,6), new Object[]{"xxx",'x',plankStack});

		OreDictionary.registerOre("plankWood", IlluminatedBlocks.illuminatedPlanks);
		OreDictionary.registerOre("logWood", IlluminatedBlocks.illuminatedWood);
		OreDictionary.registerOre("stickWood",IlluminatedItems.illuminatedStick);
		OreDictionary.registerOre("slabWood",IlluminatedBlocks.illuminatedSlab);

		if (Loader.isModLoaded("ThermalExpansion") && Config.thermalExpansion) 
		{
			Fluid glowStoneFluid = FluidRegistry.getFluid("glowstone");
			if (glowStoneFluid!=null) 
			{
				ThermalExpansionUtil.addTransposerFillRecipe(new ItemStack(IlluminatedItems.inertArrow), new ItemStack(IlluminatedItems.illuminatedArrow), new FluidStack(FluidRegistry.getFluid("glowstone"), Config.arrowLiquidAmount),
						Config.arrowEnergy);
				ThermalExpansionUtil.addTransposerFillRecipe(new ItemStack(IlluminatedItems.inertBow), new ItemStack(IlluminatedItems.illuminatedBow), new FluidStack(FluidRegistry.getFluid("glowstone"), Config.bowLiquidAmount),
						Config.bowEnergy);

				ThermalExpansionUtil.addTransposerFillRecipe(new ItemStack(Blocks.sapling, 1, 0), new ItemStack(IlluminatedBlocks.illuminatedSapling), new FluidStack(FluidRegistry.getFluid("glowstone"), Config.saplingLiquidAmount),
						Config.saplingEnergy);

				ThermalExpansionUtil.addSawmillRecipeWithChance(new ItemStack(IlluminatedBlocks.illuminatedWood), new ItemStack(IlluminatedBlocks.illuminatedPlanks, 6), new ItemStack(Items.glowstone_dust), Config.glowstonePlankChance, Config.sawmillPlankEnergy);
			}
		}
		else
		{
			GameRegistry.addRecipe(new ItemStack(IlluminatedItems.illuminatedArrow), new Object[] {"x","y","x",'x',Items.glowstone_dust,'y',IlluminatedItems.inertArrow});
			GameRegistry.addRecipe(new ItemStack(IlluminatedItems.illuminatedBow), new Object[] {"xxx","xyx","xxx",'x',Items.glowstone_dust,'y',IlluminatedItems.inertBow});
			GameRegistry.addRecipe(new ItemStack(IlluminatedBlocks.illuminatedSapling), new Object[] {"x x","xyx","x x",'x',Items.glowstone_dust,'y',Blocks.sapling});
		}
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(IlluminatedItems.illuminationCleanser), new Object[]{"  x","yy ","yy ",'x',"ingotIron",'y',new ItemStack(Blocks.stained_hardened_clay,1,14)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(IlluminatedItems.inventoryManager, 1, 0), new Object[]{"x x"," y ","x x",'x',"nuggetIron",'y',"plankWood"}));
		
		GameRegistry.addRecipe(new ItemStack(IlluminatedItems.illuminatedPotathoe), new Object[]{" aa"," b ", "b  ", 'a', Items.potato, 'b', IlluminatedItems.illuminatedStick});
	}
}

