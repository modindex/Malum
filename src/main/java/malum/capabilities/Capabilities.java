package malum.capabilities;


import net.minecraft.nbt.CompoundNBT;

public class Capabilities {

    private int dangerLevel = 0;
    private float hate = 0;

    public Capabilities() {
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public float getHate() {
        return hate;
    }

    public void setDangerLevel(int dangerLevel)
    {
        this.dangerLevel = dangerLevel;
    }

    public void setHate(float hate)
    {
        this.hate = hate;
    }
    public void copyFrom(Capabilities source) {
        dangerLevel = source.dangerLevel;
        hate = source.hate;
    }


    public void saveNBTData(CompoundNBT compound) {
        compound.putInt("dangerLevel", dangerLevel);
        compound.putFloat("hate", hate);
    }

    public void loadNBTData(CompoundNBT compound) {
        dangerLevel = compound.getInt("dangerLevel");
        hate = compound.getFloat("hate");
    }
}
