package malum.capabilities.crystalSpotCapability;


import java.util.concurrent.Callable;

public class CrystalSpotCapabilityFactory implements Callable<ICrystalSpotCapability>
{
    @Override
    public ICrystalSpotCapability call() throws Exception
    {
        return new CrystalSpotCapability();
    }
}
