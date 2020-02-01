package malum.capabilities.crystalSpotCapability;


import net.minecraft.util.math.BlockPos;

public interface ICrystalSpotCapability
{
    BlockPos pos();
    void setBlockPos(BlockPos pos);

    float potency();
    void setPotency(float potency);

}
