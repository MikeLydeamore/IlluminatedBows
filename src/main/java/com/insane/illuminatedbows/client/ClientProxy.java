package com.insane.illuminatedbows.client;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.MinecraftForgeClient;

import com.insane.illuminatedbows.CommonProxy;
import com.insane.illuminatedbows.EntityIlluminatedArrow;
import com.insane.illuminatedbows.IlluminatedBows;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy
{
    public void registerRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityIlluminatedArrow.class, new RenderIlluminatedArrow());

        if (Loader.isModLoaded("Thaumcraft"))
        {
            ItemStack stack = GameRegistry.findItemStack("Thaumcraft", "WandCasting", 1);
            IItemRenderer wandRenderer = MinecraftForgeClient.getItemRenderer(stack, ItemRenderType.EQUIPPED_FIRST_PERSON);
            RenderWandHandler render = new RenderWandHandler(wandRenderer);
            MinecraftForgeClient.registerItemRenderer(stack.getItem(), render);
            FMLCommonHandler.instance().bus().register(render);
        }
        
        IlluminatedBows.renderIdIllumination = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderIllumination());
    }
}
