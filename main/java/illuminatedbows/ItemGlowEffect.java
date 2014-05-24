package illuminatedbows;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemGlowEffect extends Item {

	public ItemGlowEffect(int par1) {
		super(par1);
		// TODO Auto-generated constructor stub
	}
	
	 @SideOnly(Side.CLIENT)
	  public void registerIcons(IconRegister par1IconRegister)
	  {
	      this.itemIcon = par1IconRegister.registerIcon("illuminatedbows:glow");
	  }

}
