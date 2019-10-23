package malum.event;

import malum.capabilities.PlayerProperties;
import malum.network.DangerLevelPacket;
import malum.network.NetworkManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import static malum.capabilities.PlayerProperties.*;

public class ServerPlayerEventHandler
{
    public ServerPlayerEventHandler()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onTracking(PlayerEvent.StartTracking event)
    {
        if (event.getTarget() instanceof PlayerEntity && event.getPlayer() != null)
        {
            Entity target = event.getTarget();
            sendDangerLevelPacket((PlayerEntity) target);
            sendHatePacket((PlayerEntity) target);
            sendPermaHatePacket((PlayerEntity) target);
        }
    }
}