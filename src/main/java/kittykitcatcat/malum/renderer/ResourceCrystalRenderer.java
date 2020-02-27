package kittykitcatcat.malum.renderer;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import kittykitcatcat.malum.ClientRefferences;
import kittykitcatcat.malum.registry.ModItems;
import kittykitcatcat.malum.tileentities.ResourceCrystalTileEntity;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ResourceCrystalRenderer extends TileEntityRenderer<ResourceCrystalTileEntity>
{

    @Override
    public void render(ResourceCrystalTileEntity blockEntity, double x, double y, double z, float partialTicks, int destroyStage)
    {
        if (this.rendererDispatcher.renderInfo != null && blockEntity.getDistanceSq(this.rendererDispatcher.renderInfo.getProjectedView().x, this.rendererDispatcher.renderInfo.getProjectedView().y, this.rendererDispatcher.renderInfo.getProjectedView().z) < 1024d)
        {
            GlStateManager.pushMatrix();
            int packedLight = blockEntity.getWorld().getCombinedLight(blockEntity.getPos().up(), 0);
            int skyLight = packedLight & 0xFF;
            int blockLight = packedLight >> 16 & 0xFF;
            GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, skyLight, blockLight);
            ItemRenderer itemRenderer = ClientRefferences.minecraft.getItemRenderer();
            float scale = 0.1f + blockEntity.progress * 0.001f;
            GlStateManager.translated(x + 0.5, y + (scale / 2), z + 0.5);
            GlStateManager.scaled(scale, scale, scale);
            GlStateManager.color3f(blockEntity.red, blockEntity.green, blockEntity.blue);
            ItemStack stack = new ItemStack(ModItems.crystal_render);
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("red", blockEntity.red);
            nbt.putInt("green", blockEntity.green);
            nbt.putInt("blue", blockEntity.blue);
            stack.setTag(nbt);
            itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
            GlStateManager.popMatrix();
        }
    }
}