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

public class ServerPlayerEventHandler
{
    public ServerPlayerEventHandler()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onTracking(PlayerEvent.StartTracking event)
    {
        PlayerEntity toUpdate = event.getPlayer();
        if (event.getTarget() instanceof PlayerEntity && event.getPlayer() != null)
        {
            Entity target = event.getTarget();
            send((PlayerEntity) target);
        }
    }
    public static void send(PlayerEntity player)
    {
        int value = PlayerProperties.getDangerLevel(player);
        NetworkManager.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> player), new DangerLevelPacket(value, player.getUniqueID()));
    }
}