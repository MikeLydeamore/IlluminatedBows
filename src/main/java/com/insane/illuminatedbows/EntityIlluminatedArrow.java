package com.insane.illuminatedbows;

import java.lang.reflect.Field;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.insane.illuminatedbows.blocks.BlockIlluminatedBlock;
import com.insane.illuminatedbows.blocks.IlluminatedBlocks;
import com.insane.illuminatedbows.tile.TileIllumination;

import cpw.mods.fml.relauncher.ReflectionHelper;

public class EntityIlluminatedArrow extends EntityArrow {

	public float strength;
	private static Field f;
	public boolean blockSpawned;
	private Block blockToSpawn;
	public boolean deadOnLand=false;
	public boolean gravity=true;
	private float initYaw;
	public boolean magic;

	public EntityIlluminatedArrow(World par1World) {
		super(par1World);
		blockSpawned=false;
		initYaw = this.rotationYaw;
	}

	public EntityIlluminatedArrow(World par1World, EntityLivingBase par3EntityPlayer, EntityLivingBase par4EntityPlayer, float j, float k) {
		super(par1World, par3EntityPlayer, par4EntityPlayer, j, k);
		this.strength = j;
		blockSpawned=false;
		initYaw = this.rotationYaw;
	}

	public EntityIlluminatedArrow(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
		blockSpawned=false;
		initYaw = this.rotationYaw;
	}

	public EntityIlluminatedArrow(World par1World, EntityLivingBase par2, float par3) {
		super(par1World, par2, par3);
		this.strength=par3;
		initYaw = this.rotationYaw;
	}

	protected void setIllumination(MovingObjectPosition par1MovingObjectPosition) {
		for (int count = 0; count < 20; count++) {
			this.worldObj.spawnParticle("magicCrit", this.posX, this.posY+2, this.posZ, 255, 213, 0);
		}

		
		if (!this.worldObj.isRemote && par1MovingObjectPosition != null) {
			if (par1MovingObjectPosition.entityHit != null)	{
				if (magic)
					this.setDead();
			}
			else {
				int x = par1MovingObjectPosition.blockX;
				int y = par1MovingObjectPosition.blockY;
				int z = par1MovingObjectPosition.blockZ;
                
				int meta = par1MovingObjectPosition.sideHit;
                
                if (magic)
                    meta += 6;  

                Block block = worldObj.getBlock(x, y, z);
                
                if ((block.getRenderType() == 0 || block.isOpaqueCube() || block.isNormalCube()) && worldObj.getTileEntity(x, y, z) == null)
                {
                    int blockMeta = worldObj.getBlockMetadata(x, y, z);
                    this.worldObj.setBlock(x, y, z, IlluminatedBlocks.illuminatedBlock, blockMeta, 3);
                    ((TileIllumination) worldObj.getTileEntity(x, y, z)).init(block, meta);
                    this.worldObj.playSoundAtEntity(this, "dig.glass", 1.0F, 1.0F);
                    this.worldObj.markBlockForUpdate(x, y, z);
                    this.setDead();
                }
                else if (block instanceof BlockIlluminatedBlock)
                {
                    TileIllumination te = (TileIllumination) worldObj.getTileEntity(x, y, z);
                    if (!te.sides.contains(meta))
                    {
                        te.sides.add(meta);
                        this.worldObj.playSoundAtEntity(this, "dig.glass", 1.0F, 1.0F);
                        this.worldObj.markBlockForUpdate(x, y, z);
                        this.setDead();
                    }
                }

                if (magic)
                    this.setDead();
            }
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

	public void isMagic(boolean status)
	{
		magic=status;
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
		//this.rotationYaw=initYaw;
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
