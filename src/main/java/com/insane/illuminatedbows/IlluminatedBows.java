package com.insane.illuminatedbows;

import java.io.File;
import java.io.IOException;
import com.insane.illuminatedbows.addons.nei.NEICompat;
import com.insane.illuminatedbows.addons.thaumcraft.TCAddon;
import com.insane.illuminatedbows.blocks.IlluminatedBlocks;
import com.insane.illuminatedbows.items.IlluminatedItems;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid=IlluminatedBows.MODID, name="IlluminatedBows", version="1.7.0b", dependencies="after:ThermalExpansion;after:Thaumcraft;after:NotEnoughItems")
public class IlluminatedBows {

	public static final String MODID = "insane_IlluminatedBows";
	@Mod.Instance(MODID)
	public static IlluminatedBows instance;
	@SidedProxy(clientSide="com.insane.illuminatedbows.client.ClientProxy", serverSide="com.insane.illuminatedbows.CommonProxy")
	public static CommonProxy proxy;

	public static int renderIdIllumination;

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
    		NEICompat.hideItem(new ItemStack(IlluminatedItems.inventoryManager, 1, OreDictionary.WILDCARD_VALUE));
    	}
	}	
}
