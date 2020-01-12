package malum.event;

import malum.items.curios.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosAPI;

import java.util.Objects;

@Mod.EventBusSubscriber
public class DamageEvents
{
    @SubscribeEvent
    public static void DamageEvent(LivingDamageEvent event)
    {
        LivingEntity attacked = event.getEntityLiving();
        Entity attacking = event.getSource().getTrueSource();
        Entity attackingProjectile = event.getSource().getImmediateSource();

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
}