package com.insane.illuminatedbows.addons.thaumcraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.insane.illuminatedbows.blocks.BlockIlluminatedBlock;

public class BlockMagicIllumination extends BlockIlluminatedBlock {
	
	public BlockMagicIllumination() {
		super();
		this.setBlockName("illuminatedMagicBlock");
		this.setLightLevel(1F);
		this.isOpaqueCube();
		this.setHardness(0.2F);
		this.setStepSound(Block.soundTypeGlass);
	}
	
	@Override
	 public int quantityDropped(Random par1Random)
	 {
		 return 0;
	 }


}
