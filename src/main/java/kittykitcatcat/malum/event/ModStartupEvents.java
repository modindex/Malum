package kittykitcatcat.malum.event;

import kittykitcatcat.malum.recipes.BlockTransmutationRecipes;
import kittykitcatcat.malum.recipes.ResourceFormingRecipes;
import kittykitcatcat.malum.recipes.RitualRecipes;
import kittykitcatcat.malum.recipes.SpiritInfusionRecipes;
import kittykitcatcat.malum.spirit_augmentation.SpiritAugmentationDataBinding;
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