package kittykitcatcat.malum.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPhantomWingRight extends EntityModel
{
    private final ModelRenderer right_wing;

    public ModelPhantomWingRight()
    {
        textureWidth = 64;
        textureHeight = 64;

        right_wing = new ModelRenderer(this);
        right_wing.setRotationPoint(0.0F, 24.0F, 0.0F);
        right_wing.addBox( -20F, -26.0F, 1.5F, 18, 10, 1, 0.0F, true);
    }

    @Override
    public void setRotationAngles(Entity entity, float v, float v1, float v2, float v3, float v4)
    {

    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3)
    {
        right_wing.render(matrixStack, iVertexBuilder, i, i1);
    }
}