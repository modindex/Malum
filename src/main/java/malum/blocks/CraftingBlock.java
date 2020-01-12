package malum.blocks;


import malum.recipes.RitualRecipe;
import malum.registry.ModItems;
import malum.registry.ModRecipes;
import malum.tileentities.RitualBlockTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

import static net.minecraft.block.ChestBlock.WATERLOGGED;

public class CraftingBlock extends Block
{



    public CraftingBlock(Properties properties)
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
        return new RitualBlockTileEntity();
    }

    public ArrayList<Item> listEveryItem(ItemStackHandler inventory)
    {
        ArrayList<Item> ingredients = new ArrayList<>();
        for (int counter = 0; counter < inventory.getSlots(); counter++)
        {
            if (!inventory.getStackInSlot(counter).isEmpty())
            {
                ingredients.add(inventory.getStackInSlot(counter).getItem());
            }
        }
        return ingredients;
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        TileEntity entity = worldIn.getTileEntity(pos);
        if (!worldIn.isRemote())
        {
            if (entity instanceof RitualBlockTileEntity)
            {
                if (handIn != Hand.OFF_HAND)
                {
                    ItemStackHandler inventory = ((RitualBlockTileEntity) entity).inventory;
                    ItemStack stack = player.inventory.getCurrentItem();
                    RitualRecipe recipe = ModRecipes.getRitualRecipe(listEveryItem(inventory));

                    if (((RitualBlockTileEntity) entity).crafting == 0)
                    {
                        if (stack.getItem() == ModItems.ritual_activator)
                        {
                            if (recipe != null)
                            {
                                ((RitualBlockTileEntity) entity).crafting = 1;
                            }
                            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                        }
                        //TAKING ITEMS OUT
                        if (player.isSneaking())
                        {
                            int firstNotEmptyStack = getFirstNotEmptySlot(inventory);
                            if (firstNotEmptyStack == -1)
                            {
                                return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                            }
                            ItemStack stackToAdd = inventory.getStackInSlot(firstNotEmptyStack);
                            stackToAdd.setCount(1);
                            player.addItemStackToInventory(stackToAdd);
                            inventory.setStackInSlot(firstNotEmptyStack, ItemStack.EMPTY);
                            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                        }
                        //PUTTING ITEMS IN
                        else
                        {
                            int firstEmptySlot = getFirstEmptySlot(inventory);
                            if (firstEmptySlot == -1)
                            {
                                return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                            }

                            ItemStack stackToAdd = stack.copy();
                            inventory.setStackInSlot(firstEmptySlot, stackToAdd);
                            stack.split(1);
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

    public int getFirstNotEmptySlot(ItemStackHandler inventory)
    {
        for (int counter = inventory.getSlots() - 1; counter >= 0; counter--)
        {
            if (!inventory.getStackInSlot(counter).isEmpty())
            {
                return counter;
            }
        }
        return -1;
    }

    public int getFirstEmptySlot(ItemStackHandler inventory)
    {
        for (int counter = 0; counter < inventory.getSlots(); counter++)
        {
            if (inventory.getStackInSlot(counter).isEmpty())
            {
                return counter;
            }
        }
        return -1;
    }
}