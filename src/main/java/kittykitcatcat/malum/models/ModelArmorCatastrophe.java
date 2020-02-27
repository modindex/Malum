package kittykitcatcat.malum.models;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class ModelArmorCatastrophe extends ModelArmor
{
    private final RendererModel rightleg;
    private final RendererModel leftleg;
    private final RendererModel body;
    private final RendererModel head;
    private final RendererModel headWings;
    private final RendererModel leftarm;
    private final RendererModel rightarm;
    private final RendererModel leftBoot;
    private final RendererModel rightBoot;

    public ModelArmorCatastrophe(EquipmentSlotType slot)
    {
        super(slot, 128, 128);
        textureWidth = 128;
        textureHeight = 128;


        rightleg = new RendererModel(this);
        rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        rightleg.cubeList.add(new ModelBox(rightleg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 7, 4, 0.3F, false));

        leftleg = new RendererModel(this);
        leftleg.setRotationPoint(2.0F, 12.0F, 0.0F);
        leftleg.cubeList.add(new ModelBox(leftleg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 7, 4, 0.3F, true));

        body = new RendererModel(this);
        body.setRotationPoint(0.0F, 6.0F, 0.0F);
        body.cubeList.add(new ModelBox(body, 16, 16, -4.0F, -6.0F, -2.0F, 8, 12, 4, 0.3F, false));
        body.cubeList.add(new ModelBox(body, 16, 32, -4.0F, -6.0F, -2.0F, 8, 12, 4, 0.6F, false));

        head = new RendererModel(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.cubeList.add(new ModelBox(head, 92, 7, -4.0F, -9.0F, -4.5F, 8, 1, 5, 0.1F, false));
        head.cubeList.add(new ModelBox(head, 84, 4, 4.0F, -9.0F, -4.5F, 1, 9, 3, 0.1F, false));
        head.cubeList.add(new ModelBox(head, 84, 4, -5.0F, -9.0F, -4.5F, 1, 9, 3, 0.1F, false));
        head.cubeList.add(new ModelBox(head, 92, 13, 2.0F, -2.0F, -4.5F, 2, 2, 1, 0.1F, false));
        head.cubeList.add(new ModelBox(head, 92, 13, -4.0F, -2.0F, -4.5F, 2, 2, 1, 0.1F, false));
        head.cubeList.add(new ModelBox(head, 98, 13, -4.0F, -8.0F, -4.5F, 8, 3, 1, 0.1F, false));

        headWings = new RendererModel(this);
        headWings.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(headWings, 0.0873F, 0.0F, 0.0F);
        headWings.cubeList.add(new ModelBox(headWings, 64, 5, -5.0F, -33.0F, 1.0F, 1, 2, 9, 0.0F, false));
        headWings.cubeList.add(new ModelBox(headWings, 65, 6, -5.0F, -31.0F, 1.0F, 1, 2, 8, 0.0F, false));
        headWings.cubeList.add(new ModelBox(headWings, 64, 5, 4.0F, -33.0F, 1.0F, 1, 2, 9, 0.0F, false));
        headWings.cubeList.add(new ModelBox(headWings, 66, 7, -5.0F, -29.0F, 0.9F, 1, 1, 7, 0.0F, false));
        headWings.cubeList.add(new ModelBox(headWings, 66, 7, 4.0F, -29.0F, 0.9F, 1, 1, 7, 0.0F, false));
        headWings.cubeList.add(new ModelBox(headWings, 65, 6, 4.0F, -31.0F, 1.0F, 1, 2, 8, 0.0F, false));

        leftarm = new RendererModel(this);
        leftarm.setRotationPoint(4.0F, 2.0F, 0.0F);
        leftarm.cubeList.add(new ModelBox(leftarm, 40, 16, 0.0F, -2.0F, -2.0F, 4, 12, 4, 0.1F, true));
        leftarm.cubeList.add(new ModelBox(leftarm, 40, 32, 1.5F, 2.0F, -2.5F, 3, 5, 5, -0.2F, true));
        leftarm.cubeList.add(new ModelBox(leftarm, 56, 23, 1.0F, -4.0F, -2.5F, 4, 5, 5, -0.3F, true));

        rightarm = new RendererModel(this);
        rightarm.setRotationPoint(-4.0F, 2.0F, 0.0F);
        rightarm.cubeList.add(new ModelBox(rightarm, 40, 16, -4.0F, -2.0F, -2.0F, 4, 12, 4, 0.1F, false));
        rightarm.cubeList.add(new ModelBox(rightarm, 40, 32, -4.5F, 2.0F, -2.5F, 3, 5, 5, -0.2F, false));
        rightarm.cubeList.add(new ModelBox(rightarm, 56, 23, -5.0F, -4.0F, -2.5F, 4, 5, 5, -0.2F, false));

        leftBoot = new RendererModel(this);
        leftBoot.setRotationPoint(0.0F, 24.0F, 0.0F);
        leftBoot.cubeList.add(new ModelBox(leftBoot, 0, 44, 0.0F, 8.0F, -2.0F, 4, 4, 4, 0.4F, true));

        rightBoot = new RendererModel(this);
        rightBoot.setRotationPoint(0.0F, 24.0F, 0.0F);
        rightBoot.cubeList.add(new ModelBox(rightBoot, 0, 44, -4.0F, 8.0F, -2.0F, 4, 4, 4, 0.4F, false));

        head.addChild(headWings);
    }

    @Override
    public void render(LivingEntity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {

        if (this.isSneak)
        {
            body.rotateAngleX = 0.5F;
        }
        head.showModel = slot == EquipmentSlotType.HEAD;

        leftleg.showModel = slot == EquipmentSlotType.LEGS;
        rightleg.showModel = slot == EquipmentSlotType.LEGS;

        body.showModel = slot == EquipmentSlotType.CHEST;
        leftarm.showModel = slot == EquipmentSlotType.CHEST;
        rightarm.showModel = slot == EquipmentSlotType.CHEST;

        leftBoot.showModel = slot == EquipmentSlotType.FEET;
        rightBoot.showModel = slot == EquipmentSlotType.FEET;

        bipedHeadwear.showModel = false;
        bipedHead = head;

        bipedBody = body;
        bipedRightArm = rightarm;
        bipedLeftArm = leftarm;

        if (slot == EquipmentSlotType.LEGS)
        {
            bipedRightLeg = rightleg;
            bipedLeftLeg = leftleg;
        }
        else
        {
            bipedRightLeg = rightBoot;
            bipedLeftLeg = leftBoot;
        }
        super.render(entity, f, f1, f2, f3, f4, f5);
    }

    public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}