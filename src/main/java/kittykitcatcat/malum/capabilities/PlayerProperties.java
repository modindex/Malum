package kittykitcatcat.malum.capabilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PlayerProperties
{

    @CapabilityInject(CapabilityData.class)
    public static Capability<CapabilityData> CAPABILITY;

    public static float getSoulEaterDamageBoost(PlayerEntity player)
    {
        return player.getCapability(PlayerProperties.CAPABILITY).map(CapabilityData::getSoulEaterDamageBoost).orElse(0f);
    }

    public static boolean getIsTeleporting(PlayerEntity player)
    {
        return player.getCapability(PlayerProperties.CAPABILITY).map(CapabilityData::getTeleporting).orElse(false);
    }

    public static double getTeleportChargeTime(PlayerEntity player)
    {
        return player.getCapability(PlayerProperties.CAPABILITY).map(CapabilityData::getTeleortChargeTime).orElse(0d);
    }

    public static void setIsTeleporting(PlayerEntity playerEntity, boolean isTeleporting)
    {
        playerEntity.getCapability(PlayerProperties.CAPABILITY).ifPresent(note ->
        {
            note.setIsTeleporting(isTeleporting);
        });
    }

    public static void setSoulEaterDamageBoost(PlayerEntity playerEntity, float damageBoost)
    {
        playerEntity.getCapability(PlayerProperties.CAPABILITY).ifPresent(note ->
        {
            note.setSoulEaterDamageBoost(damageBoost);
        });
    }

    public static void setTeleportChargeTime(PlayerEntity playerEntity, double teleportChargeTime)
    {
        playerEntity.getCapability(PlayerProperties.CAPABILITY).ifPresent(note ->
        {
            note.setTeleportChargeTime(teleportChargeTime);
        });
    }

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
