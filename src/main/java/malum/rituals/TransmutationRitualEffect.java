package malum.rituals;

import malum.ClientRefferences;
import malum.recipes.BlockTransmutationRecipe;
import malum.registry.ModRecipes;
import malum.registry.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TransmutationRitualEffect implements RitualEffect
{
    @Override
    public void doRitualEffect(int strenght, BlockPos position, TileEntity tileEntity)
    {
        World world = tileEntity.getWorld();
        world.playSound(ClientRefferences.getClientPlayer(), position, ModSounds.transmutate, SoundCategory.BLOCKS, 3.0F, 0.25F);
        List<Block> blockList = new ArrayList<>();
        for (BlockPos pos : BlockPos.getAllInBoxMutable(position.subtract(new Vec3i(strenght, strenght, strenght)), position.add(new Vec3i(strenght, strenght, strenght))))
        {
            if (!blockList.contains(world.getBlockState(pos).getBlock()))
            {
                BlockTransmutationRecipe recipe = ModRecipes.getBlockTransmutationRecipe(world.getBlockState(pos));
                if (recipe != null)
                {
                    blockList.add(world.getBlockState(pos).getBlock());
                }
            }
            BlockTransmutationRecipe.transmutateBlock(world.getBlockState(pos), world, pos);
        }
        for (Block block : blockList)
        {
            world.playSound(null, position, block.getDefaultState().getSoundType().getBreakSound(), SoundCategory.BLOCKS, 5F, 0.4F);
        }
    }
}