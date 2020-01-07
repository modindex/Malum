package malum.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;

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

}