package malum.event;

import malum.ClientRefferences;
import malum.items.curios.*;
import malum.registry.ModItems;
import net.minecraft.client.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
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
    static float Cooldown = 0;
    static float GlideCharge = 0;
    @SubscribeEvent
    public static void AirNecklaceFlight(TickEvent.PlayerTickEvent event) {
        PlayerEntity entity = event.player;
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemAirNecklace, entity).isPresent()) {

            GameSettings settings = ClientRefferences.getClientSettings();
            KeyBinding jump = settings.keyBindJump;
            if (Cooldown > 0) {
                if (!jump.isKeyDown()) {
                    Cooldown = 0;
                }
            }
            if (entity.onGround) {
                Cooldown = 1;
                GlideCharge = 25;
            } else {
                if (GlideCharge != 0) {
                    if (jump.isKeyDown()) {
                        if (Cooldown == 0) {
                            if (entity.getMotion().y < 0.2) {
                                entity.addVelocity(0, 0.1, 0);
                            }
                            entity.addVelocity(0, 0.05, 0);
                            GlideCharge -= 1;
                        }
                    }
                }
            }
            if (GlideCharge == 0) {
                if (jump.isKeyDown()) {
                    if (Cooldown == 0) {
                        if (entity.getMotion().y < -0.1) {
                            entity.addVelocity(0, 0.1, 0);
                        }
                    }
                }
            }
        }
    }
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
    }
    @SubscribeEvent
    public static void Hurt(LivingHurtEvent event)
    {
        LivingEntity entityLivingBase = event.getEntityLiving();
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
    public static void Damage(LivingDamageEvent event)
    {
        LivingEntity entityLivingBase = event.getEntityLiving();
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemThornsBelt, entityLivingBase).isPresent())
        {
            float damage = 1f;
            float health = entityLivingBase.getHealth();
            float value = entityLivingBase.getMaxHealth() - health;
            value *= 0.1;
            if (health <= entityLivingBase.getMaxHealth() / 4)
            {
                damage *= 2f;
            }
            damage += value;
            Objects.requireNonNull(event.getSource().getTrueSource()).attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity) entityLivingBase), damage);

        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemNetherNecklace, entityLivingBase).isPresent() || CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemWitherNecklace, entityLivingBase).isPresent())
        {
            if (event.getSource() == DamageSource.ON_FIRE || event.getSource() == DamageSource.IN_FIRE)
            {
                entityLivingBase.playSound(SoundEvents.ENTITY_BLAZE_BURN, 1, 1);
                event.setAmount(event.getAmount() / 2);
            }
            if (entityLivingBase.isInLava())
            {
                entityLivingBase.playSound(SoundEvents.ENTITY_BLAZE_HURT, 1, 1);
                event.setAmount(event.getAmount() / 3);

            }
        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemWitherNecklace, entityLivingBase).isPresent())
        {
            entityLivingBase.playSound(SoundEvents.ENTITY_WITHER_HURT, 1, 1);
            entityLivingBase.playSound(SoundEvents.ENTITY_WITHER_SKELETON_HURT, 1, 1);
            event.setAmount(event.getAmount() * 0.85f);
        }
    }
    @SubscribeEvent
    public static void Death(LivingDeathEvent event)
    {
        if (event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof LivingEntity)
        {
            LivingEntity entityLivingBase = (LivingEntity) event.getSource().getTrueSource();
            if (entityLivingBase != null) {
                Hand hand = entityLivingBase.swingingHand;
                if (hand != null) {
                    ItemStack stack = entityLivingBase.getHeldItem(hand);
                    if (stack.getItem() == Items.WOODEN_SWORD) {
                        Entity target = event.getEntityLiving();
                        if (target instanceof WitherSkeletonEntity) {
                            if (((WitherSkeletonEntity) target).getHealth() <= 0) {
                                stack.setDamage(entityLivingBase.getHeldItem(hand).getMaxDamage());
                                entityLivingBase.setHeldItem(hand, ModItems.withering_rapier.getDefaultInstance());
                            }
                        }
                    }
                }
            }
        }
    }
}