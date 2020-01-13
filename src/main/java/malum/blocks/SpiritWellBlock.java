package malum.blocks;

import malum.MalumMod;
import malum.tileentities.SpiritWellTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.minecraft.block.ChestBlock.WATERLOGGED;

public class SpiritWellBlock extends Block
{
    public SpiritWellBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(final BlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world)
    {
        return new SpiritWellTileEntity();
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        TileEntity entity = worldIn.getTileEntity(pos);
        if (!worldIn.isRemote())
        {
            if (entity instanceof SpiritWellTileEntity)
            {
                if (handIn != Hand.OFF_HAND)
                {
                    ItemStack stack = player.inventory.getCurrentItem();
                    CompoundNBT nbt = stack.getOrCreateTag();
                    if (nbt.contains("spirit"))
                    {
                        if (((SpiritWellTileEntity) entity).spirits.size() < 5)
                        {
                            player.swingArm(handIn);
                            if (stack.getCount() == 1)
                            {
                                if (nbt.contains("spirit"))
                                {
                                    ((SpiritWellTileEntity) entity).spirits.add(nbt.getString("spirit"));
                                    nbt.remove("spirit");
                                }
                            }
                            else
                            {
                                if (nbt.contains("spirit"))
                                {
                                    stack.shrink(1);
                                    ItemStack newStack = stack.getItem().getDefaultInstance();
                                    player.inventory.addItemStackToInventory(newStack);
                                    ((SpiritWellTileEntity) entity).spirits.add(nbt.getString("spirit"));
                                }
                            }
                            MalumMod.LOGGER.info(((SpiritWellTileEntity) entity).spirits);
                            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                        }
                    }
                }
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_)
    {
        p_206840_1_.add(WATERLOGGED);
    }

    @Nonnull
    public IFluidState getFluidState(BlockState p_204507_1_)
    {
        return p_204507_1_.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(p_204507_1_);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_)
    {
        IWorld lvt_2_1_ = p_196258_1_.getWorld();
        BlockPos lvt_3_1_ = p_196258_1_.getPos();
        boolean lvt_4_1_ = lvt_2_1_.getFluidState(lvt_3_1_).getFluid() == Fluids.WATER;
        return this.getDefaultState().with(WATERLOGGED, lvt_4_1_);
    }
}