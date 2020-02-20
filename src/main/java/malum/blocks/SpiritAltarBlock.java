package malum.blocks;

import malum.MalumMod;
import malum.items.gadgets.ItemSpiritContainer;
import malum.recipes.SpiritInfusionRecipe;
import malum.registry.ModRecipes;
import malum.tileentities.SpiritAltarTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
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

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;
import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;
public class SpiritAltarBlock extends Block
{
    public SpiritAltarBlock(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(SPIRITS, 0));

    }
    public static final IntegerProperty SPIRITS = IntegerProperty.create("spirit", 0, 8);
    private static final VoxelShape SHAPE_1 = Block.makeCuboidShape(3D, 0D, 3D, 13D, 4D, 13D);
    private static final VoxelShape SHAPE_2 = Block.makeCuboidShape(5D, 4D, 5D, 11D, 8D, 11D);
    private static final VoxelShape SHAPE_3 = Block.makeCuboidShape(11D, 4D, 7D, 12D, 6D, 9D);
    private static final VoxelShape SHAPE_4 = Block.makeCuboidShape(4D, 4D, 7D, 5D, 6D, 9D);
    private static final VoxelShape SHAPE_5 = Block.makeCuboidShape(7D, 4D, 11D, 9D, 6D, 12D);
    private static final VoxelShape SHAPE_6 = Block.makeCuboidShape(7D, 4D, 4D, 9D, 6D, 5D);
    private static final VoxelShape SHAPE_7 = Block.makeCuboidShape(4D, 8D, 4D, 12D, 9D, 12D);
    private static final VoxelShape SHAPE_8 = Block.makeCuboidShape(2D, 9D, 2D, 14D, 11D, 14D);
    private static final VoxelShape SHAPE_9 = Block.makeCuboidShape(2D, 9D, 2D, 14D, 11D, 14D);
    private static final VoxelShape SHAPE_10 = Block.makeCuboidShape(1D, 0D, 1D, 5D, 1D, 5D);
    private static final VoxelShape SHAPE_11 = Block.makeCuboidShape(1D, 0D, 6D, 5D, 2D, 10D);
    private static final VoxelShape SHAPE_12 = Block.makeCuboidShape(1D, 0D, 11D, 5D, 1D, 15D);
    private static final VoxelShape SHAPE_13 = Block.makeCuboidShape(6D, 0D, 11D, 10D, 2D, 15D);
    private static final VoxelShape SHAPE_14 = Block.makeCuboidShape(6D, 0D, 11D, 10D, 2D, 15D);
    private static final VoxelShape SHAPE_15 = Block.makeCuboidShape(11D, 0D, 11D, 15D, 1D, 15D);
    private static final VoxelShape SHAPE_16 = Block.makeCuboidShape(11D, 0D, 6D, 15D, 2D, 10D);
    private static final VoxelShape SHAPE_17 = Block.makeCuboidShape(11D, 0D, 6D, 15D, 2D, 10D);
    private static final VoxelShape SHAPE_18 = Block.makeCuboidShape(11D, 0D, 1D, 15D, 1D, 5D);
    private static final VoxelShape SHAPE_19 = Block.makeCuboidShape(6D, 0D, 1D, 10D, 2D, 5D);
    private static final VoxelShape FINAL_SHAPE = VoxelShapes.or(SHAPE_1,SHAPE_2,SHAPE_3,SHAPE_4,SHAPE_5,SHAPE_6,SHAPE_7,SHAPE_8,SHAPE_9,SHAPE_10,SHAPE_11,SHAPE_12,SHAPE_13,SHAPE_14,SHAPE_15,SHAPE_16,SHAPE_17,SHAPE_18,SHAPE_19);

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return FINAL_SHAPE;
    }

    @Override
    public boolean hasTileEntity(final BlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world)
    {
        return new SpiritAltarTileEntity();
    }
    public int getLightValue(BlockState state) {
        return state.get(SPIRITS) > 0 ? state.get(SPIRITS) : 0;
    }
    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        TileEntity entity = worldIn.getTileEntity(pos);
        if (!worldIn.isRemote())
        {
            if (entity instanceof SpiritAltarTileEntity)
            {
                if (handIn != Hand.OFF_HAND)
                {
                    ItemStack stack = player.inventory.getCurrentItem();
                    if (stack.getItem() instanceof ItemSpiritContainer)
                    {
                        CompoundNBT nbt = stack.getOrCreateTag();
                        if (nbt.contains("spirit"))
                        {
                            if (((SpiritAltarTileEntity) entity).spirits.size() < 8)
                            {
                                player.swingArm(handIn);
                                ((SpiritAltarTileEntity) entity).spirits.add(nbt.getString("spirit"));

                                ItemStack newStack = stack.getItem().getDefaultInstance();
                                stack.shrink(1);
                                player.inventory.addItemStackToInventory(newStack);
                            }
                            return true;
                        }

                        if (((SpiritAltarTileEntity) entity).spirits.size() > 0)
                        {
                            int slot = 0;
                            for (int a = 0; a < 8; a++)
                            {
                                if (!((SpiritAltarTileEntity) entity).spirits.get(a).isEmpty())
                                {
                                    slot = a;
                                    break;
                                }
                            }
                            ItemStack newStack = stack.getItem().getDefaultInstance();
                            stack.shrink(1);
                            CompoundNBT newNbt = new CompoundNBT();
                            newNbt.putString("spirit", ((SpiritAltarTileEntity) entity).spirits.get(slot));
                            newStack.setTag(newNbt);
                            player.addItemStackToInventory(newStack);
                            ((SpiritAltarTileEntity) entity).spirits.remove(slot);
                        }
                    }
                    else
                    {
                        SpiritInfusionRecipe recipe = ModRecipes.getSpiritInfusionRecipe(stack.getItem(), ((SpiritAltarTileEntity) entity).spirits);
                        if (recipe != null)
                        {
                            stack.shrink(1);
                            player.addItemStackToInventory(recipe.getOutput_item().getDefaultInstance());
                            ((SpiritAltarTileEntity) entity).spirits.clear();
                        }
                    }
                }
                MalumMod.LOGGER.info(((SpiritAltarTileEntity) entity).spirits);
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(SPIRITS);
        builder.add(HORIZONTAL_FACING);
        builder.add(WATERLOGGED);
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
        return this.getDefaultState().with(WATERLOGGED, lvt_4_1_).with(HORIZONTAL_FACING, p_196258_1_.getPlacementHorizontalFacing().getOpposite());
    }
}