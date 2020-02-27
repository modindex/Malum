package kittykitcatcat.malum.capabilities;

import net.minecraft.nbt.CompoundNBT;
import org.antlr.v4.runtime.misc.NotNull;

public class CapabilityData
{
    //phantom necklace
    double avaiableFlightTime;
    boolean canFly;
    double totalFlightTime;

    //ender staff
    boolean isTeleporting;
    double teleportChargeTime;

    float soulEaterDamageBoost;

    public CapabilityData()
    {
    }


    @NotNull
    public double getTeleortChargeTime()
    {
        return teleportChargeTime;
    }

    @NotNull
    public float getSoulEaterDamageBoost()
    {
        return soulEaterDamageBoost;
    }

    public void setSoulEaterDamageBoost(float soulEaterDamageBoost)
    {
        this.soulEaterDamageBoost = soulEaterDamageBoost;
    }

    @NotNull
    public boolean getTeleporting()
    {
        return isTeleporting;
    }

    public void setIsTeleporting(boolean canTeleport)
    {
        this.isTeleporting = canTeleport;
    }

    public void setTeleportChargeTime(double teleportChargeTime)
    {
        this.teleportChargeTime = teleportChargeTime;
    }

    @NotNull
    public double getAvaiableFlightTime()
    {
        return avaiableFlightTime;
    }

    public void setAvaiableFlightTime(double avaiableFlightTime)
    {
        this.avaiableFlightTime = avaiableFlightTime;
    }

    @NotNull
    public double getTotalFlightTime()
    {
        return totalFlightTime;
    }

    public void setTotalFlightTime(double totalFlightTime)
    {
        this.totalFlightTime = totalFlightTime;
    }

    @NotNull
    public boolean getCanFly()
    {
        return canFly;
    }

    public void setCanFly(boolean canFly)
    {
        this.canFly = canFly;
    }

    public void flightDuration(double flightDuration)
    {
        this.totalFlightTime = flightDuration;
    }

    public void copyFrom(CapabilityData source)
    {
        avaiableFlightTime = source.avaiableFlightTime;
        canFly = source.canFly;
        totalFlightTime = source.totalFlightTime;
        teleportChargeTime = source.teleportChargeTime;
        isTeleporting = source.isTeleporting;
        soulEaterDamageBoost = source.soulEaterDamageBoost;
    }


    public void saveNBTData(CompoundNBT compound)
    {
    }

    public void loadNBTData(CompoundNBT compound)
    {
    }
}
