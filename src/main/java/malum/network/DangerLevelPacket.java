package malum.network;

import malum.ClientRefferences;
import malum.capabilities.PlayerProperties;
import net.minecraft.entity.player.PlayerEntity;
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
        buf.writeInt(dangerLevel);
        buf.writeUniqueId(uuid);
    }
    public void whenThisPacketIsReceived(Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() -> {
            PlayerEntity player = ClientRefferences.getClientPlayerByUUID(uuid);
            player.getCapability(PlayerProperties.CAPABILITY).ifPresent(note ->
            {
                note.setDangerLevel(this.dangerLevel);
            });
        });
        context.get().setPacketHandled(true);
    }
    public static DangerLevelPacket decode(PacketBuffer buf)
    {
        int dangerLevel = buf.readInt();
        UUID uniqueID = buf.readUniqueId();
        return new DangerLevelPacket(dangerLevel, uniqueID);
    }
}