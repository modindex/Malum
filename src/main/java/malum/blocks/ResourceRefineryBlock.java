package malum.blocks;

import malum.recipes.ResourceFormingRecipe;
import malum.registry.ModRecipes;
import malum.tileentities.ResourceRefineryTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ResourceRefineryBlock extends Block
{
    public static final IntegerProperty FUEL = IntegerProperty.create("fuel", 0, 8);

    private static final VoxelShape PART_BOTTOM = Block.makeCuboidShape(0D, 0D, 0D, 16D, 4D, 16D);
    private static final VoxelShape PART_MIDDLE = Block.makeCuboidShape(2D, 4D, 2D, 14D, 10D, 14D);
    private static final VoxelShape PART_TOP = Block.makeCuboidShape(0D, 10D, 0D, 16D, 16D, 16D);
    private static final VoxelShape FINAL_SHAPE = VoxelShapes.or(PART_BOTTOM, PART_MIDDLE, PART_TOP);

    public ResourceRefineryBlock(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(FUEL, 0));
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
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
        return new ResourceRefineryTileEntity();
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(FUEL);
    }
    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isRemote())
        {
            if (handIn != Hand.OFF_HAND)
            {
                if (player.inventory.getCurrentItem().getItem() == Items.BLAZE_POWDER)
                {
                    if (state.get(FUEL) < FUEL.getAllowedValues().size() - 1)
                    {
                        worldIn.setBlockState(pos, state.with(FUEL, FUEL.getAllowedValues().size() - 1));
                        player.inventory.getCurrentItem().shrink(1);
                        player.swingArm(handIn);
                        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                    }
                }
                if (state.get(FUEL) >= 0)
                {
                    ItemStack stack = player.inventory.getCurrentItem();
                    ResourceFormingRecipe recipe = ModRecipes.getResourceFormingRecipe(stack.getItem());
                    if (recipe != null)
                    {
                        if (worldIn.getBlockState(pos.up()).isAir())
                        {
                            worldIn.setBlockState(pos.up(), recipe.getBlock().getDefaultState());
                            player.inventory.getCurrentItem().shrink(1);
                            player.swingArm(handIn);
                            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                        }
                        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                    }
                }
                return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}