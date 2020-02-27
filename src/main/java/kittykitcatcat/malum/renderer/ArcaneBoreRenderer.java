package kittykitcatcat.malum.renderer;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import kittykitcatcat.malum.ClientRefferences;
import kittykitcatcat.malum.registry.ModItems;
import kittykitcatcat.malum.tileentities.ArcaneBoreTileEntity;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;

public class ArcaneBoreRenderer extends TileEntityRenderer<ArcaneBoreTileEntity>
{

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
    }
}