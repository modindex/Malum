package malum.renderer;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import malum.ClientRefferences;
import malum.tileentities.CraftingBlockTileEntity;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class CraftingBlockRenderer extends TileEntityRenderer<CraftingBlockTileEntity>
{
     public int getItemCount(ItemStackHandler inventory)
    {
        int amountOfItems = 0;
        for (int counter=0; counter < inventory.getSlots(); counter++)
        {
            if (!inventory.getStackInSlot(counter).isEmpty())
            {
                amountOfItems = counter + 1;
            }
        }
        return amountOfItems;
    }
    @Override
    public void render(CraftingBlockTileEntity blockEntity, double x, double y, double z, float partialTicks, int destroyStage)
    {
        if (this.rendererDispatcher.renderInfo != null && blockEntity.getDistanceSq(this.rendererDispatcher.renderInfo.getProjectedView().x, this.rendererDispatcher.renderInfo.getProjectedView().y, this.rendererDispatcher.renderInfo.getProjectedView().z) < 128d)
        {
            int packedLight = blockEntity.getWorld().getCombinedLight(blockEntity.getPos().up(), 0);
            int skyLight = packedLight & 0xFF;
            int blockLight = packedLight >> 16 & 0xFF;
            GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, skyLight, blockLight);
            ItemRenderer itemRenderer = ClientRefferences.minecraft.getItemRenderer();
            GlStateManager.enableBlend();
            for (int a = 0; a < blockEntity.inventory.getSlots(); a += 1)
            {
                ItemStack item = blockEntity.inventory.getStackInSlot(a);
                if (item.isEmpty())
                {
                    break;
                }
                GlStateManager.pushMatrix();
                float rot = a / (float)getItemCount(blockEntity.inventory) * 6.28f + (blockEntity.getWorld().getGameTime() % 500f / 500f) * 6.28f;
                double dist = 0.4;
                double posY = y + 1.2 + Math.sin(blockEntity.getWorld().getGameTime() / 29f) / 64;

                dist -= (blockEntity.crafting / 100f) * 0.4f;
                posY += blockEntity.crafting / 200;
                double posX = x + 0.5 - (Math.cos(rot) * dist);
                double posZ = z + 0.5 - (Math.sin(rot) * dist);

                GlStateManager.translated(posX, posY, posZ);
                GlStateManager.scaled(0.25, 0.25, 0.25);
                itemRenderer.renderItem(item, ItemCameraTransforms.TransformType.FIXED);
                GlStateManager.popMatrix();
            }
        }
    }
}