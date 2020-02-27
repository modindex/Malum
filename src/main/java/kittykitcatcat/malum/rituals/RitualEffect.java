package kittykitcatcat.malum.rituals;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public interface RitualEffect
{
    void doRitualEffect(int strenght, BlockPos pos, TileEntity tileEntity);
}
