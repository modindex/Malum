package malum.event;

import malum.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WitheringRapierEvent
{
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