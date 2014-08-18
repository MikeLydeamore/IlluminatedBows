package com.insane.illuminatedbows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid="insane_IlluminatedBows", name="IlluminatedBows", version="1.5", dependencies="required-after:ThermalExpansion")
public class IlluminatedBows {

	@Mod.Instance("insane_IlluminatedBows")
	public static IlluminatedBows instance;
	@SidedProxy(clientSide="com.insane.illuminatedbows.client.ClientProxy", serverSide="com.insane.illuminatedbows.CommonProxy")
	public static CommonProxy proxy;

	public static Item illuminatedBow;
	public static Item illuminatedArrow;
	public static Item illuminatedGlow;
	public static Block illuminatedBlock;

	public static Block illuminatedSapling;
	public static Block illuminatedLeaf;
	public static Block illuminatedWood;
	public static Block illuminatedLeaves;
	public static Block illuminatedPlanks;
	public static Block illuminatedStairs;

	public static boolean bowTakesDamage;
	public static int arrowEnergy;
	public static int bowEnergy;
	public static int saplingEnergy;
	public static int arrowLiquidAmount;
	public static int bowLiquidAmount;
	public static int saplingLiquidAmount;
	public static int boneMealChance;
	
	public static boolean craftStairs;
	
	private static List frontRecipes = new ArrayList();


	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		/*Config */
		File file = new File(event.getModConfigurationDirectory() + "/IlluminatedBows.txt");
		try
		{
			file.createNewFile();
		}
		catch (IOException e)
		{
			System.out.println("Could not create configuration file for Minecraft Prayers to Cluckington. Reason:");
			System.out.println(e);
		}
		Configuration config = new Configuration(file);
		config.load();

		bowTakesDamage = config.get("general", "BowTakesDamage", true).getBoolean(true);
		arrowEnergy = config.get("general", "ArrowTransposeEnergy", 1000, "Amount of energy (RF) required to make Arrow").getInt(1000);
		bowEnergy = config.get("general", "BowTransposeEnergy", 8000, "Amount of energy (RF) required to make Bow").getInt(8000);
		saplingEnergy = config.get("general", "SaplingTransposeEnergy",1000, "Amount of energy (RF) required to make Sapling").getInt(1000);
		arrowLiquidAmount = config.get("general","ArrowLiquidAmount",250, "Amount (in mB) of Energized Glowstone to create Arrow").getInt(250);
		bowLiquidAmount = config.get("general","BowLiquidAmount",2000, "Amount (in mB) of Energized Glowstone to create Bow").getInt(2000);
		saplingLiquidAmount = config.get("general","SaplingLiquidAmount",500, "Amount (in mB) of Energized Glowstone to create Sapling").getInt(500);
		boneMealChance = config.get("general", "BoneMealChance",3, "Chance for bonemeal to work (1 in n)").getInt(3);
		craftStairs = config.get("general","CraftStair",true,"Able to craft illuminated stairs").getBoolean(true);
		config.save();
		/* End Config */

		illuminatedBow = new ItemIlluminatedBow();
		GameRegistry.registerItem(illuminatedBow, "illuminatedBow");
		LanguageRegistry.addName(illuminatedBow, "Illuminating Bow");

		EntityRegistry.registerModEntity(EntityIlluminatedArrow.class, "Illuminated Bow", 0, this, 64, 10, true);

		illuminatedArrow = new ItemIlluminatedArrow();
		GameRegistry.registerItem(illuminatedArrow, "illuminatedArrow");
		LanguageRegistry.addName(illuminatedArrow, "Illuminating Arrow");

		illuminatedGlow = new ItemGlowEffect();
		GameRegistry.registerItem(illuminatedGlow, "glowEffect");
		LanguageRegistry.addName(illuminatedGlow, "Illuminating Glow");

