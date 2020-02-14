package malum.capabilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PlayerProperties {

    @CapabilityInject(CapabilityData.class)
    public static Capability<CapabilityData> CAPABILITY;

    public static double getFlightTime(PlayerEntity player)
    {
        return player.getCapability(PlayerProperties.CAPABILITY).map(CapabilityData::getFlightTime).orElse(0d);
    }
    public static boolean getCanFly(PlayerEntity player)
    {
        return player.getCapability(PlayerProperties.CAPABILITY).map(CapabilityData::getCanFly).orElse(false);
    }
    public static void setCanFly(PlayerEntity playerEntity, boolean canFly)
    {
        playerEntity.getCapability(PlayerProperties.CAPABILITY).ifPresent(note ->
        {
            note.setCanFly(canFly);
        });
    }
    public static void setFlightTime(PlayerEntity playerEntity, double flightTime)
    {
        playerEntity.getCapability(PlayerProperties.CAPABILITY).ifPresent(note ->
        {
            note.setFlightTime(flightTime);
        });
    }
}
