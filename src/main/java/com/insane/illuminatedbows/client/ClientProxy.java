package com.insane.illuminatedbows.client;

import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXWisp;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.MinecraftForgeClient;

import com.insane.illuminatedbows.CommonProxy;
import com.insane.illuminatedbows.EntityIlluminatedArrow;
import com.insane.illuminatedbows.IlluminatedBows;
import com.insane.illuminatedbows.client.particles.ColourNitorFX;

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
    
    public void colourNitorEffects(World world, double x, double y, double z, double x2, double y2, double z2, float size, int type, boolean shrink, float gravity, int colour)
    {
    	ColourNitorFX ef = new ColourNitorFX(world, x, y, z, x2, y2, z2, size, type, shrink, gravity, colour);
    	ef.setGravity(gravity);
    	ef.shrink = shrink;
    	
    	ParticleEngine.instance.addEffect(world,ef);
    }
}
