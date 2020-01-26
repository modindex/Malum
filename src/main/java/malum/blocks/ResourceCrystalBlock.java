package malum.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class ResourceCrystalBlock extends Block
{
    public static final IntegerProperty TIER = IntegerProperty.create("tier", 0, 4);

    public ResourceCrystalBlock(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(TIER, 0));
    }
    private static final VoxelShape SHAPE_SIZE_1 = Block.makeCuboidShape(5D, 0D, 5D, 11D, 4D, 11D);
    private static final VoxelShape SHAPE_SIZE_2 = Block.makeCuboidShape(5D, 0D, 5D, 11D, 6D, 11D);
    private static final VoxelShape SHAPE_SIZE_3 = Block.makeCuboidShape(4D, 0D, 4D, 12D, 7D, 12D);
    private static final VoxelShape SHAPE_SIZE_4 = Block.makeCuboidShape(3D, 0D, 3D, 13D, 9D, 13D);
    private static final VoxelShape SHAPE_SIZE_5 = Block.makeCuboidShape(2D, 0D, 2D, 14D, 10D, 14D);


    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(TIER))
        {
            case(0):
            {
                return SHAPE_SIZE_1;
            }
            case(1):
            {
                return SHAPE_SIZE_2;
            }
            case(2):
            {
                return SHAPE_SIZE_3;
            }
            case(3):
            {
                return SHAPE_SIZE_4;
            }
            case(4):
            {
                return SHAPE_SIZE_5;
            }
        }
        return SHAPE_SIZE_1;
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(TIER);
    }

}