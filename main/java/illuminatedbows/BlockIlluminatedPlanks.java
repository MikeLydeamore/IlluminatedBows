package illuminatedbows;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class BlockIlluminatedPlanks extends Block {

	public BlockIlluminatedPlanks(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setLightValue(0.86F);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName("illumiantedWood");
		this.setStepSound(Block.soundWoodFootstep);
		this.setHardness(2.0F);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("illuminatedbows:illuminatedplanks");
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return this.blockIcon;
	}
	

}
