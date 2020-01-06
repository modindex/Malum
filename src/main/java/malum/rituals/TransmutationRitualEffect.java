package malum.rituals;

import malum.ClientRefferences;
import malum.recipes.BlockTransmutationRecipe;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.UUID;

import static malum.MalumMod.randomize;

public class TransmutationRitualEffect implements RitualEffect
{
    @Override
    public void doRitualEffect(int strenght, UUID uuid, int[] position, TileEntity tileEntity)
    {
        World world = tileEntity.getWorld();
        int X = position[0];
        int Y = position[1];
        int Z = position[2];
        BlockPos soundPos = new BlockPos(X,Y,Z);
        BlockPos pos1 = new BlockPos(X - strenght,Y - strenght,Z - strenght);
        BlockPos pos2 = new BlockPos(X + strenght,Y + strenght,Z + strenght);
        world.playSound(ClientRefferences.getClientPlayer(), soundPos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0F, (float)randomize(2.5F, 0.8F));
        for(BlockPos pos : BlockPos.getAllInBoxMutable(pos1, pos2))
        {
            BlockTransmutationRecipe.transmutateBlock(world.getBlockState(pos), world, pos);
        }
    }
}
