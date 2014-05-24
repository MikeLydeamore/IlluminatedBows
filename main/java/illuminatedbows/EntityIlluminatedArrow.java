package illuminatedbows;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityIlluminatedArrow extends EntityThrowable {

	public float strength;
	public EntityIlluminatedArrow(World par1World) {
		super(par1World);
		// TODO Auto-generated constructor stub
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
				int meta=0;
				switch (par1MovingObjectPosition.sideHit) {
				case 0:
					--j;
					meta=0;
					break;
				case 1:
					++j;
					meta=1;
					break;
				case 2:
					--k;
					meta=2;
					break;
				case 3:
					++k;
					meta=3;
					break;
				case 4:
					--i;
					meta=4;
					break;
				case 5:
					meta=5;
					++i;
				}

				if (this.worldObj.isAirBlock(i, j, k))	{
					this.worldObj.setBlock(i, j, k, IlluminatedBows.illuminatedBlockID, meta, 2);
					//this.worldObj.setBlockMetadataWithNotify(i,j,k,meta,2);
					this.worldObj.playSoundAtEntity(this, "random.glass", 1.0F, 1.0F);
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

}
