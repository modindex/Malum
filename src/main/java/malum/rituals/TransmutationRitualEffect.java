package malum.rituals;

import malum.ClientRefferences;
import malum.recipes.BlockTransmutationRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import static malum.MalumMod.randomize;

public class TransmutationRitualEffect implements RitualEffect
{
    @Override
    public void doRitualEffect(int strenght, BlockPos position, TileEntity tileEntity)
    {
        World world = tileEntity.getWorld();
        world.playSound(ClientRefferences.getClientPlayer(), position, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0F, (float)randomize(2.5F, 0.8F));
        for(BlockPos pos : BlockPos.getAllInBoxMutable(position.subtract(new Vec3i(strenght + 3,strenght + 3,strenght + 3)), position.add(new Vec3i(strenght + 3,strenght + 3,strenght + 3))))
        {
            BlockTransmutationRecipe.transmutateBlock(world.getBlockState(pos), world, pos);
        }
    }
}
