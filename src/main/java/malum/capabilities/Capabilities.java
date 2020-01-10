package malum.capabilities;


import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nonnull;

public class Capabilities {

    double[] curioArray = new double[10];

    public Capabilities() {
    }

    @Nonnull
    public double[] getCurioArray()
    {
        return curioArray;
    }
    public void setCurioArrayArgumenr(int arg, double value)
    {
        curioArray[arg] = value;
    }

    public void copyFrom(Capabilities source) {
        curioArray = source.curioArray;
    }


    public void saveNBTData(CompoundNBT compound) {
    }

    public void loadNBTData(CompoundNBT compound) {
    }
}
