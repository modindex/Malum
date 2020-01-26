package malum.rituals;

import malum.blocks.ResourceCrystalBlock;
import malum.recipes.BlockTransmutationRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class CrystalGrowthRitualEffect implements RitualEffect
{
    @Override
    public void doRitualEffect(int strenght, BlockPos position, TileEntity tileEntity)
    {
        World world = tileEntity.getWorld();
        assert world != null;
        for (BlockPos pos : BlockPos.getAllInBoxMutable(position.subtract(new Vec3i(strenght, strenght, strenght)), position.add(new Vec3i(strenght, strenght, strenght))))
        {
            if (world.getBlockState(pos).getBlock() instanceof ResourceCrystalBlock)
            {
                world.setBlockState(pos, world.getBlockState(pos).with(ResourceCrystalBlock.TIER, ResourceCrystalBlock.TIER.getAllowedValues().size() - 1));
                BlockTransmutationRecipe.transmutateBlock(world.getBlockState(pos), world, pos);
            }
        }
    }
}