package kittykitcatcat.malum.event;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber
public class AddFeaturesEvent
{
    @SubscribeEvent
    public static void AddFeature(FMLCommonSetupEvent event)
    {
        for (Biome biome : ForgeRegistries.BIOMES)
        {
            //            biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModFeatures.HEALING_FLOWER_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(10)));
        }
    }
}
