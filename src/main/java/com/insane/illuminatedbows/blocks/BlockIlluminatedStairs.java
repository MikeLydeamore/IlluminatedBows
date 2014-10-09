package com.insane.illuminatedbows.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockIlluminatedStairs extends BlockStairs {

	public BlockIlluminatedStairs(Block par1, int par3) {
		super(par1, par3);
		this.setLightLevel(1F);
        this.setBlockName("illuminatedStairs");
	}

}
