package malum.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PlayerProperties {

    @CapabilityInject(PlayerMadeDoll.class)
    public static Capability<PlayerMadeDoll> PLAYER_MADE_DOLL;

}
