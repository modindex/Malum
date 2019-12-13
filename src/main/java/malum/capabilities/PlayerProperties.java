package malum.capabilities;

import malum.MalumMod;
import malum.event.ServerPlayerEventHandler;
import malum.network.DangerLevelPacket;
import malum.network.HatePacket;
import malum.network.NetworkManager;
import malum.network.PermaHatePacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.network.PacketDistributor;

public class PlayerProperties {

    @CapabilityInject(Capabilities.class)
    public static Capability<Capabilities> CAPABILITY;

    public static int getDangerLevel(PlayerEntity player) {
        return player.getCapability(PlayerProperties.CAPABILITY).map(Capabilities::getDangerLevel).orElse(0);
    }

    public static float getHate(PlayerEntity player) {
        return player.getCapability(PlayerProperties.CAPABILITY).map(Capabilities::getHate).orElse(0f);
    }

    public static double[] getCurioArray(PlayerEntity player)
    {
        double[] empty = new double[0];
        return player.getCapability(PlayerProperties.CAPABILITY).map(Capabilities::getCurioArray).orElse(empty);
    }

    public static float getPermaHate(PlayerEntity player) {
        return player.getCapability(PlayerProperties.CAPABILITY).map(Capabilities::getPermaHate).orElse(0f);
    }
    public static void setDangerLevel(PlayerEntity player, int amount) {
        player.getCapability(PlayerProperties.CAPABILITY).ifPresent(note ->
        {
            note.setDangerLevel(amount);
        });
        if (!player.world.isRemote()) {
            sendDangerLevelPacket(player);
        }
    }
    public static void setCurioArrayArgument(PlayerEntity player, int i, double d)
    {
        player.getCapability(PlayerProperties.CAPABILITY).ifPresent(note ->
        {
            note.setCurioArrayArgumenr(i, d);
        });
    }
    public static void setDangerLevelCapped(PlayerEntity player, int amount, int cap) {
        player.getCapability(PlayerProperties.CAPABILITY).ifPresent(note ->
        {
            if (note.getDangerLevel() <= cap) {
                note.setDangerLevel(amount);
            }
        });
        if (!player.world.isRemote()) {
            sendDangerLevelPacket(player);
        }
    }

    public static void setHate(PlayerEntity player, float amount) {
        player.getCapability(PlayerProperties.CAPABILITY).ifPresent(note ->
        {
            if ((note.getHate() + amount) >= 100)
            {
                note.setHate(100);
                float value = note.getHate() + amount - 100;
                note.setPermaHate(value);
                if (!player.world.isRemote()) {
                    sendPermaHatePacket(player);
                }
            }
            note.setHate(amount);
            MalumMod.LOGGER.info(note.getHate());
        });
        if (!player.world.isRemote()) {
            sendHatePacket(player);
        }
    }

    public static void sendHatePacket(PlayerEntity player)
    {
        float value = PlayerProperties.getHate(player);
        NetworkManager.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> player), new HatePacket(value, player.getUniqueID()));
    }
    public static void sendPermaHatePacket(PlayerEntity player)
    {
        float value = PlayerProperties.getPermaHate(player);
        NetworkManager.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> player), new PermaHatePacket(value, player.getUniqueID()));
    }
    public static void sendDangerLevelPacket(PlayerEntity player) {
        int value = PlayerProperties.getDangerLevel(player);

        NetworkManager.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> player), new DangerLevelPacket(value, player.getUniqueID()));
    }
}
