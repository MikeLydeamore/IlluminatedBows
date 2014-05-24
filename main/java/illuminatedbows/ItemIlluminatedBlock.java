package illuminatedbows;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemIlluminatedBlock extends ItemBlock {

	public ItemIlluminatedBlock(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		// TODO Auto-generated constructor stub
	}
	
	public String getUnlocalizedName(ItemStack itemstack) {
		String name="";
		switch(itemstack.getItemDamage()) {
		case 0:	{
			name="left";
			break;
		} case 1: {
			name="right";
			break;
		} default:
			name="broken";
		}
		return getUnlocalizedName()+"."+name;
	}
	
	public int getMetadata(int par1) {
		return par1;
	}

}
