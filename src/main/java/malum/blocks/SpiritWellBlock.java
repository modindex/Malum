package malum.blocks;

import malum.MalumMod;
import malum.items.gadgets.ItemSpiritContainer;
import malum.recipes.SpiritInfusionRecipe;
import malum.registry.ModRecipes;
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
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
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

    private static final VoxelShape PART_BOTTOM = Block.makeCuboidShape(1D, 0D, 1D, 15D, 4D, 15D);

    private static final VoxelShape PART_BOTTOM_1 = Block.makeCuboidShape(15D, 0D, 4D, 16D, 2D, 12D);
    private static final VoxelShape PART_BOTTOM_2 = Block.makeCuboidShape(0D, 0D, 4D, 1D, 2D, 12D);
    private static final VoxelShape PART_BOTTOM_3 = Block.makeCuboidShape(4D, 0D, 15D, 12D, 2D, 16D);
    private static final VoxelShape PART_BOTTOM_4 = Block.makeCuboidShape(4D, 0D, 0D, 12D, 2D, 1D);

    private static final VoxelShape PART_MIDDLE = Block.makeCuboidShape(6D, 4D, 6D, 10D, 8D, 10D);
    private static final VoxelShape PART_MIDDLE_1 = Block.makeCuboidShape(10D, 4D, 10D, 13D, 8D, 13D);
    private static final VoxelShape PART_MIDDLE_2 = Block.makeCuboidShape(3D, 4D, 10D, 6D, 8D, 13D);
    private static final VoxelShape PART_MIDDLE_3 = Block.makeCuboidShape(3D, 4D, 3D, 6D, 8D, 6D);
    private static final VoxelShape PART_MIDDLE_4 = Block.makeCuboidShape(10D, 4D, 3D, 13D, 8D, 6D);

    private static final VoxelShape PART_TOP = Block.makeCuboidShape(2D, 8D, 2D, 14D, 10D, 14D);
    private static final VoxelShape FINAL_SHAPE = VoxelShapes.or(PART_BOTTOM, PART_BOTTOM_1, PART_BOTTOM_2, PART_BOTTOM_3, PART_BOTTOM_4, PART_MIDDLE, PART_MIDDLE_1, PART_MIDDLE_2, PART_MIDDLE_3, PART_MIDDLE_4, PART_TOP);

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return FINAL_SHAPE;
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
                    if (stack.getItem() instanceof ItemSpiritContainer)
                    {
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
                                        return true;
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
                                        return true;
                                    }
                                }
                                return true;
                            }
                        }
                    }
                    else
                    {
                        SpiritInfusionRecipe recipe = ModRecipes.getSpiritInfusionRecipe(stack.getItem(), ((SpiritWellTileEntity) entity).spirits);
                        if (recipe != null)
                        {
                            stack.shrink(1);
                            player.addItemStackToInventory(recipe.getOutput_item().getDefaultInstance());
                            ((SpiritWellTileEntity) entity).spirits.clear();
                        }
                    }
                }
                MalumMod.LOGGER.info(((SpiritWellTileEntity) entity).spirits);
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