package com.insane.illuminatedbows.client.particles;

import net.minecraft.world.World;
import thaumcraft.client.fx.particles.FXWisp;

public class ColourNitorFX extends FXWisp {

	public ColourNitorFX(World world, double x, double y, double z, double x2, double y2, double z2, float size, int type, boolean shrink, float gravity, int colour)
	{
		super(world, x, y, z, x2, y2, z2, size, type);

		switch(colour)
		{
		case 1: {
			this.particleRed=1F;
			this.particleBlue=0F + world.rand.nextFloat() * 0.25F;
			this.particleGreen=0F + world.rand.nextFloat() * 0.25F;
			break;
			}
		case 2: {
			
		}
		}
	}
}
