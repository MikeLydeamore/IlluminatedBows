package com.insane.illuminatedbows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.insane.illuminatedbows.addons.nei.NEICompat;
import com.insane.illuminatedbows.addons.thaumcraft.TCAddon;
import com.insane.illuminatedbows.blocks.BlockIlluminatedBlock;
import com.insane.illuminatedbows.blocks.BlockIlluminatedLeaves;
import com.insane.illuminatedbows.blocks.BlockIlluminatedPlanks;
import com.insane.illuminatedbows.blocks.BlockIlluminatedSapling;
import com.insane.illuminatedbows.blocks.BlockIlluminatedSlab;
import com.insane.illuminatedbows.blocks.BlockIlluminatedStairs;
import com.insane.illuminatedbows.blocks.BlockIlluminatedWood;
import com.insane.illuminatedbows.blocks.IlluminatedBlocks;
import com.insane.illuminatedbows.items.IlluminatedItems;
import com.insane.illuminatedbows.items.ItemBlockSlab;
import com.insane.illuminatedbows.items.ItemIlluminatedArrow;
import com.insane.illuminatedbows.items.ItemIlluminatedBlock;
import com.insane.illuminatedbows.items.ItemIlluminatedBow;
import com.insane.illuminatedbows.items.ItemIlluminatedStick;
import com.insane.illuminatedbows.items.ItemInertArrow;
import com.insane.illuminatedbows.items.ItemInertBow;

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
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid=IlluminatedBows.MODID, name="IlluminatedBows", version="1.7.0b", dependencies="after:ThermalExpansion;after:Thaumcraft;after:NotEnoughItems")
public class IlluminatedBows {

	public static final String MODID = "insane_IlluminatedBows";
	@Mod.Instance(MODID)
	public static IlluminatedBows instance;
	@SidedProxy(clientSide="com.insane.illuminatedbows.client.ClientProxy", serverSide="com.insane.illuminatedbows.CommonProxy")
	public static CommonProxy proxy;


	
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
            System.out.println("Could not create configuration file for Illuminating Bows. Reason:");
            System.out.println(e);
        }
        Config.doConfig(file);

		IlluminatedBlocks.preInit();
		IlluminatedItems.preInit();
        
        if (Config.thaumModule && Loader.isModLoaded("Thaumcraft"))
        {
        	TCAddon.preInit();
        }

	}

	@Mod.EventHandler
	public void initialize(FMLInitializationEvent event) {
		proxy.registerRenderers();
		
		Crafting.addCraftingRecipesAndRegisterOres();
		
		if (Config.thaumModule && Loader.isModLoaded("Thaumcraft"))
		{
			TCAddon.init();
		}
		
		
	}

    

    @Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
    	
    	if (Config.thaumModule && Loader.isModLoaded("Thaumcraft"))
    	{
    		TCAddon.postInit();
    	}
    	if (Loader.isModLoaded("NotEnoughItems"))
    	{
    		NEICompat.hideItem(new ItemStack(IlluminatedBlocks.illuminatedBlock, 1, OreDictionary.WILDCARD_VALUE));
    	}
	}

	
	
}
