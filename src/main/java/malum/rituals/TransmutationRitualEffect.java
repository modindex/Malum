package malum.rituals;

import malum.ClientRefferences;
import malum.recipes.BlockTransmutationRecipe;
import malum.registry.ModSounds;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class TransmutationRitualEffect implements RitualEffect
{
    @Override
    public void doRitualEffect(int strenght, BlockPos position, TileEntity tileEntity)
    {
        World world = tileEntity.getWorld();
        world.playSound(ClientRefferences.getClientPlayer(), position, ModSounds.transmutate, SoundCategory.BLOCKS, 3.0F, 0.25F);
        for (BlockPos pos : BlockPos.getAllInBoxMutable(position.subtract(new Vec3i(strenght + 3, strenght + 3, strenght + 3)), position.add(new Vec3i(strenght + 3, strenght + 3, strenght + 3))))
        {
            BlockTransmutationRecipe.transmutateBlock(world.getBlockState(pos), world, pos);
        }
    }
}