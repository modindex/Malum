package malum.network;

import malum.ClientRefferences;
import malum.capabilities.PlayerProperties;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class PermaHatePacket
{
    private float permaHate;
    private UUID uuid;
    public PermaHatePacket(float permaHate, UUID uuid)
    {
        this.permaHate = permaHate;
        this.uuid = uuid;
    }
    public void encode(PacketBuffer buf)
    {
        buf.writeFloat(permaHate);
        buf.writeUniqueId(uuid);
    }
    public void whenThisPacketIsReceived(Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() -> {
            PlayerEntity player = ClientRefferences.getClientPlayerByUUID(uuid);
            player.getCapability(PlayerProperties.CAPABILITY).ifPresent(note ->
            {
                note.setPermaHate(this.permaHate);
            });
        });
        context.get().setPacketHandled(true);
    }
    public static PermaHatePacket decode(PacketBuffer buf)
    {
        float permaHate = buf.readFloat();
        UUID uniqueID = buf.readUniqueId();
        return new PermaHatePacket(permaHate, uniqueID);
    }
}