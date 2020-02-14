package malum.renderer;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import malum.ClientRefferences;
import malum.MalumMod;
import malum.tileentities.RitualBlockTileEntity;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.lwjgl.opengl.GL11;

import static com.mojang.blaze3d.platform.GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA;
import static com.mojang.blaze3d.platform.GlStateManager.SourceFactor.SRC_ALPHA;

public class RitualBlockRenderer extends TileEntityRenderer<RitualBlockTileEntity>
{
    public static final ResourceLocation RING_PURPLE = new ResourceLocation(MalumMod.MODID, "textures/visuals/ritual_ring_1.png");
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
    public void render(RitualBlockTileEntity blockEntity, double x, double y, double z, float partialTicks, int destroyStage)
    {
        if (this.rendererDispatcher.renderInfo != null && blockEntity.getDistanceSq(this.rendererDispatcher.renderInfo.getProjectedView().x, this.rendererDispatcher.renderInfo.getProjectedView().y, this.rendererDispatcher.renderInfo.getProjectedView().z) < 1024d)
        {
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();

            GlStateManager.pushMatrix();
            ClientRefferences.minecraft.getTextureManager().bindTexture(RING_PURPLE);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(SRC_ALPHA, ONE_MINUS_SRC_ALPHA);
            GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, 240, 240);

            GlStateManager.translated(x + 0.5, y + 0.78125, z + 0.5);
            GlStateManager.scaled(2, 2, 2);
            GlStateManager.rotated(blockEntity.getWorld().getGameTime() + partialTicks + MathHelper.hash((int) blockEntity.getPos().toLong()) % 10000 + Math.sin(blockEntity.crafting), 0, 1, 0);

            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

            buffer.pos(-0.5, 0, -0.5).tex(0, 0).endVertex();
            buffer.pos(-0.5, 0, 0.5).tex(1, 0).endVertex();
            buffer.pos(0.5, 0, 0.5).tex(1, 1).endVertex();
            buffer.pos(0.5, 0, -0.5).tex(0, 1).endVertex();

            tessellator.draw();
            GlStateManager.popMatrix();
        }
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
                float rot = a / (float)getItemCount(blockEntity.inventory) * 6.28f + ((blockEntity.getWorld().getGameTime() + blockEntity.crafting) % 700f / 700f) * 6.28f;
                double dist = 0.4;
                double posY = y + 1.2 + Math.sin(blockEntity.getWorld().getGameTime() / 20f) / 64;

                dist -= (blockEntity.crafting / 300f) * 0.2f;
                posY += blockEntity.crafting / 450;
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