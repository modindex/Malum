package malum.tileentities;


import malum.ClientRefferences;
import malum.MalumMod;
import malum.recipes.RitualRecipe;
import malum.registry.ModRecipes;
import malum.registry.ModSounds;
import malum.registry.ModTileEntities;
import malum.rituals.RitualEffect;
import malum.sounds.RitualLoopTickableSound;
import malum.sounds.RitualStartTickableSound;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Objects;

public class RitualBlockTileEntity extends TileEntity implements ITickableTileEntity
{
    public RitualBlockTileEntity()
    {
        super(ModTileEntities.ritual_block_tile_entity);
    }

    public int crafting;
    public ItemStackHandler inventory = new ItemStackHandler(12)
    {
        
        @Override
        protected void onContentsChanged(int slot)
        {
            RitualBlockTileEntity.this.markDirty();
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
            if (crafting == 1)
            {
                ClientRefferences.minecraft.getSoundHandler().play(new RitualStartTickableSound(this.getTileEntity()));
            }
            if (crafting == 18)
            {
                ClientRefferences.minecraft.getSoundHandler().play(new RitualLoopTickableSound(this.getTileEntity()));
            }
            crafting += 1;
            assert world != null;
            MalumMod.LOGGER.info(crafting);
        }
        if (crafting >= 320)
        {
            RitualRecipe recipe = ModRecipes.getRitualRecipe(listEveryItem(inventory));
            if (recipe != null)
            {
                assert world != null;
                doRitualEffect(pos, recipe.getRitualEffect());
            }
            ClientRefferences.minecraft.getSoundHandler().stop(new ResourceLocation(MalumMod.MODID, "ritual_loop"), SoundCategory.BLOCKS);
            world.playSound(null, pos, ModSounds.ritual_end, SoundCategory.PLAYERS, 1F, 1F);
            crafting = 0;
            emptyInventory(inventory);
        }
    }
    public void doRitualEffect(BlockPos pos, RitualEffect effect)
    {
        BlockPos offsetPos = new BlockPos(1, 0, 1);
        BlockPos TopRightPos = pos.add(offsetPos);
        BlockPos BottomLeftPos = pos.subtract(offsetPos);
        assert world != null;
        int strenght = 4;
        for(BlockPos cycle : BlockPos.getAllInBoxMutable(TopRightPos, BottomLeftPos))
        {
            if (world.getBlockState(cycle).getBlock() == Blocks.REDSTONE_WIRE)
            {
                strenght += 2;
                world.setBlockState(cycle, Blocks.AIR.getDefaultState());
            }
        }
        effect.doRitualEffect(strenght, pos, this);
    }
}