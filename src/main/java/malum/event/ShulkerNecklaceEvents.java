package malum.event;

import com.google.common.collect.Lists;
import malum.MalumMod;
import malum.items.curios.ItemShulkerNecklace;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosAPI;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber
public class ShulkerNecklaceEvents
{
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
}