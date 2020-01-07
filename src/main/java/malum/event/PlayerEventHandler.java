package malum.event;

import com.google.common.collect.Lists;
import malum.MalumMod;
import malum.items.curios.*;
import malum.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosAPI;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Mod.EventBusSubscriber
public class PlayerEventHandler
{

    @SubscribeEvent
    public static void Jump(LivingEvent.LivingJumpEvent event)
    {
        LivingEntity entityLivingBase = event.getEntityLiving();
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemNetherNecklace, entityLivingBase).isPresent())
        {
            entityLivingBase.setVelocity(entityLivingBase.getMotion().x, 0, entityLivingBase.getMotion().z);
        }
    }

    @SubscribeEvent
    public static void Heal(LivingHealEvent event)
    {
        Random random = new Random();
        LivingEntity entity = event.getEntityLiving();
        if (entity instanceof PlayerEntity)
        {
            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemShulkerNecklace, entity).isPresent())
            {
                double rand = random.nextDouble();
                double minimum = 0.2;
                minimum += event.getAmount();
                if (rand <= minimum)
                {
                    double x = entity.getPosition().getX();
                    double y = entity.getPosition().getY();
                    double z = entity.getPosition().getZ();
                    AxisAlignedBB bounds = new AxisAlignedBB(x - 10, y - 10, z - 10, x + 10, y + 10, z + 10);
                    List<Entity> targets = entity.world.getEntitiesWithinAABBExcludingEntity(entity, bounds);
                    List<LivingEntity> livingTargets = Lists.newArrayList();
                    if (!targets.isEmpty())
                    {
                        for (Entity e : targets)
                        {
                            if (e instanceof LivingEntity)
                            {
                                livingTargets.add((LivingEntity) e);
                            }
                        }
                    }
                    if (!targets.isEmpty())
                    {
                        LivingEntity target = entity.world.getClosestEntity(livingTargets, EntityPredicate.DEFAULT, entity, x, y, z);
                        if (target != null)
                        {
                            ShulkerBulletEntity shulkerBulletEntity = new ShulkerBulletEntity(entity.world, entity, target, Direction.Axis.Y);
                            double xVel = random.nextDouble() * 0.5 * (random.nextDouble() > 0.5 ? -1 : 1);
                            double zVel = random.nextDouble() * 0.5 * (random.nextDouble() > 0.5 ? -1 : 1);
                            shulkerBulletEntity.posY += 1;
                            shulkerBulletEntity.addVelocity(xVel, 0.1, zVel);
                            entity.world.addEntity(shulkerBulletEntity);
                            entity.setAIMoveSpeed(entity.getAIMoveSpeed() * 2);
                            shulkerBulletEntity.getTags().add("noLevitate");
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void AddPotion(PotionEvent.PotionAddedEvent event)
    {
        if (event.getEntityLiving().getLastDamageSource() != null)
        {
            if (event.getEntityLiving().getLastDamageSource().getImmediateSource() != null)
            {
                Entity applyingProjectile = event.getEntityLiving().getLastDamageSource().getImmediateSource();
                if (applyingProjectile instanceof ShulkerBulletEntity)
                {
                    if (applyingProjectile.getTags().contains("noLevitate"))
                    {
                        MalumMod.LOGGER.info("noLev");
                        event.getEntityLiving().getTags().add("removeLevitate");
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void RemoveLevitate(LivingEvent.LivingUpdateEvent event)
    {
        if (event.getEntityLiving().getTags().contains("removeLevitate"))
        {
            event.getEntityLiving().getTags().remove("removeLevitate");
            event.getEntityLiving().removePotionEffect(Effects.LEVITATION);
        }
    }
    @SubscribeEvent
    public static void DamageEvent(LivingDamageEvent event)
    {
        LivingEntity attacked = event.getEntityLiving(); // you were attacked by a mob
        Entity attacking = event.getSource().getTrueSource(); //a mob was attacked by you
        Entity attackingP = event.getSource().getImmediateSource(); //you were attacked by a mobs projectile

        if (event.getSource() == DamageSource.FALL)
        {
            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemNetherNecklace, attacked).isPresent())
            {
                event.setCanceled(true);
            }
            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemAirNecklace, attacked).isPresent())
            {
                event.setCanceled(true);
            }
        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemThornsBelt, attacked).isPresent())
        {
            float damage = 1f;
            float health = attacked.getHealth();
            float value = attacked.getMaxHealth() - health;
            value *= 0.1;
            if (health <= attacked.getMaxHealth() / 4)
            {
                damage *= 2f;
            }
            damage += value;
            Objects.requireNonNull(event.getSource().getTrueSource()).attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity) attacked), damage);

        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemWitherNecklace, attacked).isPresent())
        {
            attacked.playSound(SoundEvents.ENTITY_WITHER_HURT, 1, 1);
            attacked.playSound(SoundEvents.ENTITY_WITHER_SKELETON_HURT, 1, 1);
            event.setAmount(event.getAmount() * 0.85f);
        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemNetherNecklace, attacked).isPresent())
        {
            attacked.setSprinting(true);
        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemHealingBelt, attacked).isPresent())
        {
            float maxhealth = attacked.getMaxHealth();
            float health = attacked.getHealth();
            float modifier = 0.5f;
            float value = (maxhealth - health) * modifier;
            attacked.setAbsorptionAmount(attacked.getAbsorptionAmount() + value);
        }
    }

    @SubscribeEvent
    public static void Death(LivingDeathEvent event)
    {
        if (event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof PlayerEntity)
        {
            PlayerEntity entityLivingBase = (PlayerEntity) event.getSource().getTrueSource();
            if (entityLivingBase.swingingHand != null)
            {
                Hand hand = entityLivingBase.swingingHand;

                ItemStack stack = entityLivingBase.getHeldItem(hand);
                if (!stack.isEmpty())
                {
                    if (stack.getItem() == Items.WOODEN_SWORD)
                    {
                        Entity target = event.getEntityLiving();
                        if (target instanceof WitherSkeletonEntity)
                        {
                            entityLivingBase.setHeldItem(hand, ModItems.withering_rapier.getDefaultInstance());
                        }
                    }
                }
            }
        }
    }
}