package malum.event;

import malum.MalumMod;
import malum.capabilities.PlayerProperties;
import malum.items.tools.SoulEaterSwordItem;
import malum.spirit_augmentation.SpiritAugmentationData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SoulEaterEvents
{
    @SubscribeEvent
    public static void decreaseDamageBoost(LivingEvent.LivingUpdateEvent event)
    {
        if (event.getEntityLiving() instanceof PlayerEntity)
        {
            PlayerEntity playerEntity = (PlayerEntity) event.getEntityLiving();
            if (playerEntity.ticksExisted % 20 == 0)
            {
                float amount = 0.9f;
                if (playerEntity.getHeldItemMainhand().getItem() instanceof SoulEaterSwordItem)
                {
                    amount *= 1 - (SpiritAugmentationData.getAugmentAmountFromStack(playerEntity.getHeldItemMainhand(), MalumMod.soul_eater_boost_upkeep_augment) / 100);
                }
                if (PlayerProperties.getSoulEaterDamageBoost(playerEntity) <= 0.1f)
                {
                    amount = 0;
                }
                PlayerProperties.setSoulEaterDamageBoost(playerEntity, PlayerProperties.getSoulEaterDamageBoost(playerEntity) * amount);
                MalumMod.LOGGER.info(PlayerProperties.getSoulEaterDamageBoost(playerEntity));
            }
        }
    }
    @SubscribeEvent
    public static void increaseDamageBoost(LivingDeathEvent event)
    {
        if (event.getSource().getTrueSource() instanceof PlayerEntity)
        {
            PlayerEntity playerEntity = (PlayerEntity) event.getSource().getTrueSource();
            if (playerEntity.getHeldItemMainhand().getItem() instanceof SoulEaterSwordItem)
            {
                float amount = SpiritAugmentationData.getAugmentAmountFromStack(playerEntity.getHeldItemMainhand(),MalumMod.soul_eater_boost_gain_augment) / 100;
                PlayerProperties.setSoulEaterDamageBoost(playerEntity, PlayerProperties.getSoulEaterDamageBoost(playerEntity) + 2 + (2 * amount));
            }
        }
    }
    @SubscribeEvent
    public static void applyExtraDamage(LivingHurtEvent event)
    {
        if (event.getSource().getTrueSource() instanceof PlayerEntity)
        {
            PlayerEntity playerEntity = (PlayerEntity) event.getSource().getTrueSource();
            if (playerEntity.getHeldItemMainhand().getItem() instanceof SoulEaterSwordItem)
            {
                PlayerProperties.setSoulEaterDamageBoost(playerEntity, PlayerProperties.getSoulEaterDamageBoost(playerEntity) + 0.25f);
                event.setAmount(event.getAmount() + PlayerProperties.getSoulEaterDamageBoost(playerEntity));
            }
        }
    }
}