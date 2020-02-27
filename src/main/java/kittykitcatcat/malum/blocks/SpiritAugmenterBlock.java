package kittykitcatcat.malum.blocks;

import kittykitcatcat.malum.items.armor.ItemArmorSoulCrystal;
import kittykitcatcat.malum.items.armor.ItemArmorSoulSteel;
import kittykitcatcat.malum.items.gadgets.ItemSpiritContainer;
import kittykitcatcat.malum.registry.ModRecipes;
import kittykitcatcat.malum.spirit_augmentation.SpiritAugmentationData;
import kittykitcatcat.malum.tileentities.SpiritAugmenterTileEntity;
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
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

import static net.minecraft.block.ChestBlock.WATERLOGGED;

public class SpiritAugmenterBlock extends Block
{
    public SpiritAugmenterBlock(Properties properties)
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
        return new SpiritAugmenterTileEntity();
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

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        TileEntity entity = worldIn.getTileEntity(pos);
        if (!worldIn.isRemote())
        {
            if (entity instanceof SpiritAugmenterTileEntity)
            {
                if (handIn != Hand.OFF_HAND)
                {
                    ItemStackHandler inventory = ((SpiritAugmenterTileEntity) entity).inventory;
                    ItemStack spirit_bottle = player.inventory.getCurrentItem();
                    if (spirit_bottle.getItem() instanceof ItemSpiritContainer)
                    {
                        if (spirit_bottle.getTag() != null)
                        {
                            if (!inventory.getStackInSlot(0).isEmpty())
                            {
                                ItemStack augment_stack = inventory.getStackInSlot(0);
                                CompoundNBT augment_nbt = augment_stack.getOrCreateTag();
                                for (SpiritAugmentationData data : ModRecipes.spiritAugmentationData)
                                {
                                    if (data != null)
                                    {
                                        if (data.getItem() == augment_stack.getItem())
                                        {
                                            String spirit = spirit_bottle.getTag().getString("spirit");
                                            if (data.getSpirit().spirit().equals(spirit))
                                            {
                                                if ((augment_stack.getItem() instanceof ItemArmorSoulSteel || augment_stack.getItem() instanceof ItemArmorSoulCrystal))
                                                {
                                                    int maxAugments = (augment_stack.getItem() instanceof ItemArmorSoulCrystal) ? 20 : 30;
                                                    if (data.getItem() == augment_stack.getItem())
                                                    {
                                                        if (augment_nbt.contains(data.getSpirit().augmentTag()))
                                                        {
                                                            maxAugments -= augment_nbt.getInt(data.getSpirit().augmentTag());
                                                        }
                                                    }
                                                    if (maxAugments <= 0 || SpiritAugmentationData.doesItemHaveGrandAugment(augment_stack) && data.getSpirit().isGrand() && !data.getSpirit().spirit().equals(Objects.requireNonNull(SpiritAugmentationData.getGrandAugmentFromStack(augment_stack)).spirit()))
                                                    {
                                                        return true;
                                                    }
                                                }
                                                if (augment_nbt.getInt(data.getSpirit().augmentTag()) < data.getSpirit().maxAmount())
                                                {
                                                    data.getSpirit().handleNBT(augment_nbt);

                                                    ItemStack newStack = spirit_bottle.getItem().getDefaultInstance();
                                                    spirit_bottle.shrink(1);
                                                    player.inventory.addItemStackToInventory(newStack);
                                                }
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return true;
                    }
                    if (spirit_bottle.isEmpty())
                    {
                        if (!inventory.getStackInSlot(0).isEmpty())
                        {
                            player.addItemStackToInventory(inventory.getStackInSlot(0));
                            return true;
                        }
                    }
                    else
                    {
                        if (inventory.getStackInSlot(0).isEmpty())
                        {
                            inventory.setStackInSlot(0, spirit_bottle);
                            player.setHeldItem(handIn, ItemStack.EMPTY);
                            return true;
                        }
                    }
                }
            }
        }
        return true;
    }
}