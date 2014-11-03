package com.insane.illuminatedbows.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.insane.illuminatedbows.Config;
import com.insane.illuminatedbows.IlluminatedBows;
import com.insane.illuminatedbows.tile.TileIllumination;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockIlluminatedBlock extends Block {
	private IIcon[] iconArray;
	public BlockIlluminatedBlock() {
		super(Material.glass);
		this.setBlockName("illuminatedBlock");
		this.setLightLevel(1F);
		this.isOpaqueCube();
		this.setHardness(0.2F);
		this.setStepSound(Block.soundTypeGlass);
	}
	
	private Material mat = Material.glass;

	public String getItemStackDisplayName() {
		return "Illumination";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("illuminatedbows:illuminatedblock");
		this.iconArray = new IIcon[3];
		iconArray[0] = this.blockIcon;
		iconArray[1] = par1IconRegister.registerIcon("illuminatedbows:illumination_vis");
		iconArray[2] = par1IconRegister.registerIcon("illuminatedBows:illumination_blank");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
	    boolean magic = meta >= 6;
	    meta %= 6;
		return iconArray[magic ? 1 : 0];
	}
	
	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
	    TileIllumination te = (TileIllumination) world.getTileEntity(x, y, z);
	    for (int i : te.sides)
	    {
	        if (i % 6 == side)
	        {
	            return getIcon(0, i);
	        }
	    }
	    return iconArray[2];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
    @SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i=0; i<12; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public boolean hasTileEntity(int meta) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TileIllumination();
	}

	public int damageDropped(int par1) {
		return 0;
	}
	
	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
	    return 0;
	}
	
	@Override
	public int getRenderType() {
	    return IlluminatedBows.renderIdIllumination;
	}

//	@Override
//	public int getMobilityFlag() {
//		return 1;
//	}

//	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
//		int meta = world.getBlockMetadata(x,y,z);
//		switch (meta%6) {
//		case 0: {
//			this.setBlockBounds(0,0.99F,0,1,1,1);
//			break; }
//		case 1: {
//			this.setBlockBounds(0,0,0,1,0.01F,1);
//			break; }
//		case 2: {
//			this.setBlockBounds(0,0,0.99F,1,1,1);
//			break; }
//		case 3: {
//			this.setBlockBounds(0,0,0,1,1,0.01F);
//			break; }
//		case 4: {
//			this.setBlockBounds(0.99F,0,0,1,1,1);
//			break; }
//		case 5: {
//			this.setBlockBounds(0,0,0,0.01F,1,1);
//			break; }
//		/*default:
//			this.setBlockBounds(0,0,0,1,1,1);
//			break;*/
//		}
//	}
//
//	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
//		int meta = par1World.getBlockMetadata(par2, par3, par4);
//		boolean flag=false;
//		//System.out.println(meta);
//		switch (meta) {
//		case 0: {
//			if (par1World.isAirBlock(par2, par3+1, par4)) {
//				flag=true;
//			}
//		} case 1: {
//			if (par1World.isAirBlock(par2, par3-1, par4)) {
//				flag=true;
//			}
//			break;
//		} case 2: {
//			if (par1World.isAirBlock(par2, par3, par4+1)) {
//				flag=true;
//			}
//			break;
//		} case 3: {
//			if (par1World.isAirBlock(par2, par3, par4-1)) {
//				flag=true;
//			}
//			break;
//		} case 4: {
//			if (par1World.isAirBlock(par2+1, par3, par4)) {
//				flag=true;
//			}
//			break;
//		} case 5: {
//			if (par1World.isAirBlock(par2-1, par3, par4)) {
//				flag=true;
//			}
//			break;
//		}
//		}
//
//		if (flag) {
//			par1World.setBlockToAir(par2,par3,par4);
//		}
//
//	}

