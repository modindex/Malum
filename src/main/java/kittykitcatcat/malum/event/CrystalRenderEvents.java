package kittykitcatcat.malum.event;

import kittykitcatcat.malum.registry.ModItems;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CrystalRenderEvents
{
    @SubscribeEvent
    public static void SpiritHarvest(ColorHandlerEvent.Item event)
    {
        event.getItemColors().register(((IItemColor) ModItems.crystal_render)::getColor, ModItems.crystal_render);
    }
}