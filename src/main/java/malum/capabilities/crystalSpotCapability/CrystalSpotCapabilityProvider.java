package malum.capabilities.crystalSpotCapability;


import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class CrystalSpotCapabilityProvider implements ICapabilityProvider
{
    @CapabilityInject(ICrystalSpotCapability.class)
    public static final Capability<ICrystalSpotCapability> VOID_CRYSTAL_DRAIN_CAPABILITY_DATA_CAPABILITY = null;

    private ICrystalSpotCapability instance = VOID_CRYSTAL_DRAIN_CAPABILITY_DATA_CAPABILITY.getDefaultInstance();

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
    {
        if (cap == VOID_CRYSTAL_DRAIN_CAPABILITY_DATA_CAPABILITY)
        {
            return LazyOptional.of(() -> instance).cast();
        }
        else
        {
            return LazyOptional.empty();
        }
    }
}
