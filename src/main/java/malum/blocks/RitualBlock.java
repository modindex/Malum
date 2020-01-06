package malum.blocks;


import malum.recipes.RitualRecipe;
import malum.registry.ModItems;
import malum.registry.ModRecipes;
import malum.registry.ModTileEntities;
import malum.tileentities.RitualBlockTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

import static net.minecraft.block.ChestBlock.WATERLOGGED;

public class RitualBlock extends Block
{

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public RitualBlock(Properties properties)
    {
        super(properties);
        this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, false);
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
                        if (player.inventory.getCurrentItem().getItem() == ModItems.ritual_activator)
                        {
                            if (recipe != null)
                            {
                                if (!stack.getTag().hasUniqueId("uuid") && recipe.getTarget().equals("uuid"))
                                {
                                    StringTextComponent text = new StringTextComponent("this ritual takes an entity, not a world position");
                                    player.sendMessage(text);
                                    return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                                }
                                if (!stack.getTag().contains("data") && recipe.getTarget().equals("position"))
                                {
                                    StringTextComponent text = new StringTextComponent("this ritual takes a world position, not an entity");
                                    player.sendMessage(text);
                                    return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                                }
                                if (stack.getTag().contains("data") && recipe.getTarget().equals("position"))
                                {
                                    int[] tag = stack.getTag().getIntArray("data");
                                    if (tag.length != 0)
                                    {
                                        ((RitualBlockTileEntity) entity).position = new int[]{tag[0], tag[1], tag[2]};
                                        ((RitualBlockTileEntity) entity).crafting = 1;
                                        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                                    }
                                }
                                if (stack.getTag().hasUniqueId("uuid") && recipe.getTarget().equals("uuid"))
                                {
                                    UUID tag = stack.getTag().getUniqueId("uuid");
                                    ((RitualBlockTileEntity) entity).uuid = tag;
                                    ((RitualBlockTileEntity) entity).crafting = 1;
                                    return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                                }
                            }
                            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                        }
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
                        else
                        {
                            int firstEmptySlot = getFirstEmptySlot(inventory);

                            if (firstEmptySlot == -1)
                            {
                                return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                            }

                            ItemStack stackToAdd = new ItemStack(player.inventory.getCurrentItem().getItem());
                            stackToAdd.setTag(player.inventory.getCurrentItem().getTag());
                            inventory.setStackInSlot(firstEmptySlot, stackToAdd);
                            player.inventory.getCurrentItem().split(1);
                            int slot;
                            for (slot = 0; slot < inventory.getSlots() - 1; slot += 1)
                            {
                                LOGGER.info(inventory.getStackInSlot(slot));
                            }
                            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                        }
                    }
                }
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Nonnull
    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_)
    {
        return p_185499_1_.with(FACING, p_185499_2_.rotate(p_185499_1_.get(FACING)));
    }

    @Nonnull
    public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_)
    {
        return p_185471_1_.rotate(p_185471_2_.toRotation(p_185471_1_.get(FACING)));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_)
    {
        p_206840_1_.add(WATERLOGGED, FACING);
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
        return this.getDefaultState().with(FACING, p_196258_1_.getPlacementHorizontalFacing()).with(WATERLOGGED, lvt_4_1_);
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