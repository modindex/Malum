package malum.registry;

import malum.capabilities.Capabilities;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityRegistry
{
    private static void registerCapabilities(){
        CapabilityManager.INSTANCE.register(Capabilities.class, new Capability.IStorage<Capabilities>() {
            @Override
            public void readNBT(Capability<Capabilities> capability, Capabilities capabilities, Direction direction, INBT inbt) {
                throw new UnsupportedOperationException();
            }
            @Override
            public INBT writeNBT(Capability<Capabilities> capability, Capabilities instance, Direction side) {
                throw new UnsupportedOperationException();
            }
        }, () -> {

            throw new UnsupportedOperationException();

        });
    }
}