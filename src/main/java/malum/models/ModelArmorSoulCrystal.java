package malum.models;//Made with Blockbench
//Paste this code into your mod.

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class ModelArmorSoulCrystal extends ModelArmor
{
	private final RendererModel torso;
	private final RendererModel chestplate_slope;
	private final RendererModel head;
	private final RendererModel arm_r;
	private final RendererModel arm_l;
	private final RendererModel leg_r;
	private final RendererModel boot_r;
	private final RendererModel boot_wing_r;
	private final RendererModel thigh_guard_r;
	private final RendererModel leg_l;
	private final RendererModel thigh_guard_l;
	private final RendererModel boot_l;
	private final RendererModel boot_wing_l;

	public ModelArmorSoulCrystal(EquipmentSlotType slot) {
        super(slot, 64, 64);
		textureWidth = 64;
		textureHeight = 64;

        torso = new RendererModel(this);
        torso.setRotationPoint(0.0F, 6.0F, 0.0F);
        torso.cubeList.add(new ModelBox(torso, 0, 25, -5.0F, -5.0F, -3.0F, 10, 5, 6, 0.0F, false));
        torso.cubeList.add(new ModelBox(torso, 0, 36, -4.5F, -0.5F, -2.5F, 9, 6, 5, 0.0F, false));
        torso.cubeList.add(new ModelBox(torso, 26, 23, -5.0F, -7.0F, -3.0F, 3, 2, 6, 0.0F, true));
        torso.cubeList.add(new ModelBox(torso, 26, 23, 2.0F, -7.0F, -3.0F, 3, 2, 6, 0.0F, false));
        torso.cubeList.add(new ModelBox(torso, 30, 16, 2.0F, -7.0F, 3.0F, 3, 5, 2, 0.0F, false));
        torso.cubeList.add(new ModelBox(torso, 30, 16, -5.0F, -7.0F, 3.0F, 3, 5, 2, 0.0F, true));

        chestplate_slope = new RendererModel(this);
        chestplate_slope.setRotationPoint(-8.0F, 0.0F, 5.0F);
        setRotationAngle(chestplate_slope, 0.2618F, 0.0F, 0.0F);
        torso.addChild(chestplate_slope);
        chestplate_slope.cubeList.add(new ModelBox(chestplate_slope, 19, 22, 6.0F, -2.0706F, -7.7274F, 4, 2, 1, 0.0F, false));

        head = new RendererModel(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.cubeList.add(new ModelBox(head, 0, 0, -4.5F, -9.0F, -5.0F, 9, 4, 6, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 21, 1, -1.5F, -10.0F, -6.0F, 3, 6, 9, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 36, 0, -5.0F, -5.0F, -5.0F, 2, 6, 4, 0.0F, true));
        head.cubeList.add(new ModelBox(head, 0, 10, -5.0F, -10.0F, -1.0F, 2, 7, 7, 0.0F, true));
        head.cubeList.add(new ModelBox(head, 0, 10, 3.0F, -10.0F, -1.0F, 2, 7, 7, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 11, 10, 3.0F, -10.0F, 6.0F, 2, 4, 2, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 11, 10, -5.0F, -10.0F, 6.0F, 2, 4, 2, 0.0F, true));
        head.cubeList.add(new ModelBox(head, 36, 0, 3.0F, -5.0F, -5.0F, 2, 6, 4, 0.0F, false));

        arm_r = new RendererModel(this);
        arm_r.setRotationPoint(-4.0F, 2.0F, 0.0F);
        arm_r.cubeList.add(new ModelBox(arm_r, 44, 14, -4.0F, -4.0F, -3.0F, 4, 6, 6, 0.0F, true));
        arm_r.cubeList.add(new ModelBox(arm_r, 42, 26, -4.5F, 5.0F, -3.0F, 5, 6, 6, 0.0F, true));
        arm_r.cubeList.add(new ModelBox(arm_r, 48, 7, -4.5F, 3.0F, -2.5F, 3, 2, 5, 0.0F, true));

        arm_l = new RendererModel(this);
        arm_l.setRotationPoint(4.0F, 2.0F, 0.0F);
        arm_l.cubeList.add(new ModelBox(arm_l, 44, 14, 0.0F, -4.0F, -3.0F, 4, 6, 6, 0.0F, false));
        arm_l.cubeList.add(new ModelBox(arm_l, 42, 26, -0.5F, 5.0F, -3.0F, 5, 6, 6, 0.0F, false));
        arm_l.cubeList.add(new ModelBox(arm_l, 48, 7, 1.5F, 3.0F, -2.5F, 3, 2, 5, 0.0F, false));

        leg_r = new RendererModel(this);
        leg_r.setRotationPoint(-2.0F, 12.0F, 0.0F);
        leg_r.cubeList.add(new ModelBox(leg_r, 12, 47, -2.5F, -0.5F, -2.5F, 5, 7, 5, 0.0F, true));

        boot_r = new RendererModel(this);
        boot_r.setRotationPoint(-4.0F, 0.0F, 8.0F);
        leg_r.addChild(boot_r);
        boot_r.cubeList.add(new ModelBox(boot_r, 29, 39, -1.0F, 6.0F, -3.0F, 6, 7, 6, 0.0F, true));

        boot_wing_r = new RendererModel(this);
        boot_wing_r.setRotationPoint(-7.0F, 9.0F, 0.0F);
        setRotationAngle(boot_wing_r, 0.7854F, 0.0F, 0.0F);
        boot_r.addChild(boot_wing_r);
        boot_wing_r.cubeList.add(new ModelBox(boot_wing_r, 25, 53, 5.0F, -1.6569F, -1.6569F, 1, 4, 7, 0.0F, true));

        thigh_guard_r = new RendererModel(this);
        thigh_guard_r.setRotationPoint(-8.0F, 0.0F, 8.0F);
        setRotationAngle(thigh_guard_r, 0.0F, 0.0F, 0.1745F);
        leg_r.addChild(thigh_guard_r);
        thigh_guard_r.cubeList.add(new ModelBox(thigh_guard_r, 0, 53, 4.8785F, -2.3892F, -11.0F, 3, 5, 6, 0.0F, true));

        leg_l = new RendererModel(this);
        leg_l.setRotationPoint(2.0F, 12.0F, 0.0F);
        leg_l.cubeList.add(new ModelBox(leg_l, 12, 47, -2.5F, -0.5F, -2.5F, 5, 7, 5, 0.0F, false));

        thigh_guard_l = new RendererModel(this);
        thigh_guard_l.setRotationPoint(-8.0F, 0.0F, 8.0F);
        setRotationAngle(thigh_guard_l, 0.0F, 0.0F, -0.1745F);
        leg_l.addChild(thigh_guard_l);
        thigh_guard_l.cubeList.add(new ModelBox(thigh_guard_l, 0, 53, 7.8785F, 0.3892F, -11.0F, 3, 5, 6, 0.0F, false));

        boot_l = new RendererModel(this);
        boot_l.setRotationPoint(-8.0F, 0.0F, 8.0F);
        leg_l.addChild(boot_l);
        boot_l.cubeList.add(new ModelBox(boot_l, 29, 39, 7.0F, 6.0F, -3.0F, 6, 7, 6, 0.0F, false));

        boot_wing_l = new RendererModel(this);
        boot_wing_l.setRotationPoint(3.0F, 9.0F, 0.0F);
        setRotationAngle(boot_wing_l, 0.7854F, 0.0F, 0.0F);
        boot_l.addChild(boot_wing_l);
        boot_wing_l.cubeList.add(new ModelBox(boot_wing_l, 25, 53, 10.0F, -1.6569F, -1.6569F, 1, 4, 7, 0.0F, false));
	}
	@Override
	public void render(LivingEntity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        head.showModel = slot == EquipmentSlotType.HEAD;

        leg_r.showModel = slot == EquipmentSlotType.LEGS;
        leg_l.showModel = slot == EquipmentSlotType.LEGS;
        thigh_guard_r.showModel = slot == EquipmentSlotType.LEGS;
        thigh_guard_l.showModel = slot == EquipmentSlotType.LEGS;

        torso.showModel = slot == EquipmentSlotType.CHEST;
        chestplate_slope.showModel = slot == EquipmentSlotType.CHEST;
        arm_r.showModel = slot == EquipmentSlotType.CHEST;
        arm_l.showModel = slot == EquipmentSlotType.CHEST;

        boot_r.showModel = slot == EquipmentSlotType.FEET;
        boot_l.showModel = slot == EquipmentSlotType.FEET;

        boot_wing_r.showModel = slot == EquipmentSlotType.FEET;
        boot_wing_l.showModel = slot == EquipmentSlotType.FEET;

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