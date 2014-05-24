package illuminatedbows;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class BlockIlluminatedSapling extends BlockFlower {
	
	private Random boneRand = new Random();
	protected BlockIlluminatedSapling(int par1) {
		super(par1);
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setStepSound(Block.soundGrassFootstep);
		this.setLightValue(0.86F);
	}

	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!par1World.isRemote)
		{
			super.updateTick(par1World, par2, par3, par4, par5Random);

			if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0)
			{
				this.growTree(par1World, par2, par3, par4);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("illuminatedbows:illuminatedsapling");
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return this.blockIcon;
	}

	@ForgeSubscribe
	public void onUseBonemeal(BonemealEvent event)
	{
		if (event.ID==IlluminatedBows.illuminatedSaplingID) {
			if (boneRand.nextInt(IlluminatedBows.boneMealChance)==0) {
				if (!event.world.isRemote) {
					this.growTree(event.world, event.X, event.Y, event.Z);
				}
			}
			event.setResult(Result.ALLOW);
		}

	}

	private void growTree(World par1World, int par2, int par3, int par4) {
		//Check for space around tree
		boolean space=true;
		for (int i=1; i<8; i++) { //Space for the wood blocks.
			if (!par1World.isAirBlock(par2, par3+i, par4)) {
				space=false;
				break;
			}
		}

		if (space) { //Generate tree
			par1World.setBlock(par2,par3,par4, IlluminatedBows.illuminatedWoodID);
			for (int i=1; i<6; i++) {
				if (par1World.isAirBlock(par2, par3+i, par4)) {
				par1World.setBlock(par2, par3+i, par4, IlluminatedBows.illuminatedWoodID);
				}
			}
			par1World.setBlock(par2,par3+6,par4,IlluminatedBows.illuminatedLeavesID); //Top Leaf
			for (int i=3; i<7; i=i+2) { //Thin leaf layers
				if (par1World.isAirBlock(par2+1, par3+i, par4)) {
					par1World.setBlock(par2+1,par3+i, par4, IlluminatedBows.illuminatedLeavesID);
				} if (par1World.isAirBlock(par2-1, par3+i, par4)) {
					par1World.setBlock(par2-1,par3+i, par4, IlluminatedBows.illuminatedLeavesID);
				} if (par1World.isAirBlock(par2, par3+i, par4+1)) {
					par1World.setBlock(par2,par3+i, par4+1, IlluminatedBows.illuminatedLeavesID);
				} if (par1World.isAirBlock(par2, par3+i, par4-1)) {
					par1World.setBlock(par2,par3+i, par4-1, IlluminatedBows.illuminatedLeavesID);
				}
			}
			for (int i=2; i<6; i=i+2) {
				for (int z=-1; z<=1; z++) {
					if (par1World.isAirBlock(par2-2, par3+i, par4+z)) {
						par1World.setBlock(par2-2,par3+i,par4+z, IlluminatedBows.illuminatedLeavesID);
					}
				}
				for (int z=-2; z<=2; z++) {
					if (par1World.isAirBlock(par2-1, par3+i, par4+z)) {
						par1World.setBlock(par2-1,par3+i,par4+z, IlluminatedBows.illuminatedLeavesID);
					}
				}
				for (int z=-2; z<=2; z++) {
					if (z!=0) {
						if (par1World.isAirBlock(par2, par3+i, par4+z)) {
							par1World.setBlock(par2,par3+i,par4+z, IlluminatedBows.illuminatedLeavesID);
						}
					}
				}
				for (int z=-2; z<=2; z++) {
					if (par1World.isAirBlock(par2+1, par3+i, par4+z)) {
						par1World.setBlock(par2+1,par3+i,par4+z, IlluminatedBows.illuminatedLeavesID);
					}
				}
				for (int z=-1; z<=1; z++) {
					if (par1World.isAirBlock(par2+2, par3+i, par4+z)) {
						par1World.setBlock(par2+2,par3+i,par4+z, IlluminatedBows.illuminatedLeavesID);
					}
				}
				
			}
		}
	}

}
