package com.insane.illuminatedbows.client;

import com.insane.illuminatedbows.CommonProxy;
import com.insane.illuminatedbows.EntityIlluminatedArrow;
import com.insane.illuminatedbows.IlluminatedBows;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class ClientProxy extends CommonProxy{
	 public void registerRenderers() {
         RenderingRegistry.registerEntityRenderingHandler(EntityIlluminatedArrow.class, new RenderIlluminatedArrow());
     }
}
