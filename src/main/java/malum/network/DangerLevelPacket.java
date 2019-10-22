package malum.network;

import malum.ClientRefferences;
import malum.MalumMod;
import malum.capabilities.PlayerProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class DangerLevelPacket
{
    private int dangerLevel;
    private UUID uuid;
    public DangerLevelPacket(int dangerLevel, UUID uuid)
    {
        this.dangerLevel = dangerLevel;
        this.uuid = uuid;
    }
    public void encode(PacketBuffer buf)
    {
        MalumMod.LOGGER.info(dangerLevel + uuid.toString());
        buf.writeInt(dangerLevel);
        buf.writeUniqueId(uuid);
    }
    public static DangerLevelPacket decode(PacketBuffer buf)
    {
        return new DangerLevelPacket(buf.readInt(), buf.readUniqueId());
    }
    public void whenThisPacketIsReceived(Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() -> {
            ServerPlayerEntity player = (ServerPlayerEntity) ClientRefferences.getClientPlayerByUUID(uuid);
            player.getCapability(PlayerProperties.PLAYER_MADE_DOLL).ifPresent(note ->
            {
                note.setPlayerMadeDoll(this.dangerLevel);
            });
        });
        context.get().setPacketHandled(true);
    }
}