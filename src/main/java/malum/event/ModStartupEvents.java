package malum.event;

import malum.recipes.BlockTransmutationRecipes;
import malum.recipes.ResourceFormingRecipes;
import malum.recipes.RitualRecipes;
import malum.recipes.SpiritInfusionRecipes;
import malum.spirit_augmentation.SpiritAugmentationDataBinding;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
@Mod.EventBusSubscriber
public class ModStartupEvents
{
    @SubscribeEvent
    public static void registerRecipes(FMLServerStartedEvent event)
    {
        BlockTransmutationRecipes.initRecipes();
        RitualRecipes.initRecipes();
        ResourceFormingRecipes.initRecipes();
        SpiritInfusionRecipes.initRecipes();
        SpiritAugmentationDataBinding.bindItemsToAugmenters();
    }
}