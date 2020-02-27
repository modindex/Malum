package kittykitcatcat.malum.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ServerPlayerEventHandler
{
    public ServerPlayerEventHandler()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void sendPackets(PlayerEvent.StartTracking event)
    {
        if (event.getTarget() instanceof PlayerEntity && event.getPlayer() != null)
        {
            Entity target = event.getTarget();
            //sendDangerLevelPacket((PlayerEntity) target);
        }
    }
}