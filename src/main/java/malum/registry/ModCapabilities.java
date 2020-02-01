package malum.registry;

import malum.capabilities.crystalSpotCapability.ICrystalSpotCapability;
import malum.capabilities.crystalSpotCapability.CrystalSpotCapabilityFactory;
import malum.capabilities.crystalSpotCapability.CrystalSpotCapabilityStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModCapabilities
{
    @SubscribeEvent
    public static void registerBlocks(FMLCommonSetupEvent event)
    {
        CapabilityManager.INSTANCE.register(ICrystalSpotCapability.class, new CrystalSpotCapabilityStorage(), new CrystalSpotCapabilityFactory());
    }
}