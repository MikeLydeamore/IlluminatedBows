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

@Mod(modid="insane_IlluminatedBows", name="IlluminatedBows", version="1.5")//, dependencies="required-after:ThermalExpansion")
public class IlluminatedBows {

	@Mod.Instance("insane_IlluminatedBows")
	public static IlluminatedBows instance;
	@SidedProxy(clientSide="com.insane.illuminatedbows.client.ClientProxy", serverSide="com.insane.illuminatedbows.CommonProxy")
	public static CommonProxy proxy;

	public static Item illuminatedBow;
	public static Item illuminatedArrow;
	public static Item illuminatedGlow;
    public static Item illuminatedStick;
    public static Item inertBow;
    public static Item inertArrow;

	public static Block illuminatedSapling;
	public static Block illuminatedLeaf;
	public static Block illuminatedWood;
	public static Block illuminatedLeaves;
	public static Block illuminatedPlanks;
	public static Block illuminatedStairs;
    public static Block illuminatedBlock;

	
	private static List frontRecipes = new ArrayList();


	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
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
        Config.doConfig(file);

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

        illuminatedStick = new ItemIlluminatedStick();
        GameRegistry.registerItem(illuminatedStick, "illuminatedStick");

        inertBow = new ItemInertBow();
        GameRegistry.registerItem(inertBow, "inertBow");

        inertArrow = new ItemInertArrow();
        GameRegistry.registerItem(inertArrow, "inertArrow");

	}

	@Mod.EventHandler
	public void initialize(FMLInitializationEvent event) {
		proxy.registerRenderers();
		
		ItemStack plankStack = new ItemStack(illuminatedPlanks);
		GameRegistry.addShapelessRecipe(new ItemStack(illuminatedPlanks, 4), new ItemStack(illuminatedWood));
	    GameRegistry.addRecipe(new ItemStack(illuminatedStairs,4), new Object[] {"  x", " xx", "xxx", 'x', plankStack});
        ItemStack stickStack = new ItemStack(illuminatedStick);
        GameRegistry.addRecipe(new ItemStack(illuminatedStick,4), new Object[]{"x","x", 'x', plankStack});
        GameRegistry.addRecipe(new ItemStack(inertBow,1), new Object[] {" xy", "x y"," xy", 'x',illuminatedStick,'y',new ItemStack(Items.string)});
        GameRegistry.addRecipe(new ItemStack(inertArrow), new Object[] {"x","y","z", 'x', Items.flint, 'y', stickStack, 'z', Items.feather});
		
		OreDictionary.registerOre("plankWood", illuminatedPlanks);
		OreDictionary.registerOre("logWood", illuminatedWood);
        OreDictionary.registerOre("stickWood",illuminatedStick);

		/* Transpose Arrows */
		if (Loader.isModLoaded("ThermalExpansion")) {
			addTransposerFillRecipe(new ItemStack(inertArrow), new ItemStack(illuminatedArrow), new FluidStack(FluidRegistry.getFluid("glowstone"), Config.arrowLiquidAmount),
					Config.arrowEnergy);

			addTransposerFillRecipe(new ItemStack(inertBow), new ItemStack(illuminatedBow), new FluidStack(FluidRegistry.getFluid("glowstone"), Config.bowLiquidAmount),
					Config.bowEnergy);
			
			addTransposerFillRecipe(new ItemStack(Blocks.sapling, 1, 0), new ItemStack(illuminatedSapling), new FluidStack(FluidRegistry.getFluid("glowstone"), Config.saplingLiquidAmount),
					Config.saplingEnergy);
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
