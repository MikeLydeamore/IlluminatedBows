package com.insane.illuminatedbows;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class EntityIlluminatedArrow extends EntityThrowable {

	public float strength;
	public EntityIlluminatedArrow(World par1World) {
		super(par1World);
	}

	public EntityIlluminatedArrow(World par1World, EntityLivingBase par3EntityPlayer, float j) {
		super(par1World, par3EntityPlayer);
		this.strength = j;
	}

	public EntityIlluminatedArrow(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}

	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
		//this.worldObj.setBlock((int)(this.posX), (int)(this.posY), (int)(this.posZ), IlluminatedBows.illuminatedBlockID);
		if (!this.worldObj.isRemote) {
			if (par1MovingObjectPosition.entityHit != null)	{
				par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0);
			}
			else {
                int i = par1MovingObjectPosition.blockX;
                int j = par1MovingObjectPosition.blockY;
                int k = par1MovingObjectPosition.blockZ;
                int meta = 0;
                if (canPlaceBlockAt(this.worldObj, i, j, k)) {
                    switch (par1MovingObjectPosition.sideHit) {
                        case 0:
                            --j;
                            meta = 0;
                            break;
                        case 1:
                            ++j;
                            meta = 1;
                            break;
                        case 2:
                            --k;
                            meta = 2;
                            break;
                        case 3:
                            ++k;
                            meta = 3;
                            break;
                        case 4:
                            --i;
                            meta = 4;
                            break;
                        case 5:
                            meta = 5;
                            ++i;
                    }

                    if (this.worldObj.isAirBlock(i, j, k)) {
                        this.worldObj.setBlock(i, j, k, IlluminatedBows.illuminatedBlock, meta, 2);
                        //this.worldObj.setBlockMetadataWithNotify(i,j,k,meta,2);
                        this.worldObj.playSoundAtEntity(this, "random.glass", 1.0F, 1.0F);
                    }
                }
            }
			this.setDead();
		}
	}
	/*@Override
	protected float getGravityVelocity() {
		System.out.println(-0.97F*this.strength+1.0F);
		return -0.97F*this.strength+1.0F;
	}*/

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return world.isSideSolid(x,y,z, ForgeDirection.SOUTH) || world.isSideSolid(x,y,z, ForgeDirection.EAST) ||
                world.isSideSolid(x,y,z, ForgeDirection.WEST) || world.isSideSolid(x,y,z, ForgeDirection.NORTH) ||
                world.doesBlockHaveSolidTopSurface(world, x, y, z) || (world.getBlock(x,y,z) == Blocks.leaves) ||
                (world.getBlock(x,y,z) == Blocks.glass);
    }

}
