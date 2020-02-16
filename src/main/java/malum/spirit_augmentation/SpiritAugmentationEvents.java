package malum.spirit_augmentation;

import malum.registry.ModRecipes;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SpiritAugmentationEvents
{
    @SubscribeEvent
    public static void updateFOV(ItemTooltipEvent event)
    {
        if (event.getEntityPlayer() != null)
        {
            if (event.getEntityPlayer().world.isRemote())
            {
                for (SpiritAugmentationData augmentationData : ModRecipes.spiritAugmentationData)
                {
                    if (augmentationData != null)
                    {
                        if (augmentationData.getItem() == event.getItemStack().getItem())
                        {
                            if (event.getItemStack().getTag() != null)
                            {
                                if (event.getItemStack().getTag().contains(augmentationData.spirit.augmentTag()))
                                {
                                    augmentationData.spirit.handleTooltip(event.getItemStack().getTag(), event.getToolTip());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}