//	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
//	{
//		return null;
//	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
    @Override
    public boolean isOpaqueCube() {
        return false;
    }
    
    public void setStats(World world, int x, int y, int z)
    {
        TileIllumination te = (TileIllumination) world.getTileEntity(x, y, z);

        this.stepSound = te.camoBlock.stepSound;
        this.setHardness(te.camoBlock.getBlockHardness(world, x, y, z));
        this.mat = te.camoBlock.getMaterial();

        if (world.isRemote)
            this.blockIcon = te.camoBlock.getIcon(0, te.getBlockMetadata());
    }
    
    @Override
    public Material getMaterial()
    {
        return mat;
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
    {
        this.setStats(world, x, y, z);
        super.onBlockClicked(world, x, y, z, player);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean addHitEffects(World world, MovingObjectPosition target, EffectRenderer effectRenderer)
    {
        int side    = target.sideHit;
        int x       = target.blockX;
        int y       = target.blockY;
        int z       = target.blockZ;
        
        if (getMaterial() != Material.air)
        {
            float offset = 0.1F;
            double pX = (double)x + world.rand.nextDouble() * (getBlockBoundsMaxX() - getBlockBoundsMinX() - (double)(offset * 2.0F)) + (double)offset + getBlockBoundsMinX();
            double pY = (double)y + world.rand.nextDouble() * (getBlockBoundsMaxY() - getBlockBoundsMinY() - (double)(offset * 2.0F)) + (double)offset + getBlockBoundsMinY();
            double pZ = (double)z + world.rand.nextDouble() * (getBlockBoundsMaxZ() - getBlockBoundsMinZ() - (double)(offset * 2.0F)) + (double)offset + getBlockBoundsMinZ();

            ForgeDirection dir = ForgeDirection.getOrientation(side);
            
            pX = dir.offsetX == 0 ? pX : x + Math.max(0, dir.offsetX) + (offset * dir.offsetX);
            pY = dir.offsetY == 0 ? pY : y + Math.max(0, dir.offsetY) + (offset * dir.offsetY);
            pZ = dir.offsetZ == 0 ? pZ : z + Math.max(0, dir.offsetZ) + (offset * dir.offsetZ);

            effectRenderer.addEffect((new EntityDiggingFX(world, pX, pY, pZ, 0.0D, 0.0D, 0.0D, ((TileIllumination)world.getTileEntity(x, y, z)).camoBlock, world.getBlockMetadata(x, y, z)).applyColourMultiplier(x, y, z).multiplyVelocity(0.2F).multipleParticleScaleBy(0.6F)));
        }
        
        return true;
    }
    
    @Override
    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {
        int max = 4;

        for (int xOffset = 0; xOffset < max; ++xOffset)
        {
            for (int yOffset = 0; yOffset < max; ++yOffset)
            {
                for (int zOffset = 0; zOffset < max; ++zOffset)
                {
                    double pX = (double) x + ((double) xOffset + 0.5D) / (double) max;
                    double pY = (double) y + ((double) yOffset + 0.5D) / (double) max;
                    double pZ = (double) z + ((double) zOffset + 0.5D) / (double) max;
                    Minecraft.getMinecraft().effectRenderer.addEffect((new EntityDiggingFX(world, pX, pY, pZ, pX - (double) x - 0.5D, pY - (double) y - 0.5D, pZ - (double) z
                            - 0.5D, ((TileIllumination) world.getTileEntity(x, y, z)).camoBlock, meta)).applyColourMultiplier(x, y, z));
                }
            }
        }

        return true;
    }
    
    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player)
    {
        TileIllumination te = (TileIllumination) world.getTileEntity(x, y, z);
        Block block = te.camoBlock;

        if (block != this) // infinite recursion yo
        {
            // make sure we give the internal block all the methods we should
            block.onBlockHarvested(world, x, y, z, meta, player);
            block.onBlockDestroyedByPlayer(world, x, y, z, meta);
            block.breakBlock(world, x, y, z, block, meta);
        }

        if (!player.capabilities.isCreativeMode)
        {
            for (ItemStack stack : block.getDrops(world, x, y, z, meta, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, player.getCurrentEquippedItem())))
            {
                dropBlockAsItem(world, x, y, z, stack);
            }
        }
        
        float incr = Config.glowstoneChancePerSide;
        float chance = 0f;
        
        for (int i : te.sides)
        {
            chance += i < 6 ? incr : 0;
        }

        int amnt = MathHelper.floor_float(chance);
        amnt += world.rand.nextFloat() < chance - amnt ? 1 : 0;
        dropBlockAsItem(world, x, y, z, new ItemStack(Items.glowstone_dust, amnt));

        super.onBlockHarvested(world, x, y, z, meta, player);
    }
}
