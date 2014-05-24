package illuminatedbows;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockIlluminatedWood extends BlockRotatedPillar {

	private Icon[] blockIcon;
	public BlockIlluminatedWood(int par1) {
		super(par1, Material.wood);
		this.setUnlocalizedName("illuminatedWood");
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setLightValue(0.86F);
		this.setStepSound(Block.soundWoodFootstep);
		this.setHardness(2.0F);
		// TODO Auto-generated constructor stub
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = new Icon[2];
		this.blockIcon[0] = par1IconRegister.registerIcon("illuminatedbows:illuminatedlogtop");
		this.blockIcon[1]=par1IconRegister.registerIcon("illuminatedbows:illuminatedlogside");
	}


	protected Icon getSideIcon(int par1)
	{
		return this.blockIcon[1];
	}

	@SideOnly(Side.CLIENT)

	/**
	 * The icon for the tops and bottoms of the block.
	 */
	protected Icon getEndIcon(int par1)
	{
		return this.blockIcon[0];
	}

	public boolean canSustainLeaves(World world, int x, int y, int z)
	{
		return true;
	}

	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		byte b0 = 4;
		int j1 = b0 + 1;

		if (par1World.checkChunksExist(par2 - j1, par3 - j1, par4 - j1, par2 + j1, par3 + j1, par4 + j1))
		{
			for (int k1 = -b0; k1 <= b0; ++k1)
			{
				for (int l1 = -b0; l1 <= b0; ++l1)
				{
					for (int i2 = -b0; i2 <= b0; ++i2)
					{
						int j2 = par1World.getBlockId(par2 + k1, par3 + l1, par4 + i2);

						if (Block.blocksList[j2] != null)
						{
							Block.blocksList[j2].beginLeavesDecay(par1World, par2 + k1, par3 + l1, par4 + i2);
						}
					}
				}
			}
		}
	}
	@Override
	public boolean isWood(World world, int x, int y, int z)
	{
		return true;
	}

}
