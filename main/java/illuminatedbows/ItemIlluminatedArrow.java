package illuminatedbows;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemIlluminatedArrow extends Item {

	public ItemIlluminatedArrow(int par1) {
		super(par1);
		this.setUnlocalizedName("illuminatedArrow");
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.setMaxStackSize(64);
		// TODO Auto-generated constructor stub
	}
	
	 @SideOnly(Side.CLIENT)
	  public void registerIcons(IconRegister par1IconRegister)
	  {
	      this.itemIcon = par1IconRegister.registerIcon("illuminatedbows:illuminatedarrow");
	  }

}
