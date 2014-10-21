package com.insane.illuminatedbows.addons.thaumcraft.blocks;


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class TCBlocks {
	
	public static Block blockMagicalIllumination;
	
	public static void preInit()
	{
		blockMagicalIllumination = new BlockMagicIllumination();
		GameRegistry.registerBlock(blockMagicalIllumination, "illuminatedMagicBlock");
	}

}
