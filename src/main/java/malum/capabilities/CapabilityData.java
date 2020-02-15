package malum.capabilities;

import net.minecraft.nbt.CompoundNBT;
import org.antlr.v4.runtime.misc.NotNull;

public class CapabilityData
{
    double avaiableFlightTime;
    boolean canFly;
    double totalFlightTime;


    public CapabilityData()
    {
    }

    @NotNull
    public double getAvaiableFlightTime()
    {
        return avaiableFlightTime;
    }
    @NotNull
    public double getTotalFlightTime()
    {
        return totalFlightTime;
    }
    @NotNull
    public boolean getCanFly()
    {
        return canFly;
    }
    public void setAvaiableFlightTime(double avaiableFlightTime)
    {
        this.avaiableFlightTime = avaiableFlightTime;
    }
    public void setTotalFlightTime(double totalFlightTime)
    {
        this.totalFlightTime = totalFlightTime;
    }
    public void flightDuration(double flightDuration)
    {
        this.totalFlightTime = flightDuration;
    }
    public void setCanFly(boolean canFly)
    {
        this.canFly = canFly;
    }
    public void copyFrom(CapabilityData source)
    {
        avaiableFlightTime = source.avaiableFlightTime;
        canFly = source.canFly;
        totalFlightTime = source.totalFlightTime;
    }


    public void saveNBTData(CompoundNBT compound)
    {
    }

    public void loadNBTData(CompoundNBT compound)
    {
    }
}
