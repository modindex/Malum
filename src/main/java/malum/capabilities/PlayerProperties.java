package malum.capabilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PlayerProperties {

    @CapabilityInject(CapabilityData.class)
    public static Capability<CapabilityData> CAPABILITY;

    public static double getAvaiableFlightTime(PlayerEntity player)
    {
        return player.getCapability(PlayerProperties.CAPABILITY).map(CapabilityData::getAvaiableFlightTime).orElse(0d);
    }
    public static boolean getCanFly(PlayerEntity player)
    {
        return player.getCapability(PlayerProperties.CAPABILITY).map(CapabilityData::getCanFly).orElse(false);
    }
    public static double getTotalFlightTime(PlayerEntity player)
    {
        return player.getCapability(PlayerProperties.CAPABILITY).map(CapabilityData::getTotalFlightTime).orElse(0d);
    }
    public static void setCanFly(PlayerEntity playerEntity, boolean canFly)
    {
        playerEntity.getCapability(PlayerProperties.CAPABILITY).ifPresent(note ->
        {
            note.setCanFly(canFly);
        });
    }
    public static void setAvaiableFlightTime(PlayerEntity playerEntity, double avaiableFlightTime)
    {
        playerEntity.getCapability(PlayerProperties.CAPABILITY).ifPresent(note ->
        {
            note.setAvaiableFlightTime(avaiableFlightTime);
        });
    }
    public static void setTotalFlightTime(PlayerEntity playerEntity, double totalFlightTime)
    {
        playerEntity.getCapability(PlayerProperties.CAPABILITY).ifPresent(note ->
        {
            note.setTotalFlightTime(totalFlightTime);
        });
    }
}
