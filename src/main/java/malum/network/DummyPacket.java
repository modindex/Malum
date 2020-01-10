package malum.network;

public class DummyPacket
{
    /*private int dangerLevel;
    private UUID uuid;
    public DummyPacket(int dangerLevel, UUID uuid)
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
    public static DummyPacket decode(PacketBuffer buf)
    {
        int dangerLevel = buf.readInt();
        UUID uniqueID = buf.readUniqueId();
        return new DummyPacket(dangerLevel, uniqueID);
    }*/
}