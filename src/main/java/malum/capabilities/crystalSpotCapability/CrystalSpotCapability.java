package malum.capabilities.crystalSpotCapability;


import net.minecraft.util.math.BlockPos;

public class CrystalSpotCapability implements ICrystalSpotCapability
{
    private float potency;
    private BlockPos pos;

    @Override
    public BlockPos pos()
    {
        return this.pos;
    }

    @Override
    public void setBlockPos(BlockPos pos)
    {
        this.pos = pos;
    }

    @Override
    public float potency()
    {
        return this.potency;
    }

    @Override
    public void setPotency(float potency)
    {
        this.potency = potency;
    }

}
