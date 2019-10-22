package malum.capabilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PlayerProperties {

    @CapabilityInject(Capabilities.class)
    public static Capability<Capabilities> CAPABILITY;
    public static int getDangerLevel(PlayerEntity player)
    {
        return player.getCapability(PlayerProperties.CAPABILITY).map(Capabilities::getDangerLevel).orElse(0);
    }
    public static float getHate(PlayerEntity player)
    {
        return player.getCapability(PlayerProperties.CAPABILITY).map(Capabilities::getHate).orElse(0f);
    }
}
