package com.insane.illuminatedbows;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockIlluminatedStairs extends BlockStairs {

	protected BlockIlluminatedStairs(Block par1, int par3) {
		super(par1, par3);
		this.setLightLevel(0.86F);
        this.setBlockName("illuminatedStairs");
	}

}
