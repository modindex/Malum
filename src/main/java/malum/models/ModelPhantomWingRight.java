package malum.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelPhantomWingRight extends EntityModel
{
	private final RendererModel right_wing;

	public ModelPhantomWingRight() {
		textureWidth = 64;
		textureHeight = 64;

        right_wing = new RendererModel(this);
        right_wing.setRotationPoint(0.0F, 24.0F, 0.0F);
        right_wing.cubeList.add(new ModelBox(right_wing, 0, 0, -20F, -26.0F, 1.5F, 18, 10, 1, 0.0F, true));
    }

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		right_wing.render(f5);
	}
	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}