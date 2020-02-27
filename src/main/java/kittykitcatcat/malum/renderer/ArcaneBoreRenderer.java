package kittykitcatcat.malum.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import kittykitcatcat.malum.tileentities.ArcaneBoreTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class ArcaneBoreRenderer extends TileEntityRenderer<ArcaneBoreTileEntity>
{
    public ArcaneBoreRenderer(TileEntityRendererDispatcher rendererDispatcherIn)
    {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(ArcaneBoreTileEntity arcaneBoreTileEntity, float v, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, int i1)
    {

    }
/*
    @Override
    public void render(ArcaneBoreTileEntity blockEntity, double x, double y, double z, float partialTicks, int destroyStage)
    {
        if (this.rendererDispatcher.renderInfo != null && blockEntity.getDistanceSq(this.rendererDispatcher.renderInfo.getProjectedView().x, this.rendererDispatcher.renderInfo.getProjectedView().y, this.rendererDispatcher.renderInfo.getProjectedView().z) < 1024d)
        {
            int packedLight = blockEntity.getWorld().getCombinedLight(blockEntity.getPos(), 0);
            int skyLight = packedLight & 0xFF;
            int blockLight = packedLight >> 16 & 0xFF;
            GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, skyLight, blockLight);
            ItemRenderer itemRenderer = ClientRefferences.minecraft.getItemRenderer();
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.translated(x + 0.5, y + 0.5, z + 0.5);
            itemRenderer.renderItem(ModItems.drill_render.getDefaultInstance(), ItemCameraTransforms.TransformType.FIXED);
            GlStateManager.popMatrix();
        }
    }*/
}