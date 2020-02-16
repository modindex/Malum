package malum.recipes;

import malum.registry.ModRecipes;
import malum.registry.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IProperty;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static malum.MalumMod.randomize;

/**
 * Transmutation recipe for Runic Shears
 */
public class BlockTransmutationRecipe
{

    private Block block;
    private Block replacementBlock;

    public BlockTransmutationRecipe(Block block, Block replacementBlock)
    {
        this.block = block;
        this.replacementBlock = replacementBlock;
    }
    public Block getBlock()
    {
        return block;
    }

    public Block getReplacementBlock()
    {
        return replacementBlock;
    }

    public static void transmutateBlock(BlockState state, World world, BlockPos pos)
    {
        BlockTransmutationRecipe recipe = ModRecipes.getBlockTransmutationRecipe(state);
        if (recipe != null)
        {
            Block replacementBlock = recipe.getReplacementBlock();
            BlockState replacementState = replacementBlock.getDefaultState();
            setBlockStateWithExistingProperties(world, pos, replacementState);
        }
    }
    public static void makeTransmutationVisuals(BlockState state, World world, BlockPos pos)
    {
        BlockTransmutationRecipe recipe = ModRecipes.getBlockTransmutationRecipe(state);
        if (recipe != null)
        {
            Block replacementBlock = recipe.getReplacementBlock();
            BlockState replacementState = replacementBlock.getDefaultState();
            setBlockStateWithExistingProperties(world, pos, replacementState);
            world.playSound(null, pos, ModSounds.transmutate, SoundCategory.PLAYERS, 1F, (float) (0.2F + randomize(1, 1)));
            world.playSound(null, pos, state.getSoundType().getBreakSound(), SoundCategory.PLAYERS, 1F, 0.8F);
            for (int a = 0; a <= 10; a++)
            {
                double posX = randomize(pos.getX() + 0.5, 0.6);
                double posY = randomize(pos.getY() + 0.5, 0.6);
                double posZ = randomize(pos.getZ() + 0.5, 0.6);
                double velX = randomize(0, 0.1);
                double velY = randomize(0, 0.1);
                double velZ = randomize(0, 0.1);
                world.addParticle(ParticleTypes.LARGE_SMOKE, posX, posY, posZ, velX, velY, velZ);
            }
        }
    }
    public static <T extends Comparable<T>> BlockState newStateWithOldProperty(BlockState oldState, BlockState newState, IProperty<T> property)
    {
        return newState.with(property, oldState.get(property));
    }
    public static void setBlockStateWithExistingProperties(World world, BlockPos pos, BlockState newState)
    {
        BlockState oldState = world.getBlockState(pos);

        BlockState finalState = newState;
        for(IProperty<?> property : oldState.getProperties())
        {
            if (newState.has(property))
            {
                finalState = newStateWithOldProperty(oldState, finalState, property);
            }
        }

        world.setBlockState(pos, finalState);
    }
}
