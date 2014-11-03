package com.insane.illuminatedbows.addons.thaumcraft.blocks;

import com.insane.illuminatedbows.addons.thaumcraft.tile.TileColouredNitor;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class TCBlocks {
	
	public static Block nitorColour;
	
	public static void preInit() 
	{
		nitorColour = new BlockNitorColour();
		GameRegistry.registerBlock(nitorColour,"nitorColour");
		GameRegistry.registerTileEntity(TileColouredNitor.class, "colournitor");
		
	}

}
