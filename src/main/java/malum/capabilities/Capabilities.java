package malum.capabilities;


import net.minecraft.nbt.CompoundNBT;

public class Capabilities {

    private int dangerLevel = 0;
    private float hate = 0;
    private float permaHate = 0;

    public Capabilities() {
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
