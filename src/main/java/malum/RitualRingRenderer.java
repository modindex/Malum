package malum;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import malum.blocks.RitualBlock;
import malum.tileentities.RitualBlockTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

import static com.mojang.blaze3d.platform.GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA;
import static com.mojang.blaze3d.platform.GlStateManager.SourceFactor.SRC_ALPHA;

public class RitualRingRenderer extends TileEntityRenderer<RitualBlockTileEntity>
{
    public static final ResourceLocation RING_PURPLE = new ResourceLocation(MalumMod.MODID, "textures/visuals/ritual_ring_1.png");
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

            GlStateManager.translated(x + 0.5, y + 1.1, z + 0.5);
            GlStateManager.scaled(2, 2, 2);
            GlStateManager.rotated(blockEntity.getWorld().getWorld().getDayTime() + partialTicks + MathHelper.hash((int) blockEntity.getPos().toLong()) % 10000 + Math.sin(blockEntity.crafting), 0, 1,0);

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
            BlockState state = blockEntity.getBlockState();
            Direction facing = state.get(RitualBlock.FACING);
            for (int a = 0; a < blockEntity.inventory.getSlots(); a += 1)
            {
                ItemStack item = blockEntity.inventory.getStackInSlot(a);
                if (item.isEmpty())
                {
                    break;
                }
                GlStateManager.pushMatrix();

                double rad = blockEntity.getWorld().getGameTime() * (Math.PI / 180) + a * 40;
                double dist = 0.4;
                switch (facing)
                {
                    case NORTH:
                    {
                        rad += 90;
                        break;
                    }
                    case EAST:
                    {
                        rad += 180;
                        break;
                    }
                    case SOUTH:
                    {
                        break;
                    }
                    case WEST:
                    {
                        rad += 270;
                    }
                    default:
                }
                double posX = x + 0.5 - (Math.cos(rad) * dist);
                double posZ = z + 0.5 - (Math.sin(rad) * dist);
                GlStateManager.translated(posX, y + 1, posZ);
                GlStateManager.scaled(0.25, 0.25, 0.25);
                GlStateManager.rotated(90, 1, 0, 0);
                switch (facing)
                {
                    case NORTH:
                    {
                        GlStateManager.rotated(180, 0, 0, 1);
                        break;
                    }
                    case EAST:
                    {
                        GlStateManager.rotated(270, 0, 0, 1);
                        break;
                    }
                    case SOUTH:
                    {
                        break;
                    }
                    case WEST:
                    {
                        GlStateManager.rotated(90, 0, 0, 1);
                    }
                    default:
                }
                itemRenderer.renderItem(item, ItemCameraTransforms.TransformType.FIXED);
                GlStateManager.popMatrix();
            }
        }
    }
}