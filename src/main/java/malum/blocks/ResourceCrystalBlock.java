package malum.blocks;


import malum.tileentities.ResourceRefineryTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ResourceCrystalBlock extends Block
{
    public static final IntegerProperty TIER = IntegerProperty.create("tier", 0, 4);

    public ResourceCrystalBlock(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(TIER, 0));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(TIER);
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

}