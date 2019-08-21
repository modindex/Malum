package malum.items.curios;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;

import static net.minecraft.entity.LivingEntity.SWIM_SPEED;

public class AttributeWaterNecklace extends AttributeModifier
{
    public AttributeWaterNecklace(String nameIn, double amountIn, Operation operationIn, PlayerEntity player)
    {
        super(nameIn, amountIn, operationIn);
        IAttributeInstance attributeInstance = player.getAttributes().getAttributeInstance(SWIM_SPEED);
        assert attributeInstance != null;
        double Y = player.getPosition().getY();
        double ValueofY = 110 - Y;
        if (ValueofY > 0) {
            amountIn += (float) (ValueofY / 80);
        }
    }
}