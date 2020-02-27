package kittykitcatcat.malum.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelPhantomWingLeft extends EntityModel
{
    private final RendererModel left_wing;

    public ModelPhantomWingLeft()
    {
        textureWidth = 64;
        textureHeight = 64;

        left_wing = new RendererModel(this);
        left_wing.setRotationPoint(0.0F, 24.0F, 0.0F);
        left_wing.cubeList.add(new ModelBox(left_wing, 0, 0, 2F, -26.0F, 1.5F, 18, 10, 1, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        left_wing.render(f5);
    }
}