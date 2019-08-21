package malum.event;

import malum.items.curios.ItemHealingBelt;
import malum.items.curios.ItemThornsBelt;
import malum.items.curios.ItemWaterNecklace;
import malum.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosAPI;

import java.util.Objects;
import java.util.UUID;

import static net.minecraft.entity.LivingEntity.SWIM_SPEED;

@Mod.EventBusSubscriber
public class PlayerEventHandler
{
    private static final UUID ID = UUID.fromString("6d3be89e-37e6-453f-8654-2fd37d85b2ab");
    @SubscribeEvent
    public static void Tick (TickEvent.PlayerTickEvent event)
    {
        LivingEntity entityLivingBase = event.player;
        if (entityLivingBase.isInWater())
        {
            IAttributeInstance attributeInstance = entityLivingBase.getAttributes().getAttributeInstance(SWIM_SPEED);
            assert attributeInstance != null;
            double Y = entityLivingBase.getPosition().getY();
            double ValueofY = 110 - Y;
            double swimSpeed = 0.1D;
            if (ValueofY > 0)
            {
                swimSpeed += (float) (ValueofY / 80);
            }
            final AttributeModifier SWIM_SPEED_INCREASE = new AttributeModifier(ID, "Swim Speed modifier", swimSpeed, AttributeModifier.Operation.ADDITION);

            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemWaterNecklace, entityLivingBase).isPresent())
            {
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
            else
            {
                if (attributeInstance.getModifier(ID) != null)
                {
                    attributeInstance.removeModifier(SWIM_SPEED_INCREASE);
                }
            }
        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemThornsBelt, entityLivingBase).isPresent())
        {
        }
    }
    @SubscribeEvent
    public static void Hurt(LivingHurtEvent event)
    {
        LivingEntity entityLivingBase = event.getEntityLiving();
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemThornsBelt, entityLivingBase).isPresent())
        {
            float damage = 1f;
            float health = entityLivingBase.getHealth();
            float value = 15 - health;
            if (value > 0)
            {
                damage += value;
            }
            Objects.requireNonNull(event.getSource().getTrueSource()).attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity) entityLivingBase), damage);

        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemHealingBelt, entityLivingBase).isPresent())
        {
            float maxhealth = entityLivingBase.getMaxHealth();
            float health = entityLivingBase.getHealth();
            float modifier = 0.5f;
            float value = (maxhealth - health) * modifier;
            entityLivingBase.setAbsorptionAmount(entityLivingBase.getAbsorptionAmount() + value);
        }
    }
    @SubscribeEvent
    public static void Attack(LivingDeathEvent event)
    {
        if (event.getSource().getTrueSource() instanceof LivingEntity)
        {
            LivingEntity entityLivingBase = (LivingEntity) event.getSource().getTrueSource();
            if (entityLivingBase != null) {
                Hand hand = entityLivingBase.swingingHand;
                if (entityLivingBase.getHeldItem(entityLivingBase.swingingHand).getItem() == Items.WOODEN_SWORD) {
                    Entity target = event.getEntityLiving();
                    if (target instanceof WitherSkeletonEntity) {
                        if (((WitherSkeletonEntity) target).getHealth() <= 0) {
                            entityLivingBase.getHeldItem(hand).setDamage(entityLivingBase.getHeldItem(hand).getMaxDamage());
                            entityLivingBase.setHeldItem(hand, ModItems.withering_rapier.getDefaultInstance());
                        }
                    }
                }
            }
        }
    }
}