package kittykitcatcat.malum.blocks;

import kittykitcatcat.malum.recipes.ResourceFormingRecipe;
import kittykitcatcat.malum.registry.ModBlocks;
import kittykitcatcat.malum.registry.ModRecipes;
import kittykitcatcat.malum.tileentities.ResourceCrystalTileEntity;
import kittykitcatcat.malum.tileentities.ResourceRefineryTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
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
        return new ResourceRefineryTileEntity();
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(FUEL);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isRemote())
        {
            if (handIn != Hand.OFF_HAND)
            {
                if (player.getHeldItem(handIn) != ItemStack.EMPTY)
                {
                    for (ResourceFormingRecipe resourceFormingRecipe : ModRecipes.resourceFormingRecipes)
                    {
                        if (player.getHeldItem(handIn).getItem().equals(resourceFormingRecipe.getFormItem()))
                        {
                            worldIn.setBlockState(pos.up(), ModBlocks.resource_crystal.getDefaultState());
                            if (worldIn.getTileEntity(pos.up()) instanceof ResourceCrystalTileEntity)
                            {
                                worldIn.notifyBlockUpdate(pos.up(), state, state, 3);
                                worldIn.notifyBlockUpdate(pos, state, state, 3);

                                ResourceCrystalTileEntity tileEntity = (ResourceCrystalTileEntity) worldIn.getTileEntity(pos.up());
                                worldIn.getTileEntity(pos).markDirty();
                                tileEntity.markDirty();
                                tileEntity.inventory.setStackInSlot(0, resourceFormingRecipe.getOutputItem().getDefaultInstance());
                                tileEntity.red = resourceFormingRecipe.getRed();
                                tileEntity.green = resourceFormingRecipe.getGreen();
                                tileEntity.blue = resourceFormingRecipe.getBlue();
                                return ActionResultType.SUCCESS;
                            }
                        }
                    }
                    if (player.getHeldItem(handIn).getItem().equals(Items.BLAZE_POWDER))
                    {
                        if (worldIn.getTileEntity(pos) instanceof ResourceRefineryTileEntity)
                        {
                            ResourceRefineryTileEntity tileEntity = (ResourceRefineryTileEntity) worldIn.getTileEntity(pos);
                            tileEntity.fuel += 12000;
                            player.getHeldItem(handIn).shrink(1);
                            return ActionResultType.SUCCESS;
                        }
                    }
                }
                return ActionResultType.FAIL;
            }
        }
        return ActionResultType.PASS;
    }
}