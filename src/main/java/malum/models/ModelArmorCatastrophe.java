//Made with Blockbench
//Paste this code into your mod.
package malum.models;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class ModelArmorCatastrophe extends ModelArmor {
	private final RendererModel rightleg;
	private final RendererModel leftleg;
	private final RendererModel body;
	private final RendererModel head;
	private final RendererModel leftarm;
	private final RendererModel rightarm;
	private final RendererModel belt;
	private final RendererModel leftboot;
	private final RendererModel rightboot;

	public ModelArmorCatastrophe(EquipmentSlotType slot)
	{
		super(slot, 128, 128);

		rightleg = new RendererModel(this);
		rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.35F, false));
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 32, -2.0F, 0.0F, -2.0F, 4, 8, 4, 0.45F, false));
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 32, -2.0F, 0.0F, -2.0F, 4, 8, 4, 0.55F, false));
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 32, -2.0F, 0.0F, -2.0F, 4, 8, 4, 0.65F, false));
        rightleg.cubeList.add(new ModelBox(rightleg, 0, 32, -2.0F, 0.0F, -2.0F, 4, 8, 4, 0.7F, false));

		leftleg = new RendererModel(this);
		leftleg.setRotationPoint(2.0F, 12.0F, 0.0F);
		leftleg.cubeList.add(new ModelBox(leftleg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.35F, true));
		leftleg.cubeList.add(new ModelBox(leftleg, 0, 32, -2.0F, 0.0F, -2.0F, 4, 8, 4, 0.45F, true));
		leftleg.cubeList.add(new ModelBox(leftleg, 0, 32, -2.0F, 0.0F, -2.0F, 4, 8, 4, 0.55F, true));
		leftleg.cubeList.add(new ModelBox(leftleg, 0, 32, -2.0F, 0.0F, -2.0F, 4, 8, 4, 0.65F, true));
        leftleg.cubeList.add(new ModelBox(leftleg, 0, 32, -2.0F, 0.0F, -2.0F, 4, 8, 4, 0.7F, true));

		body = new RendererModel(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 16, 16, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.4F, false));
		body.cubeList.add(new ModelBox(body, 16, 32, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.5F, false));
		body.cubeList.add(new ModelBox(body, 16, 32, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.6F, false));
		body.cubeList.add(new ModelBox(body, 16, 32, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.7F, false));
		body.cubeList.add(new ModelBox(body, 16, 32, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.8F, false));
		body.cubeList.add(new ModelBox(body, 16, 32, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.9F, false));
        body.cubeList.add(new ModelBox(body, 16, 32, -4.0F, 0.0F, -2.0F, 8, 12, 4, 1F, false));

		head = new RendererModel(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.4F, false));
		head.cubeList.add(new ModelBox(head, 32, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.45F, false));
		head.cubeList.add(new ModelBox(head, 32, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.55F, false));
		head.cubeList.add(new ModelBox(head, 32, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.65F, false));
        head.cubeList.add(new ModelBox(head, 32, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.75F, false));

		leftarm = new RendererModel(this);
		leftarm.setRotationPoint(4.0F, 2.0F, 0.0F);
		leftarm.cubeList.add(new ModelBox(leftarm, 40, 16, -1F, -2.0F, -2.0F, 4, 12, 4, 0.4F, true));
		leftarm.cubeList.add(new ModelBox(leftarm, 56, 16, -1F, -2.0F, -2.0F, 4, 12, 4, 0.45F, true));
		leftarm.cubeList.add(new ModelBox(leftarm, 56, 16, -1F, -2.0F, -2.0F, 4, 12, 4, 0.55F, true));
		leftarm.cubeList.add(new ModelBox(leftarm, 56, 16, -1F, -2.0F, -2.0F, 4, 12, 4, 0.65F, true));
		leftarm.cubeList.add(new ModelBox(leftarm, 56, 16, -1F, -2.0F, -2.0F, 4, 12, 4, 0.75F, true));
		leftarm.cubeList.add(new ModelBox(leftarm, 56, 16, -1F, -2.0F, -2.0F, 4, 12, 4, 0.85F, true));
		leftarm.cubeList.add(new ModelBox(leftarm, 56, 16, -1F, -2.0F, -2.0F, 4, 12, 4, 0.95F, true));
        leftarm.cubeList.add(new ModelBox(leftarm, 56, 16, -1F, -2.0F, -2.0F, 4, 12, 4, 1.05F, true));

		rightarm = new RendererModel(this);
		rightarm.setRotationPoint(-4.0F, 2.0F, 0.0F);
		rightarm.cubeList.add(new ModelBox(rightarm, 40, 16, -3F, -2.0F, -2.0F, 4, 12, 4, 0.4F, false));
		rightarm.cubeList.add(new ModelBox(rightarm, 56, 16, -3F, -2.0F, -2.0F, 4, 12, 4, 0.55F, false));
		rightarm.cubeList.add(new ModelBox(rightarm, 56, 16, -3F, -2.0F, -2.0F, 4, 12, 4, 0.65F, false));
		rightarm.cubeList.add(new ModelBox(rightarm, 56, 16, -3F, -2.0F, -2.0F, 4, 12, 4, 0.75F, false));
		rightarm.cubeList.add(new ModelBox(rightarm, 56, 16, -3F, -2.0F, -2.0F, 4, 12, 4, 0.85F, false));
		rightarm.cubeList.add(new ModelBox(rightarm, 56, 16, -3F, -2.0F, -2.0F, 4, 12, 4, 0.95F, false));
        rightarm.cubeList.add(new ModelBox(rightarm, 56, 16, -3F, -2.0F, -2.0F, 4, 12, 4, 1.05F, false));

		belt = new RendererModel(this);
		belt.setRotationPoint(0.0F, 0f, 0.0F);

		leftboot = new RendererModel(this);
		leftboot.setRotationPoint(0.0F, 24.0F, 0.0F);
		leftboot.cubeList.add(new ModelBox(leftboot, 0, 44, 0.0F, 8.0F, -2.0F, 4, 4, 4, 0.4F, true));
		leftboot.cubeList.add(new ModelBox(leftboot, 0, 44, 0.0F, 8.0F, -2.0F, 4, 4, 4, 0.5F, true));
		leftboot.cubeList.add(new ModelBox(leftboot, 0, 44, 0.0F, 8.0F, -2.0F, 4, 4, 4, 0.6F, true));
        leftboot.cubeList.add(new ModelBox(leftboot, 0, 44, 0.0F, 8.0F, -2.0F, 4, 4, 4, 0.7F, true));

		rightboot = new RendererModel(this);
		rightboot.setRotationPoint(0.0F, 24.0F, 0.0F);
		rightboot.cubeList.add(new ModelBox(rightboot, 0, 44, -4.0F, 8.0F, -2.0F, 4, 4, 4, 0.4F, false));
		rightboot.cubeList.add(new ModelBox(rightboot, 0, 44, -4.0F, 8.0F, -2.0F, 4, 4, 4, 0.5F, false));
		rightboot.cubeList.add(new ModelBox(rightboot, 0, 44, -4.0F, 8.0F, -2.0F, 4, 4, 4, 0.6F, false));
        rightboot.cubeList.add(new ModelBox(rightboot, 0, 44, -4.0F, 8.0F, -2.0F, 4, 4, 4, 0.7F, false));
    }
	@Override
	public void render(LivingEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.showModel = slot == EquipmentSlotType.HEAD;
		belt.showModel = slot == EquipmentSlotType.LEGS;
		leftleg.showModel = slot == EquipmentSlotType.LEGS;
		rightleg.showModel = slot == EquipmentSlotType.LEGS;
		body.showModel = slot == EquipmentSlotType.CHEST;
		leftarm.showModel = slot == EquipmentSlotType.CHEST;
		rightarm.showModel = slot == EquipmentSlotType.CHEST;
		leftboot.showModel = slot == EquipmentSlotType.FEET;
		rightboot.showModel = slot == EquipmentSlotType.FEET;
		bipedHeadwear.showModel = false;
		bipedHead = head;
		bipedBody = body;
		bipedRightArm = rightarm;
		bipedLeftArm = leftarm;
		if(slot == EquipmentSlotType.LEGS) {
			bipedBody = belt;
			bipedRightLeg = rightleg;
			bipedLeftLeg = leftleg;
		} else {
			bipedRightLeg = rightboot;
			bipedLeftLeg = leftboot;
		}
		super.render(entity, f, f1, f2, f3, f4, f5);

	}
	protected void setRotateAngle(RendererModel modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}