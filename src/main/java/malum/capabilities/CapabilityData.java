package malum.capabilities;

import net.minecraft.nbt.CompoundNBT;
import org.antlr.v4.runtime.misc.NotNull;

public class CapabilityData
{
    double flightTime;
    boolean canFly;


    public CapabilityData()
    {
    }

    @NotNull
    public double getFlightTime()
    {
        return flightTime;
    }
    @NotNull
    public boolean getCanFly()
    {
        return canFly;
    }
    public void setFlightTime(double flightTime)
    {
        this.flightTime = flightTime;
    }
    public void setCanFly(boolean canFly)
    {
        this.canFly = canFly;
    }
    public void copyFrom(CapabilityData source)
    {
        flightTime = source.flightTime;
        canFly = source.canFly;
    }


    public void saveNBTData(CompoundNBT compound)
    {
    }

    public void loadNBTData(CompoundNBT compound)
    {
    }
}
