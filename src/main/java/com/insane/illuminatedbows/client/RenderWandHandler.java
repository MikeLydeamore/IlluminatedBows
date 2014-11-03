package com.insane.illuminatedbows.client;

import static org.lwjgl.opengl.GL11.*;

import java.lang.reflect.Field;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Timer;
import net.minecraftforge.client.IItemRenderer;
import thaumcraft.api.wands.IWandFocus;
import thaumcraft.common.items.wands.ItemWandCasting;

import com.insane.illuminatedbows.addons.thaumcraft.items.ItemFocusIlluminating;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class RenderWandHandler implements IItemRenderer
{
    private IItemRenderer wandRenderer;
    private static final Random rand = new Random();
    private int rot;
    
    private static final Field timer =  ReflectionHelper.findField(Minecraft.class, "timer", "Q", "field_71428_T");
    
    public RenderWandHandler(IItemRenderer wandRenderer)
    {
        this.wandRenderer = wandRenderer;
    }
    
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return wandRenderer.shouldUseRenderHelper(type, item, helper);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        wandRenderer.renderItem(type, item, data);

        ItemWandCasting wand = (ItemWandCasting) item.getItem();
        IWandFocus focus = wand.getFocus(item);
        if (focus instanceof ItemFocusIlluminating && (type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON))
        {
            Tessellator tessellator = Tessellator.instance;
            float partialTicks = 0;
            try
            {
                partialTicks = ((Timer)timer.get(Minecraft.getMinecraft())).renderPartialTicks;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
            glPushMatrix();
            
            EntityLivingBase entity = (EntityLivingBase) data[1];
            
            if ((entity != null) && ((entity instanceof EntityPlayer)) && (((EntityPlayer)entity).getItemInUse() != null)) 
            {
                float t = ((EntityPlayer)entity).getItemInUseDuration() + partialTicks;
                if (t > 3.0F) {
                  t = 3.0F;
                }
                glTranslated(0.0D, 1.0D, 0.0D);
                if (type != IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)
                {
                  glRotatef(33.0F, 0.0F, 0.0F, 1.0F);
                }
                else
                {
                  glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                  glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                }
                glRotatef(60.0F * (t / 3.0F), -1.0F, 0.0F, 0.0F);
                if ((wand.animation == IWandFocus.WandFocusAnimation.WAVE) || ((wand.getFocus(item) != null) && (wand.getFocus(item).getAnimation() == IWandFocus.WandFocusAnimation.WAVE)))
                {
                  float wave = MathHelper.sin((((EntityPlayer)entity).getItemInUseDuration() + partialTicks) / 10.0F) * 10.0F;
                  glRotatef(wave, 0.0F, 0.0F, 1.0F);
                  wave = MathHelper.sin((((EntityPlayer)entity).getItemInUseDuration() + partialTicks) / 15.0F) * 10.0F;
                  glRotatef(wave, 1.0F, 0.0F, 0.0F);
                }
                else if ((wand.getFocus(item) != null) && (wand.getFocus(item).getAnimation() == IWandFocus.WandFocusAnimation.CHARGE))
                {
                  float wave = MathHelper.sin((((EntityPlayer)entity).getItemInUseDuration() + partialTicks) / 0.8F) * 1.0F;
                  glRotatef(wave, 0.0F, 0.0F, 1.0F);
                  wave = MathHelper.sin((((EntityPlayer)entity).getItemInUseDuration() + partialTicks) / 0.7F) * 1.0F;
                  glRotatef(wave, 1.0F, 0.0F, 0.0F);
                }
                glTranslated(0.0D, -1.0D, 0.0D);
                
                if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
                {
                    glTranslatef(0.75f, 1.5f, -0.3f);
                }
                else
                {
                    glTranslatef(0.6f, 1.2f, -0.6f);
                }
            }
            else
            {
                glTranslatef(0.5f, 1.6f, 0.5f);
            }

            
            RenderHelper.disableStandardItemLighting();
            
            glDisable(GL_TEXTURE_2D);
            glShadeModel(GL_SMOOTH);
            glEnable(GL_BLEND);
            OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE, GL_ZERO, GL_ONE);
            glDisable(GL_ALPHA_TEST);
            glEnable(GL_CULL_FACE);
            glDisable(GL_LIGHTING);
            glDepthMask(false);

            rand.setSeed(298347L);
            
            for (int i = 0; i < 15; i++)
            {
                glRotatef(rand.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                glRotatef(rand.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
                glRotatef(rand.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
                glRotatef(rand.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);

                glRotatef((rand.nextFloat() * 0.25f * rot) % 360, 0, 1, 0);

                tessellator.startDrawingQuads();

                tessellator.setBrightness(0xFF);
                tessellator.setColorRGBA(255, 255, 100, 255);

                tessellator.addVertex(0, 0f, 0);
                tessellator.addVertex(0, 0f, 0);
                
                tessellator.setColorRGBA(255, 255, 100, 0);
                
                tessellator.addVertex(0.4f, 0.25f, 0.4f);
                tessellator.addVertex(0.4f, 0.55f, 0.4f);

                tessellator.draw();
            }

            glDisable(GL_BLEND);
            glShadeModel(GL_FLAT);
            glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            glEnable(GL_TEXTURE_2D);
            glEnable(GL_ALPHA_TEST);
            glEnable(GL_LIGHTING);
            RenderHelper.enableStandardItemLighting();

            glPopMatrix();
        }
    }
    
    @SubscribeEvent
    public void onClientTick(ClientTickEvent event)
    {
        rot++;
    }
}
