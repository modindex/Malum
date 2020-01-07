package malum.items.curios;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.capability.ICurio;

import java.util.UUID;

import static net.minecraft.entity.LivingEntity.SWIM_SPEED;

public class ItemWaterNecklace extends Item implements ICurio
{
    public ItemWaterNecklace(Item.Properties builder) {
        super(builder);
    }

    private static final UUID ID = UUID.fromString("6d3be89e-37e6-453f-8654-2fd37d85b2ab");
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused) {
        return CapCurioItem.createProvider(new ICurio() {

            @Override
            public void playEquipSound(LivingEntity entityLivingBase) {
                entityLivingBase.world.playSound(null, entityLivingBase.getPosition(),
                        SoundEvents.ITEM_ARMOR_EQUIP_GOLD, SoundCategory.NEUTRAL,
                        1.0f, 1.0f);
            }

            @Override
            public void onCurioTick(String identifier, int index, LivingEntity entityLivingBase)
            {
                if (entityLivingBase.isInWater())
                {
                    IAttributeInstance attributeInstance = entityLivingBase.getAttributes().getAttributeInstance(SWIM_SPEED);
                    assert attributeInstance != null;
                    final AttributeModifier SWIM_SPEED_INCREASE = new AttributeModifier(ID, "Swim Speed modifier", getSwimSpeed(entityLivingBase), AttributeModifier.Operation.ADDITION);

                    if (!entityLivingBase.getEntityWorld().isRemote && entityLivingBase.ticksExisted % 19 == 0)
                    {
                        if (attributeInstance.getModifier(ID) != null)
                        {
                            attributeInstance.removeModifier(SWIM_SPEED_INCREASE);
                        }
                    }
                    if (attributeInstance.getModifier(ID) == null)
                    {
                        attributeInstance.applyModifier(SWIM_SPEED_INCREASE);
                    }
                }
            }

            @Override
            public void onUnequipped(String identifier, LivingEntity entityLivingBase)
            {
                IAttributeInstance attributeInstance = entityLivingBase.getAttributes().getAttributeInstance(SWIM_SPEED);
                assert attributeInstance != null;
                final AttributeModifier SWIM_SPEED_INCREASE = new AttributeModifier(ID, "Swim Speed modifier", getSwimSpeed(entityLivingBase), AttributeModifier.Operation.ADDITION);

                if (attributeInstance.getModifier(ID) != null)
                {
                    attributeInstance.removeModifier(SWIM_SPEED_INCREASE);
                }
            }

            public double getSwimSpeed(LivingEntity entityLivingBase)
            {
                double Y = entityLivingBase.getPosition().getY();
                double ValueofY = 110 - Y;
                double swimSpeed = 0.1D;
                if (ValueofY > 0)
                {
                    swimSpeed += (float) (ValueofY / 80);
                }
                return swimSpeed;
            }
            @Override
            public boolean canRightClickEquip() {

                return true;
            }
        });
    }
}