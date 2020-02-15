package malum.models;
//Made with Blockbench, by Daniel Astral (@TrisAstral)
//Paste this code into your mod.

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class ModelArmorSoulSteel extends ModelArmor
{
	private final RendererModel torso;
	private final RendererModel chestplate_slope2;
	private final RendererModel head;
	private final RendererModel arm_r;
	private final RendererModel arm_l;
	private final RendererModel leg_r;
	private final RendererModel thigh_guard_r2;
	private final RendererModel boot_r;
	private final RendererModel boot_wing_r2;
	private final RendererModel leg_l;
	private final RendererModel thigh_guard_l2;
	private final RendererModel boot_l;
	private final RendererModel boot_wing_l2;

	public ModelArmorSoulSteel(EquipmentSlotType slot) {
        super(slot, 64, 98);
        textureWidth = 64;
		textureHeight = 96;

        torso = new RendererModel(this);
        torso.setRotationPoint(0.0F, 6.0F, 0.0F);
        torso.cubeList.add(new ModelBox(torso, 32, 22, -5.0F, -5.0F, -3.0F, 10, 4, 6, 0.0F, false));
        torso.cubeList.add(new ModelBox(torso, 23, 35, -5.0F, 1.0F, -3.0F, 10, 3, 6, 0.0F, false));
        torso.cubeList.add(new ModelBox(torso, 0, 39, -4.5F, -1.5F, -2.5F, 9, 7, 5, 0.0F, false));
        torso.cubeList.add(new ModelBox(torso, 46, 14, -5.0F, -7.0F, -3.0F, 3, 2, 6, 0.0F, true));
        torso.cubeList.add(new ModelBox(torso, 46, 14, 2.0F, -7.0F, -3.0F, 3, 2, 6, 0.0F, false));
        torso.cubeList.add(new ModelBox(torso, 54, 6, 2.0F, -7.0F, 3.0F, 3, 6, 2, 0.0F, false));
        torso.cubeList.add(new ModelBox(torso, 54, 6, -5.0F, -7.0F, 3.0F, 3, 6, 2, 0.0F, true));

        chestplate_slope2 = new RendererModel(this);
        chestplate_slope2.setRotationPoint(0.0F, -1.0F, -3.0F);
        setRotationAngle(chestplate_slope2, 0.2618F, 0.0F, 0.0F);
        torso.addChild(chestplate_slope2);
        chestplate_slope2.cubeList.add(new ModelBox(chestplate_slope2, 18, 31, -3.0F, 0.0F, 0.0F, 6, 2, 1, 0.0F, false));

        head = new RendererModel(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.cubeList.add(new ModelBox(head, 0, 0, -4.5F, -9.0F, -5.0F, 9, 4, 10, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 0, 14, -4.5F, -5.0F, -1.5F, 9, 5, 6, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 29, 5, -3.5F, -10.0F, -6.0F, 2, 6, 9, 0.0F, true));
        head.cubeList.add(new ModelBox(head, 29, 5, 1.5F, -10.0F, -6.0F, 2, 6, 9, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 42, 4, -5.0F, -5.0F, -5.0F, 2, 6, 4, 0.0F, true));
        head.cubeList.add(new ModelBox(head, 0, 25, -5.5F, -10.0F, -1.0F, 2, 7, 7, 0.0F, true));
        head.cubeList.add(new ModelBox(head, 0, 25, 3.5F, -10.0F, -1.0F, 2, 7, 7, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 0, 0, 3.5F, -10.0F, 6.0F, 2, 4, 2, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 0, 0, -5.5F, -10.0F, 6.0F, 2, 4, 2, 0.0F, true));
        head.cubeList.add(new ModelBox(head, 42, 4, 3.0F, -5.0F, -5.0F, 2, 6, 4, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 11, 25, -3.0F, -2.0F, -5.0F, 6, 4, 2, 0.0F, false));

        arm_r = new RendererModel(this);
        arm_r.setRotationPoint(-4.0F, 2.0F, 0.0F);
        arm_r.cubeList.add(new ModelBox(arm_r, 0, 53, -4.0F, -4.0F, -3.0F, 4, 5, 6, 0.0F, true));
        arm_r.cubeList.add(new ModelBox(arm_r, 20, 53, -3.5F, 6.0F, -3.0F, 5, 5, 6, 0.0F, true));
        arm_r.cubeList.add(new ModelBox(arm_r, 28, 44, -3.5F, 2.0F, -2.5F, 3, 4, 5, 0.0F, true));
        arm_r.cubeList.add(new ModelBox(arm_r, 38, 47, -5.0F, -5.0F, -2.0F, 3, 5, 6, 0.0F, true));
        arm_r.cubeList.add(new ModelBox(arm_r, 52, 41, -4.5F, 5.0F, -1.5F, 2, 7, 3, 0.0F, true));

        arm_l = new RendererModel(this);
        arm_l.setRotationPoint(4.0F, 2.0F, 0.0F);
        arm_l.cubeList.add(new ModelBox(arm_l, 0, 53, 0.0F, -4.0F, -3.0F, 4, 5, 6, 0.0F, false));
        arm_l.cubeList.add(new ModelBox(arm_l, 38, 47, 2.0F, -5.0F, -2.0F, 3, 5, 6, 0.0F, false));
        arm_l.cubeList.add(new ModelBox(arm_l, 20, 53, -1.5F, 6.0F, -3.0F, 5, 5, 6, 0.0F, false));
        arm_l.cubeList.add(new ModelBox(arm_l, 28, 44, 0.5F, 2.0F, -2.5F, 3, 4, 5, 0.0F, false));
        arm_l.cubeList.add(new ModelBox(arm_l, 52, 41, 2.5F, 5.0F, -1.5F, 2, 7, 3, 0.0F, false));

        leg_r = new RendererModel(this);
        leg_r.setRotationPoint(-2.0F, 12.0F, 0.0F);
        leg_r.cubeList.add(new ModelBox(leg_r, 34, 65, -2.5F, -0.5F, -2.5F, 5, 7, 5, 0.0F, true));

        thigh_guard_r2 = new RendererModel(this);
        thigh_guard_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(thigh_guard_r2, 0.0F, 0.0F, 0.1745F);
        leg_r.addChild(thigh_guard_r2);
        thigh_guard_r2.cubeList.add(new ModelBox(thigh_guard_r2, 18, 73, -3.0F, -1.0F, -3.0F, 3, 7, 6, 0.0F, true));
        thigh_guard_r2.cubeList.add(new ModelBox(thigh_guard_r2, 0, 78, -4.0F, 4.0F, -3.0F, 1, 2, 6, 0.0F, true));

        boot_r = new RendererModel(this);
        boot_r.setRotationPoint(0.0F, 4.0F, 0.0F);
        leg_r.addChild(boot_r);
        boot_r.cubeList.add(new ModelBox(boot_r, 0, 65, -5.0F, 6.0F, -3.0F, 6, 7, 6, 0.0F, true));

        boot_wing_r2 = new RendererModel(this);
        boot_wing_r2.setRotationPoint(-5.0F, 5.0F, 0.0F);
        setRotationAngle(boot_wing_r2, 0.7854F, 0.0F, 0.0F);
        boot_r.addChild(boot_wing_r2);
        boot_wing_r2.cubeList.add(new ModelBox(boot_wing_r2, 24, 65, -1.0F, 1.0F, -5.0F, 1, 4, 4, 0.0F, true));

        leg_l = new RendererModel(this);
        leg_l.setRotationPoint(2.0F, 12.0F, 0.0F);
        leg_l.cubeList.add(new ModelBox(leg_l, 34, 65, -2.5F, -0.5F, -2.5F, 5, 7, 5, 0.0F, false));

        thigh_guard_l2 = new RendererModel(this);
        thigh_guard_l2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(thigh_guard_l2, 0.0F, 0.0F, -0.1745F);
        leg_l.addChild(thigh_guard_l2);
        thigh_guard_l2.cubeList.add(new ModelBox(thigh_guard_l2, 18, 73, 0.0F, -1.0F, -3.0F, 3, 7, 6, 0.0F, false));
        thigh_guard_l2.cubeList.add(new ModelBox(thigh_guard_l2, 0, 78, 3.0F, 4.0F, -3.0F, 1, 2, 6, 0.0F, false));

        boot_l = new RendererModel(this);
        boot_l.setRotationPoint(0.0F, 4.0F, 0.0F);
        leg_l.addChild(boot_l);
        boot_l.cubeList.add(new ModelBox(boot_l, 0, 65, -1.0F, 6.0F, -3.0F, 6, 7, 6, 0.0F, false));

        boot_wing_l2 = new RendererModel(this);
        boot_wing_l2.setRotationPoint(5.0F, 5.0F, 0.0F);
        setRotationAngle(boot_wing_l2, 0.7854F, 0.0F, 0.0F);
        boot_l.addChild(boot_wing_l2);
        boot_wing_l2.cubeList.add(new ModelBox(boot_wing_l2, 24, 65, 0.0F, 1.0F, -5.0F, 1, 4, 4, 0.0F, false));
    }

	@Override
	public void render(LivingEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        head.showModel = slot == EquipmentSlotType.HEAD;

        leg_r.showModel = slot == EquipmentSlotType.LEGS;
        leg_l.showModel = slot == EquipmentSlotType.LEGS;

        thigh_guard_r2.showModel = slot == EquipmentSlotType.LEGS;
        thigh_guard_l2.showModel = slot == EquipmentSlotType.LEGS;

        torso.showModel = slot == EquipmentSlotType.CHEST;
        chestplate_slope2.showModel = slot == EquipmentSlotType.CHEST;
        arm_r.showModel = slot == EquipmentSlotType.CHEST;
        arm_l.showModel = slot == EquipmentSlotType.CHEST;

        boot_r.showModel = slot == EquipmentSlotType.FEET;
        boot_l.showModel = slot == EquipmentSlotType.FEET;

        boot_wing_r2.showModel = slot == EquipmentSlotType.FEET;
        boot_wing_l2.showModel = slot == EquipmentSlotType.FEET;

        bipedHeadwear.showModel = false;

        bipedHead = head;

        bipedBody = torso;
        bipedRightArm = arm_r;
        bipedLeftArm = arm_l;

        if (slot == EquipmentSlotType.LEGS)
        {
            bipedRightLeg = leg_r;
            bipedLeftLeg = leg_l;
        }
        else
        {
            bipedRightLeg = boot_r;
            bipedLeftLeg = boot_l;
        }
        super.render(entity, f, f1, f2, f3, f4, f5);
	}
	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}