package malum.tileentities;


import malum.ClientRefferences;
import malum.MalumMod;
import malum.recipes.BlockTransmutationRecipe;
import malum.recipes.CraftingRecipe;
import malum.recipes.RitualRecipe;
import malum.registry.ModRecipes;
import malum.registry.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.minecart.TNTMinecartEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import static malum.MalumMod.randomize;

public class CraftingBlockTileEntity extends TileEntity implements ITickableTileEntity
{
    public CraftingBlockTileEntity()
    {
        super(ModTileEntities.crafting_block_tile_entity);
    }

    public int crafting;
    public ItemStackHandler inventory = new ItemStackHandler(12)
    {
        
        @Override
        protected void onContentsChanged(int slot)
        {
            CraftingBlockTileEntity.this.markDirty();
            if (!world.isRemote)
            {
                updateContainingBlockInfo();
                BlockState state = world.getBlockState(pos);
                world.notifyBlockUpdate(pos, state, state, 3);
            }
        }
    };

    @Override
    public CompoundNBT getUpdateTag()
    {
        return this.write(new CompoundNBT());
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag)
    {
        read(tag);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        return new SUpdateTileEntityPacket(pos, 0, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        handleUpdateTag(pkt.getNbtCompound());
    }


    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        super.write(compound);
        compound.put("inventory", inventory.serializeNBT());
        compound.putInt("crafting", crafting);
        return compound;
    }

    @Override
    public void read(CompoundNBT compound)
    {
        inventory.deserializeNBT((CompoundNBT) Objects.requireNonNull(compound.get("inventory")));
        crafting = compound.getInt("crafting");
        super.read(compound);
    }
    public ArrayList<Item> listEveryItem(ItemStackHandler inventory)
    {
        ArrayList<Item> ingredients = new ArrayList<>();
        for (int counter=0; counter < inventory.getSlots(); counter++)
        {
            if (!inventory.getStackInSlot(counter).isEmpty())
            {
                ingredients.add(inventory.getStackInSlot(counter).getItem());
            }
        }
        return ingredients;
    }
    public void emptyInventory(ItemStackHandler inventory)
    {
        for (int counter=0; counter < inventory.getSlots(); counter++)
        {
            if (!inventory.getStackInSlot(counter).isEmpty())
            {
                inventory.setStackInSlot(counter, ItemStack.EMPTY);
            }
        }
    }
    @Override
    public void tick()
    {
        if (crafting >= 1)
        {
            crafting += 1;
            assert world != null;
            world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 3);
            MalumMod.LOGGER.info(crafting);
        }
        if (crafting >= 100)
        {
            CraftingRecipe recipe = ModRecipes.getCraftingRecipe(listEveryItem(inventory));
            if (recipe != null)
            {
                world.addEntity(new ItemEntity(world, this.pos.getX() + 0.5, this.pos.getY() + 1, this.pos.getZ() + 0.5, recipe.getItem().getDefaultInstance()));
                crafting = 0;
                emptyInventory(inventory);
            }
        }
    }
}