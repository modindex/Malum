package kittykitcatcat.malum.capabilities;

import kittykitcatcat.malum.MalumMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CapabilityEvents
{
    @SubscribeEvent
    public static void onEntityConstructing(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof PlayerEntity)
        {
            if (!event.getObject().getCapability(PlayerProperties.CAPABILITY).isPresent())
            {
                event.addCapability(new ResourceLocation(MalumMod.MODID, "properties"), new PropertiesDispatcher());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event)
    {
        if (event.isWasDeath())
        {
            // We need to copyFrom the capabilities
            LazyOptional<CapabilityData> capability = event.getOriginal().getCapability(PlayerProperties.CAPABILITY);
            capability.ifPresent(oldStore ->
            {
                event.getOriginal().getCapability(PlayerProperties.CAPABILITY).ifPresent(newStore ->
                {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }
}