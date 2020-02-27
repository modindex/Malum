package kittykitcatcat.malum.registry;

import kittykitcatcat.malum.MalumMod;
import kittykitcatcat.malum.worldgen.CrystalSpotConfig;
import kittykitcatcat.malum.worldgen.CrystalSpotFeature;
import kittykitcatcat.malum.worldgen.HealingFlowerFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = MalumMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures
{
    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, MalumMod.MODID);
    public static RegistryObject<Feature<CrystalSpotConfig>> CRYSTAL_FEATURE;
    public static RegistryObject<Feature<NoFeatureConfig>> HEALING_FLOWER_FEATURE;

    @SubscribeEvent
    public static void AddFeature(FMLCommonSetupEvent event)
    {
        CRYSTAL_FEATURE = FEATURES.register("crystal_feature", () -> new CrystalSpotFeature(CrystalSpotConfig::deserialize));
        HEALING_FLOWER_FEATURE = FEATURES.register("healing_flower_feature", () -> new HealingFlowerFeature(NoFeatureConfig::deserialize));
    }
}