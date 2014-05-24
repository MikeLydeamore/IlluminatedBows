package illuminatedbows;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockIlluminatedLeaves extends BlockLeaves {

	public BlockIlluminatedLeaves(int par1) {
		super(par1);
		this.setUnlocalizedName("illuminatedLeaves");
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setLightValue(0.86F);
		this.isOpaqueCube();
		this.setTickRandomly(true);
		this.setHardness(0.2F);
		this.setStepSound(Block.soundGrassFootstep);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isLeaves(World world, int x, int y, int z) {
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("illuminatedbows:illuminatedleaves");
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return this.blockIcon;
	}
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		par3List.add(new ItemStack(par1, 1, 0));
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		//return ColorizerFoliage.getFoliageColorBasic();
		return 16777215;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return 16777215;
    }
	
    public int getRenderColor(int par1)
    {
        return 16777215;
    }
    
	public int quantityDropped(Random par1Random)
    {
        return par1Random.nextInt(50) == 0 ? 1 : 0;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return IlluminatedBows.illuminatedSaplingID;
    }
	
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        if (!par1World.isRemote)
        {
            int j1 = 20;

            if ((par5 & 3) == 3)
            {
                j1 = 40;
            }

            if (par7 > 0)
            {
                j1 -= 2 << par7;

                if (j1 < 10)
                {
                    j1 = 10;
                }
            }

            if (par1World.rand.nextInt(j1) == 0)
            {
                int k1 = this.idDropped(par5, par1World.rand, par7);
                this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(k1, 1, this.damageDropped(par5)));
            }

            j1 = 200;

            if (par7 > 0)
            {
                j1 -= 10 << par7;

                if (j1 < 40)
                {
                    j1 = 40;
                }
            }

            if ((par5 & 3) == 0 && par1World.rand.nextInt(j1) == 0)
            {
                this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(Item.glowstone, 1, 0));
            }
        }
    }

}
