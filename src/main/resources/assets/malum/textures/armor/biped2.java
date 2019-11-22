//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class  extends ModelBase {
	private final ModelRenderer rightleg;
	private final ModelRenderer leftleg;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightarm;

	public () {
		textureWidth = 128;
		textureHeight = 128;

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 7, 4, 0.0F, false));
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 33, -2.0F, 0.0F, -2.0F, 4, 7, 4, 0.3F, false));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(2.0F, 12.0F, 0.0F);
		leftleg.cubeList.add(new ModelBox(leftleg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 7, 4, 0.0F, true));
		leftleg.cubeList.add(new ModelBox(leftleg, 0, 33, -2.0F, 0.0F, -2.0F, 4, 7, 4, 0.3F, true));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 6.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 16, 16, -4.0F, -6.0F, -2.0F, 8, 12, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 16, 32, -4.0F, -6.0F, -2.0F, 8, 12, 4, 0.4F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 32, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.3F, false));

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(4.0F, 2.0F, 0.0F);
		leftarm.cubeList.add(new ModelBox(leftarm, 40, 16, 0.0F, -3.0F, -2.5F, 5, 6, 5, 0.0F, true));
		leftarm.cubeList.add(new ModelBox(leftarm, 60, 16, 0.0F, -3.0F, -2.5F, 5, 6, 5, 0.4F, true));
		leftarm.cubeList.add(new ModelBox(leftarm, 40, 32, 2.0F, 5.0F, -2.5F, 3, 5, 5, -0.2F, true));

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-4.0F, 2.0F, 0.0F);
		rightarm.cubeList.add(new ModelBox(rightarm, 40, 16, -5.0F, -3.0F, -2.5F, 5, 6, 5, 0.0F, false));
		rightarm.cubeList.add(new ModelBox(rightarm, 60, 16, -5.0F, -3.0F, -2.5F, 5, 6, 5, 0.4F, false));
		rightarm.cubeList.add(new ModelBox(rightarm, 40, 32, -5.0F, 5.0F, -2.5F, 3, 5, 5, -0.2F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		rightleg.render(f5);
		leftleg.render(f5);
		body.render(f5);
		head.render(f5);
		leftarm.render(f5);
		rightarm.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}