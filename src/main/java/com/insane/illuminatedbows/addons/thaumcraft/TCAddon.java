package com.insane.illuminatedbows.addons.thaumcraft;

import org.apache.commons.lang3.StringUtils;

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
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.insane.illuminatedbows.IlluminatedBows;
import com.insane.illuminatedbows.addons.thaumcraft.blocks.TCBlocks;
import com.insane.illuminatedbows.addons.thaumcraft.items.TCItems;
import com.insane.illuminatedbows.items.IlluminatedItems;

import cpw.mods.fml.common.registry.GameRegistry;

public class TCAddon {
	

	private static InfusionRecipe magicBowRecipe;
	private static InfusionRecipe focusRecipe;
	private static InfusionRecipe colouredFocusRecipe;
	

	
	private static ResourceLocation iconLocation = new ResourceLocation("illuminatedbows:textures/items/illuminatedbow_standby.png");
	private static ResourceLocation bowIconLocation = new ResourceLocation("illuminatedbows:textures/items/visbow_standby.png");
	private static ResourceLocation focusIconLocation = new ResourceLocation("illuminatedbows:textures/items/focus_illuminating.png");
	private static ResourceLocation backgroundLocation = new ResourceLocation("illuminatedbows:textures/thaumcraft/bg.png");
	private static ResourceLocation focusColourLocation = new ResourceLocation("illuminatedbows:textures/items/focus_coloured.png"); 
	
	public final static String TCCATEGORY = "illuminatedBows";
	
	public static String magicBowKey = IlluminatedBows.MODID+"magicBow";
	public static AspectList magicBowTags = new AspectList().add(Aspect.LIGHT, 1).add(Aspect.WEAPON, 1);
	public static ResearchItem researchMagicBow = new ResearchItem(magicBowKey,TCCATEGORY,magicBowTags,0,0,2,bowIconLocation);
	public static ResearchPage pageMagicBowOne = new ResearchPage(magicBowKey,"tc.magicBow.descriptionPage");
	public static ResearchPage pageMagicBowTwo;
	
	public static String illuminatingFocusKey = IlluminatedBows.MODID+"illuminatingFocus";
	public static ResearchItem researchFocus = new ResearchItem(illuminatingFocusKey,TCCATEGORY,new AspectList().add(Aspect.LIGHT,1).add(Aspect.MAGIC, 1).add(Aspect.WEAPON,1),-2,2,2,focusIconLocation);
	public static ResearchPage pageFocusOne = new ResearchPage(illuminatingFocusKey,"tc.focusIlluminating.descriptionPage");
	public static ResearchPage pageFocusTwo;
	
	public static String colouredFocusKey = IlluminatedBows.MODID+"colouredFocus";
	public static ResearchItem researchColouredFocus = new ResearchItem(colouredFocusKey,TCCATEGORY,new AspectList().add(Aspect.LIGHT,1).add(Aspect.MAGIC,1).add(Aspect.FIRE, 1).add(Aspect.AURA,1), 2, 2, 2,focusColourLocation);
	public static ResearchPage pageColouredFocusOne = new ResearchPage(colouredFocusKey,"tc.focusColoured.descriptionPage");
	public static ResearchPage pageColouredFocusTwo;
	public static ResearchPage pageColouredFocusThree;
	
	
	public static void preInit()
	{
		TCItems.preInit();
		TCBlocks.preInit();

	}
	
	public static void init()
	{
		ItemStack nitorStack = ItemApi.getItem("itemResource", 1);
		ItemStack alumentumStack = ItemApi.getItem("itemResource", 0);
		ItemStack glowstoneStack = new ItemStack(Items.glowstone_dust);
		ItemStack flameFocus = ItemApi.getItem("itemFocusFire",0);
		if (nitorStack != null && alumentumStack != null)
		{
			magicBowRecipe = ThaumcraftApi.addInfusionCraftingRecipe(magicBowKey, new ItemStack(TCItems.itemMagicBow),5, new AspectList().add(Aspect.AIR, 16).add(Aspect.LIGHT, 64).add(Aspect.WEAPON, 16).add(Aspect.MAGIC,16), new ItemStack(IlluminatedItems.illuminatedBow), new ItemStack[] {glowstoneStack,nitorStack,glowstoneStack,nitorStack,alumentumStack});
			focusRecipe = ThaumcraftApi.addInfusionCraftingRecipe(illuminatingFocusKey, new ItemStack(TCItems.itemFocusIlluminating),5, new AspectList().add(Aspect.LIGHT, 32).add(Aspect.MAGIC, 8), flameFocus, new ItemStack[] {new ItemStack(TCItems.itemMagicBow), new ItemStack(Items.arrow),glowstoneStack, new ItemStack(Items.feather)});
			colouredFocusRecipe = ThaumcraftApi.addInfusionCraftingRecipe(colouredFocusKey, new ItemStack(TCItems.itemFocusColoured), 5, new AspectList().add(Aspect.AIR,16).add(Aspect.ELDRITCH,8).add(Aspect.SENSES,32).add(Aspect.LIGHT,64), new ItemStack(TCItems.itemFocusIlluminating), new ItemStack[] {nitorStack, new ItemStack(Items.dye,1,15), nitorStack, new ItemStack(Items.dye,1,1), nitorStack, new ItemStack(Items.dye,1,2), nitorStack, new ItemStack(Items.dye,1,4)});
		}
		pageMagicBowTwo = new ResearchPage(magicBowRecipe);
		pageFocusTwo = new ResearchPage(focusRecipe);
		pageColouredFocusTwo = new ResearchPage(colouredFocusRecipe);

		//CraftingManager.getInstance().getRecipeList().add(new FocusColourCrafting());
		
		
		for (int i=0; i<16; i++)
		{
			ItemStack colourStack = new ItemStack(TCItems.itemFocusColoured, 1, 0);
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger(IlluminatedBows.MODID+"colour", i);
			colourStack.setTagCompound(tag);
			String dye;
			if (i != 7)
				dye = "dye"+StringUtils.capitalize(ItemDye.field_150923_a[i]);
			else
				dye = "dyeLightGray";
			GameRegistry.addRecipe(new ShapelessOreRecipe(colourStack, new Object[]{new ItemStack(TCItems.itemFocusColoured, 1, 0), dye}));
		}
		
	}
	
	public static void postInit()
	{
		String[] magicBowParents = {"NITOR","ALUMENTUM","INFUSION"};
		researchMagicBow.setParents(magicBowParents);
		ResearchCategories.registerCategory(TCCATEGORY,iconLocation, backgroundLocation);
		researchMagicBow = researchMagicBow.setPages(pageMagicBowOne,pageMagicBowTwo);
		ResearchCategories.addResearch(researchMagicBow);
		
		String[] focusParents = {magicBowKey,"FOCUSFIRE","INFUSION"};
		researchFocus.setParents(focusParents);
		researchFocus = researchFocus.setPages(pageFocusOne,pageFocusTwo);
		ResearchCategories.addResearch(researchFocus);
		
		String[] colouredFocusParents = {magicBowKey, "FOCUSFIRE","INFUSION",illuminatingFocusKey};
		researchColouredFocus.setParents(colouredFocusParents);
		researchColouredFocus = researchColouredFocus.setPages(pageColouredFocusOne, pageColouredFocusTwo);
		ResearchCategories.addResearch(researchColouredFocus);
	}
}
