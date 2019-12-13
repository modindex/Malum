package malum.capabilities;


import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nonnull;
import java.lang.reflect.Array;

public class Capabilities {

    private int dangerLevel = 0;
    private float hate = 0;
    private float permaHate = 0;
    double[] curioArray = new double[10];

    public Capabilities() {
    }

    @Nonnull
    public double[] getCurioArray()
    {
        return curioArray;
    }
    public double getCurioArrayArgument(int arg)
    {
        return curioArray[arg];
    }
    public void setCurioArray(double[] newCurioArray)
    {
        curioArray = newCurioArray;
    }
    public void setCurioArrayArgumenr(int arg, double value)
    {
        curioArray[arg] = value;
    }
    public int getDangerLevel() {
        return dangerLevel;
    }

    public float getHate() {
        return hate;
    }

    public float getPermaHate() {
        return permaHate;
    }
    public void setDangerLevel(int dangerLevel)
    {
        this.dangerLevel = dangerLevel;
    }

    public void setHate(float hate)
    {
        this.hate = hate;
    }

    public void setPermaHate(float permaHate)
    {
        this.permaHate = permaHate;
    }
    public void copyFrom(Capabilities source) {
        dangerLevel = source.dangerLevel;
        hate = source.hate;
        permaHate = source.permaHate;
        curioArray = source.curioArray;
    }


    public void saveNBTData(CompoundNBT compound) {
        compound.putInt("dangerLevel", dangerLevel);
        compound.putFloat("hate", hate);
        compound.putFloat("permaHate", permaHate);
    }

    public void loadNBTData(CompoundNBT compound) {
        dangerLevel = compound.getInt("dangerLevel");
        hate = compound.getFloat("hate");
        permaHate = compound.getFloat("permaHate");
    }
}
