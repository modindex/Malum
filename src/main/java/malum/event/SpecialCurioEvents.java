package malum.event;

import com.google.common.collect.Lists;
import malum.MalumMod;
import malum.items.curios.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosAPI;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber
public class SpecialCurioEvents
{
    @SubscribeEvent
    public static void heal(LivingHealEvent event)
    {
        LivingEntity entity = event.getEntityLiving();
        if (entity instanceof PlayerEntity)
        {
            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemShulkerNecklace, entity).isPresent())
            {
                Random random = new Random();
                if (MathHelper.nextInt(random,0, 4) == 0)
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
                            double xVel = MathHelper.nextDouble(random, -0.5, 0.5);
                            double zVel = MathHelper.nextDouble(random, -0.5, 0.5);
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
    public static void addPotion(PotionEvent.PotionAddedEvent event)
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
    public static void removeLevitate(LivingEvent.LivingUpdateEvent event)
    {
        if (event.getEntityLiving().getTags().contains("removeLevitate"))
        {
            event.getEntityLiving().getTags().remove("removeLevitate");
            event.getEntityLiving().removePotionEffect(Effects.LEVITATION);
        }
    }
    @SubscribeEvent
    public static void phantomTargetEvent(LivingSetAttackTargetEvent event)
    {
        if (event.getTarget() instanceof PlayerEntity)
        {
            PlayerEntity playerEntity = (PlayerEntity) event.getTarget();
            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemPhantomRing, playerEntity).isPresent())
            {
                if (event.getEntityLiving() instanceof PhantomEntity)
                {
                    ((PhantomEntity) event.getEntityLiving()).setAttackTarget(null);
                }
            }
        }
    }

    @SubscribeEvent
    public static void netherNecklaceCancelJumpEvent(LivingEvent.LivingJumpEvent event)
    {
        LivingEntity entityLivingBase = event.getEntityLiving();
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemNetherNecklace, entityLivingBase).isPresent())
        {
            entityLivingBase.setVelocity(entityLivingBase.getMotion().x, 0, entityLivingBase.getMotion().z);
        }
    }

    @SubscribeEvent
    public static void cancelFallDamage(LivingAttackEvent event)
    {
        LivingEntity attacked = event.getEntityLiving();

        if (event.getSource() == DamageSource.FALL)
        {
            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemPhantomNecklace, attacked).isPresent()
                || CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemNetherNecklace, attacked).isPresent())
            {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void cancelBerryDamage(LivingAttackEvent event)
    {
        LivingEntity attacked = event.getEntityLiving();

        if (event.getSource() == DamageSource.SWEET_BERRY_BUSH)
        {
            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemBerryRing, attacked).isPresent())
            {
                event.setCanceled(true);
            }
        }
    }
    @SubscribeEvent
    public static void removeNauseaAndHunger(LivingEvent.LivingUpdateEvent event)
    {
        if (event.getEntityLiving() instanceof PlayerEntity)
        {
            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemRottenBelt, event.getEntityLiving()).isPresent())
            {
                if (event.getEntityLiving().getActivePotionEffect(Effects.NAUSEA) != null)
                {
                    event.getEntityLiving().removePotionEffect(Effects.NAUSEA);
                }
                if (event.getEntityLiving().getActivePotionEffect(Effects.HUNGER) != null)
                {
                    event.getEntityLiving().removePotionEffect(Effects.HUNGER);
                }
            }
        }
    }

    @SubscribeEvent
    public static void thornsAndHealing(LivingHurtEvent event)
    {
        LivingEntity livingEntity = event.getEntityLiving();

        if (livingEntity instanceof PlayerEntity)
        {
            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemThornsBelt, livingEntity).isPresent())
            {
                if (MathHelper.nextDouble(new Random(), 0, 1) <= 0.5)
                {
                    float damage = 1.5f;
                    if (livingEntity.getHealth() <= livingEntity.getMaxHealth() / 4)
                    {
                        damage = 3;
                    }
                    if (event.getSource().getTrueSource() instanceof LivingEntity)
                    {
                        event.getSource().getTrueSource().attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity) livingEntity), damage);
                    }
                }
            }
            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemHealingBelt, livingEntity).isPresent())
            {
                if (MathHelper.nextDouble(new Random(), 0, 1) <= 0.1)
                {
                    livingEntity.world.playSound((PlayerEntity) livingEntity, livingEntity.getPosition(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 1, 2);
                    livingEntity.setAbsorptionAmount(2);
                }

            }
        }
    }
}