		illuminatedBlock = new BlockIlluminatedBlock();
		GameRegistry.registerBlock(illuminatedBlock, ItemIlluminatedBlock.class, "illuminatedBlock");
		for (int i=0; i<6; i++) {
			LanguageRegistry.addName(new ItemStack(illuminatedBlock, 1, i), "Illumination"+i);
		}

		illuminatedSapling = new BlockIlluminatedSapling();
		MinecraftForge.EVENT_BUS.register(illuminatedSapling);
		GameRegistry.registerBlock(illuminatedSapling, "illuminatedSapling");
		LanguageRegistry.addName(illuminatedSapling, "Illuminated Sapling");
		
		illuminatedWood = new BlockIlluminatedWood();
		GameRegistry.registerBlock(illuminatedWood, "illuminatedWood");
		LanguageRegistry.addName(illuminatedWood, "Illuminated Wood");
		
		illuminatedLeaves = new BlockIlluminatedLeaves();
        System.out.println("[ILLUMINATING BOWS]: "+illuminatedLeaves.getUnlocalizedName());
		GameRegistry.registerBlock(illuminatedLeaves, "illuminatedLeaves");
		LanguageRegistry.addName(illuminatedLeaves, "Illuminated Leaves");
		
		illuminatedPlanks = new BlockIlluminatedPlanks();
		GameRegistry.registerBlock(illuminatedPlanks, "Illuminated Planks");
		LanguageRegistry.addName(illuminatedPlanks, "Illuminated Planks");
		
		illuminatedStairs = new BlockIlluminatedStairs(illuminatedPlanks, 0);
		GameRegistry.registerBlock(illuminatedStairs, "illuminatedStairs");
		LanguageRegistry.addName(illuminatedStairs, "Illuminated Stairs");

	}

	@Mod.EventHandler
	public void initialize(FMLInitializationEvent event) {
		proxy.registerRenderers();
		
		ItemStack plankStack = new ItemStack(illuminatedPlanks);
		GameRegistry.addShapelessRecipe(new ItemStack(illuminatedPlanks, 4), new ItemStack(illuminatedWood));
		if (craftStairs) {
			GameRegistry.addRecipe(new ItemStack(illuminatedStairs,4), new Object[] {"  x", " xx", "xxx", 'x', plankStack});
		}
		
		OreDictionary.registerOre("plankWood", illuminatedPlanks);
		OreDictionary.registerOre("logWood", illuminatedWood);
		/* Transpose Arrows */
		if (Loader.isModLoaded("ThermalExpansion")) {
			addTransposerFillRecipe(new ItemStack(Items.arrow), new ItemStack(illuminatedArrow), new FluidStack(FluidRegistry.getFluid("glowstone"), arrowLiquidAmount),
					arrowEnergy);

			addTransposerFillRecipe(new ItemStack(Items.bow), new ItemStack(illuminatedBow), new FluidStack(FluidRegistry.getFluid("glowstone"), bowLiquidAmount),
					bowEnergy);
			
			addTransposerFillRecipe(new ItemStack(Blocks.sapling, 1, 0), new ItemStack(illuminatedSapling), new FluidStack(FluidRegistry.getFluid("glowstone"), saplingLiquidAmount),
					saplingEnergy);
		}
		
		
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

	private void addTransposerFillRecipe(ItemStack input, ItemStack output, FluidStack fluid, int energy) {
		NBTTagCompound toSend = new NBTTagCompound();
		toSend.setInteger("energy", energy);
		toSend.setTag("input", new NBTTagCompound());
		toSend.setTag("output", new NBTTagCompound());
		toSend.setTag("fluid", new NBTTagCompound());

		input.writeToNBT(toSend.getCompoundTag("input"));
		output.writeToNBT(toSend.getCompoundTag("output"));
		toSend.setBoolean("reversible", false);
		fluid.writeToNBT(toSend.getCompoundTag("fluid"));
		FMLInterModComms.sendMessage("ThermalExpansion", "TransposerFillRecipe", toSend);
	}
	
}
