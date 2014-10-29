package com.insane.illuminatedbows.client;

import com.insane.illuminatedbows.IlluminatedBows;
import com.insane.illuminatedbows.blocks.IlluminatedBlocks;
import com.insane.illuminatedbows.tile.TileIllumination;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderIllumination implements ISimpleBlockRenderingHandler
{
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
    {
        ;
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileIllumination)
        {
            Block facade = ((TileIllumination) te).camoBlock;
            renderer.renderStandardBlock(facade, x, y, z);
            renderer.renderStandardBlock(IlluminatedBlocks.illuminatedBlock, x, y, z);
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return false;
    }

    @Override
    public int getRenderId()
    {
        return IlluminatedBows.renderIdIllumination;
    }
}
