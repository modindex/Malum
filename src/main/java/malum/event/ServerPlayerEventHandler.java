package malum.event;

import malum.capabilities.PlayerMadeDoll;
import malum.capabilities.PlayerProperties;
import malum.items.curios.ItemAirNecklace;
import malum.items.curios.ItemEnderSightNecklace;
import malum.items.gadgets.ItemVoodoDoll;
import malum.network.DangerLevelPacket;
import malum.network.NetworkManager;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import top.theillusivec4.curios.api.CuriosAPI;

import java.util.Random;

@Mod.EventBusSubscriber(Dist.DEDICATED_SERVER)
public class ServerPlayerEventHandler
{
    @SubscribeEvent
    public static void onTracking(PlayerEvent.StartTracking event)
    {
        PlayerEntity toUpdate = event.getPlayer();
        if (toUpdate instanceof ServerPlayerEntity)
        {
            Entity target = event.getTarget();
            if (target instanceof PlayerEntity)
            {
                send(toUpdate);
            }
        }
    }
    public static void send(PlayerEntity player)
    {
        if (!player.world.isRemote())
        {
            PacketDistributor.TRACKING_ENTITY.with(() -> player);
            int value = player.getCapability(PlayerProperties.PLAYER_MADE_DOLL).map(PlayerMadeDoll::hasPlayerMadeDoll).orElse(0);
            NetworkManager.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> player), new DangerLevelPacket(value, player.getUniqueID()));
        }
    }
}