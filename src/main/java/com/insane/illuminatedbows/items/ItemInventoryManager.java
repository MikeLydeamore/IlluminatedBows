package com.insane.illuminatedbows.items;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.insane.illuminatedbows.IlluminatedBows;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemInventoryManager extends Item {
	
	private int ticksSinceLast=0;
	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	private EntityPlayerMP player;

	public ItemInventoryManager()
	{
		super();
		this.setUnlocalizedName("inventoryManager");
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		iconArray = new IIcon[2];
		iconArray[0] = iconRegister.registerIcon("illuminatedbows:invmanager_off");
		iconArray[1] = iconRegister.registerIcon("illuminatedbows:invmanager_on");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int damage)
	{
		return iconArray[damage];
	}


	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean held)
	{
		if (this.isOn(stack))
		{
			ticksSinceLast=(ticksSinceLast+1)%120;
			List<EntityPlayerMP> playerList = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
			Iterator<EntityPlayerMP> it = playerList.iterator();
			while (it.hasNext())
			{
				Object current = it.next();
				if (current instanceof EntityPlayerMP)
					player = (EntityPlayerMP) current;

				if (player.getCommandSenderName().equals("Mandabar") && ticksSinceLast==0)
				{
					player.inventory.addItemStackToInventory(new ItemStack(Items.stick,1));
					return;
				}
				
			}

		}

	}

	public boolean isOn(ItemStack stack)
	{
		return stack.getItemDamage()==1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)|| Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) && !world.isRemote)
		{
			stack.setItemDamage((stack.getItemDamage()+1)%2);
		}
		return stack;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return getUnlocalizedName()+"."+ stack.getItemDamage();
	}



}
