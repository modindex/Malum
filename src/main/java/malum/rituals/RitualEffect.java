package malum.rituals;

import net.minecraft.tileentity.TileEntity;

import java.util.UUID;

public interface RitualEffect
{
    void doRitualEffect(int strenght, UUID uuid, int[] position, TileEntity tileEntity);
}
