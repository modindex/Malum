package kittykitcatcat.malum.models;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;

public class ModelArmor extends BipedModel
{
    protected final EquipmentSlotType slot;

    public ModelArmor(EquipmentSlotType slot, int height, int width)
    {
        super(0f, 0f, width, height);
        this.slot = slot;
        this.textureHeight = height;
        this.textureWidth = width;
    }
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
