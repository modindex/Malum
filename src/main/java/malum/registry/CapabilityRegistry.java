package malum.registry;

import malum.capabilities.PlayerMadeDoll;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityRegistry
{
    private static void registerCapabilities(){
        CapabilityManager.INSTANCE.register(PlayerMadeDoll.class, new Capability.IStorage<PlayerMadeDoll>() {
            @Override
            public void readNBT(Capability<PlayerMadeDoll> capability, PlayerMadeDoll playerMadeDoll, Direction direction, INBT inbt) {
                throw new UnsupportedOperationException();
            }
            @Override
            public INBT writeNBT(Capability<PlayerMadeDoll> capability, PlayerMadeDoll instance, Direction side) {
                throw new UnsupportedOperationException();
            }
        }, () -> {

            throw new UnsupportedOperationException();

        });
    }
}