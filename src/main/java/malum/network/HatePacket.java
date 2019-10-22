package malum.network;

import malum.ClientRefferences;
import malum.capabilities.PlayerProperties;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class HatePacket
{
    private float hate;
    private UUID uuid;
    public HatePacket(float hate, UUID uuid)
    {
        this.hate = hate;
        this.uuid = uuid;
    }
    public void encode(PacketBuffer buf)
    {
        buf.writeFloat(hate);
        buf.writeUniqueId(uuid);
    }
    public void whenThisPacketIsReceived(Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() -> {
            PlayerEntity player = ClientRefferences.getClientPlayerByUUID(uuid);
            player.getCapability(PlayerProperties.CAPABILITY).ifPresent(note ->
            {
                note.setHate(this.hate);
            });
        });
        context.get().setPacketHandled(true);
    }
    public static HatePacket decode(PacketBuffer buf)
    {
        int dangerLevel = buf.readInt();
        UUID uniqueID = buf.readUniqueId();
        return new HatePacket(dangerLevel, uniqueID);
    }
}