package malum.capabilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PlayerProperties {

    @CapabilityInject(Capabilities.class)
    public static Capability<Capabilities> CAPABILITY;

    public static double[] getCurioArray(PlayerEntity player)
    {
        double[] empty = new double[0];
        return player.getCapability(PlayerProperties.CAPABILITY).map(Capabilities::getCurioArray).orElse(empty);
    }
    public static void setCurioArrayArgument(PlayerEntity player, int i, double d)
    {
        player.getCapability(PlayerProperties.CAPABILITY).ifPresent(note ->
        {
            note.setCurioArrayArgumenr(i, d);
        });
    }
}
