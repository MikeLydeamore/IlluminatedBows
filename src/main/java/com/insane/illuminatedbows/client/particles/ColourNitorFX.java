package com.insane.illuminatedbows.client.particles;

import net.minecraft.world.World;
import thaumcraft.client.fx.particles.FXWisp;

public class ColourNitorFX extends FXWisp {

	public ColourNitorFX(World world, double x, double y, double z, double x2, double y2, double z2, float size, int type, boolean shrink, float gravity, int colour)
	{
		super(world, x, y, z, x2, y2, z2, size, type);

		switch(colour)
		{
		case 0: { //Black
			this.particleRed=0.0F;
			this.particleBlue=0.0F;
			this.particleGreen=0.0F;
			this.particleAlpha=1F;
			break;
		}
		case 1: { //Red
			this.particleRed=1F;
			this.particleBlue=0F + world.rand.nextFloat() * 0.25F;
			this.particleGreen=0F + world.rand.nextFloat() * 0.25F;
			break;
			}
		case 2: { //Green
			this.particleRed = 0.14F + world.rand.nextFloat()*0.1F;
			this.particleBlue = 0F + world.rand.nextFloat()*0.25F;
			this.particleGreen = 0.54F;
			break;
		}
		case 3: { //Brown
			this.particleRed = 0.43F + world.rand.nextFloat() * 0.1F;
			this.particleGreen = 0.24F + world.rand.nextFloat()*0.1F;
			this.particleBlue = 0F;
			break;
		}
		case 4: { //Blue
			this.particleRed = 0F + world.rand.nextFloat() * 0.25F;
			this.particleBlue=1F;
			this.particleGreen = 0F + world.rand.nextFloat() * 0.25F;
			break;
		}
		case 5: { //Purple
			this.particleRed = 0.69F;
			this.particleGreen=0F + world.rand.nextFloat() * 0.2F;
			this.particleBlue = 0.76F + world.rand.nextFloat() * 0.1F;
			break;
		}
		case 6: { //Cyan
			this.particleRed = 0F + world.rand.nextFloat() * 0.1F;
			this.particleGreen = 0.63F + world.rand.nextFloat() * 0.2F;
			this.particleBlue = 0.63F + world.rand.nextFloat() * 0.2F;
			break;
		}
		case 7: { //Light Gray
			this.particleRed = 0.02F + world.rand.nextFloat() * 0.2F;
			this.particleGreen = 0.02F + world.rand.nextFloat() * 0.2F;
			this.particleBlue = 0.02F + world.rand.nextFloat() * 0.2F;
			break;
		}
		case 8: { //Gray
			this.particleRed = 0.44F + world.rand.nextFloat() * 0.2F;
			this.particleGreen = 0.44F + world.rand.nextFloat() * 0.2F;
			this.particleBlue = 0.44F + world.rand.nextFloat() * 0.2F;
			break;
		}
		case 9: { //Pink
			this.particleRed = 0.88F;
			this.particleGreen = 0F + world.rand.nextFloat() * 0.1F;
			this.particleBlue = 0.82F + world.rand.nextFloat() * 0.2F;
			break;
		}
		case 10: { //Lime
			this.particleRed = 0.35F + world.rand.nextFloat() * 0.2F;
			this.particleGreen = 0.90F + world.rand.nextFloat() * 0.1F;
			this.particleBlue = 0.22F + world.rand.nextFloat() * 0.1F;
			break;
		}
		case 11: { //Yellow
			this.particleRed = 0.70F + world.rand.nextFloat() * 0.1F;
			this.particleGreen = 0.70F + world.rand.nextFloat() * 0.1F;
			this.particleBlue = 0F + world.rand.nextFloat() * 0.2F;
			break;
		}
		case 12: { //Light Blue
			this.particleRed = 0F + world.rand.nextFloat()*0.2F;
			this.particleGreen = 0.57F + world.rand.nextFloat() * 0.1F;
			this.particleBlue = 0.7F + world.rand.nextFloat() * 0.1F;
			break;
		}
		case 13: { //Mangenta
			this.particleRed = 0.45F + world.rand.nextFloat() * 0.2F;
			this.particleGreen = 0F + world.rand.nextFloat() * 0.1F;
			this.particleBlue = 0.6F + world.rand.nextFloat() * 0.2F;
			break;
		}
		case 14: { //Orange
			this.particleRed = 0.67F + world.rand.nextFloat() * 0.2F;
			this.particleGreen = 0.49F + world.rand.nextFloat () * 0.1F;
			this.particleBlue = 0F + world.rand.nextFloat() * 0.1F;
			break;
		}
		case 15: { //White
			this.particleRed = 1F;
			this.particleGreen=1F;
			this.particleBlue=1F;
			break;
		}
		case 16: { //Rainbow!
			this.particleRed = world.rand.nextFloat();
			this.particleGreen = world.rand.nextFloat();
			this.particleBlue = world.rand.nextFloat();
		}
		}
	}
}
