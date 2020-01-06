package malum.recipes;

import malum.registry.ModRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import java.util.Objects;

import static malum.MalumMod.randomize;

/**
 * Transmutation recipe for Runic Shears
 */
public class BlockTransmutationRecipe
{

    private Block block;
    private Block replacementBlock;
    private String name;

    public BlockTransmutationRecipe(Block block, Block replacementBlock)
    {
        this.block = block;
        this.replacementBlock = replacementBlock;
        this.name = Objects.requireNonNull(block.getRegistryName()).toString();
    }
    public Block getBlock()
    {
        return block;
    }

    public Block getReplacementBlock()
    {
        return replacementBlock;
    }

    public String getName()
    {
        return name;
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
