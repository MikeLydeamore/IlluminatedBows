package com.insane.illuminatedbows;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.lang.reflect.Field;
import java.util.Random;

import com.insane.illuminatedbows.blocks.IlluminatedBlocks;

public class EntityIlluminatedArrow extends EntityArrow {

	public float strength;
    private static Field f;
    public boolean blockSpawned;
    private Block blockToSpawn;
    public boolean deadOnLand=false;
    public boolean gravity=true;

    public EntityIlluminatedArrow(World par1World) {
		super(par1World);
        blockSpawned=false;
	}

	public EntityIlluminatedArrow(World par1World, EntityLivingBase par3EntityPlayer, EntityLivingBase par4EntityPlayer, float j, float k) {
		super(par1World, par3EntityPlayer, par4EntityPlayer, j, k);
		this.strength = j;
        blockSpawned=false;
	}

	public EntityIlluminatedArrow(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
        blockSpawned=false;
	}

    public EntityIlluminatedArrow(World par1World, EntityLivingBase par2, float par3) {
        super(par1World, par2, par3);
        this.strength=par3;
    }

	protected void setIllumination(MovingObjectPosition par1MovingObjectPosition) {
            for (int count = 0; count < 20; count++) {
                this.worldObj.spawnParticle("magicCrit", this.posX, this.posY+2, this.posZ, 255, 213, 0);
            }

		if (!this.worldObj.isRemote && par1MovingObjectPosition != null) {
			if (par1MovingObjectPosition.entityHit != null)	{
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

                    if (this.worldObj.isAirBlock(i, j, k) || this.worldObj.getBlock(i, j, k).isReplaceable(worldObj, i, j, k)) {
                    	if (blockToSpawn == null)
                    	{
                    		blockToSpawn = IlluminatedBlocks.illuminatedBlock;
                    	}
                        this.worldObj.setBlock(i, j, k, blockToSpawn, meta, 2);
                        this.worldObj.playSoundAtEntity(this, "dig.glass", 1.0F, 1.0F);
                        this.setDead();
                    }
                    else
                    {
                    	if (deadOnLand==true)
                    		this.setDead();
                    }
                }
            }
			//this.setDead();
		}
	}
	/*@Override
	protected float getGravityVelocity() {
		System.out.println(-0.97F*this.strength+1.0F);
		return -0.97F*this.strength+1.0F;
	}*/
	
	public void setBlockToSet(Block block)
	{
		blockToSpawn = block;
	}
	
	public void setDeadOnLand(boolean status)
	{
		deadOnLand=status;
	}
	
	public void setGravity(boolean status)
	{
		gravity=status;
	}
	

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return world.isSideSolid(x,y,z, ForgeDirection.SOUTH) || world.isSideSolid(x,y,z, ForgeDirection.EAST) ||
                world.isSideSolid(x,y,z, ForgeDirection.WEST) || world.isSideSolid(x,y,z, ForgeDirection.NORTH) ||
                world.doesBlockHaveSolidTopSurface(world, x, y, z) || (world.getBlock(x,y,z) == Blocks.leaves) ||
                (world.getBlock(x,y,z) == Blocks.glass);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.worldObj.spawnParticle("reddust",this.posX, this.posY, this.posZ, 255, 213, 0);
        try {
            if (f==null) {
                f=ReflectionHelper.findField(EntityArrow.class, "inGround", "field_70254_i");
                f.setAccessible(true);
            }
            if (f.getBoolean(this) && !blockSpawned) {
                //this.worldObj.setBlock((int) this.posX, (int) this.posY, (int) this.posZ, IlluminatedBows.illuminatedBlock);
                //this.setDead();
                Vec3 vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
                Vec3 vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
                MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(vec31, vec3, false, true, false);
                //Block blockHit = this.worldObj.getBlock(movingobjectposition.blockX,movingobjectposition.blockY,movingobjectposition.blockZ);
                blockSpawned=true;
                this.setIllumination(movingobjectposition);

                //this.setDead();
            }
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
