package com.insane.illuminatedbows.addons.thaumcraft;

import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.insane.illuminatedbows.IlluminatedBows;
import com.insane.illuminatedbows.items.IlluminatedItems;

import cpw.mods.fml.common.registry.GameRegistry;

public class TCAddon {
	
	public static Item itemMagicBow;
	private static InfusionRecipe magicBowRecipe;
	
	private static ResourceLocation iconLocation = new ResourceLocation("illuminatedbows:textures/items/illuminatedbow_standby.png");
	private static ResourceLocation backgroundLocation = new ResourceLocation("illuminatedbows:textures/thaumcraft/bg.png");
	
	public final static String TCCATEGORY = "illuminatedBows";
	
	public static String magicBowKey = IlluminatedBows.MODID+"magicBow";
	public static AspectList magicBowTags = new AspectList().add(Aspect.LIGHT, 1).add(Aspect.WEAPON, 1);
	public static ResearchItem researchMagicBow = new ResearchItem(magicBowKey,TCCATEGORY,magicBowTags,0,0,2,iconLocation);
	public static ResearchPage pageMagicBowOne = new ResearchPage(magicBowKey,"tc.magicBow.descriptionPage");
	public static ResearchPage pageMagicBowTwo;
	
	public static void preInit()
	{
		itemMagicBow = new ItemMagicBow();
		GameRegistry.registerItem(itemMagicBow, "magicBow");
	}
	
	public static void init()
	{
		ItemStack nitorStack = ItemApi.getItem("itemResource", 1);
		ItemStack alumentumStack = ItemApi.getItem("itemResource", 0);
		ItemStack glowstoneStack = new ItemStack(Items.glowstone_dust);
		if (nitorStack != null && alumentumStack != null)
			magicBowRecipe = ThaumcraftApi.addInfusionCraftingRecipe(magicBowKey, new ItemStack(itemMagicBow),5, new AspectList().add(Aspect.AIR, 16).add(Aspect.LIGHT, 64).add(Aspect.WEAPON, 16).add(Aspect.MAGIC,16), new ItemStack(IlluminatedItems.illuminatedBow), new ItemStack[] {glowstoneStack,nitorStack,glowstoneStack,nitorStack,alumentumStack});
		pageMagicBowTwo = new ResearchPage(magicBowRecipe);
	}
	
	public static void postInit()
	{
		ResearchCategories.registerCategory(TCCATEGORY,iconLocation, backgroundLocation);
		researchMagicBow = researchMagicBow.setPages(pageMagicBowOne,pageMagicBowTwo);
		ResearchCategories.addResearch(researchMagicBow);
	}
}
