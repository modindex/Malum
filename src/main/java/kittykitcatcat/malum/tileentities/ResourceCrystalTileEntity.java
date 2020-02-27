package kittykitcatcat.malum.tileentities;


import kittykitcatcat.malum.registry.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Objects;

public class ResourceCrystalTileEntity extends TileEntity implements ITickableTileEntity
{
    public float progress;
    public int red;
    public int green;
    public int blue;
    public ItemStackHandler inventory = new ItemStackHandler(1)
    {

        @Override
        protected void onContentsChanged(int slot)
        {
            ResourceCrystalTileEntity.this.markDirty();
            if (!world.isRemote)
            {
                updateContainingBlockInfo();
                BlockState state = world.getBlockState(pos);
                world.notifyBlockUpdate(pos, state, state, 3);
            }
        }
    };

    public ResourceCrystalTileEntity()
    {
        super(ModTileEntities.resource_crystal_tile_entity);
    }

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
        compound.putFloat("progress", progress);
        compound.putInt("red", red);
        compound.putInt("green", green);
        compound.putInt("blue", blue);
        compound.put("inventory", inventory.serializeNBT());
        return compound;
    }

    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);
        progress = compound.getFloat("progress");
        red = compound.getInt("red");
        green = compound.getInt("green");
        blue = compound.getInt("blue");
        inventory.deserializeNBT((CompoundNBT) Objects.requireNonNull(compound.get("inventory")));
    }

    @Override
    public void tick()
    {
        if (world.getTileEntity(pos.down()) instanceof ResourceRefineryTileEntity)
        {
            if (((ResourceRefineryTileEntity) world.getTileEntity(pos.down())).fuel > 0)
            {
                if (progress < 1000)
                {
                    progress++;
                    this.markDirty();
                }
            }
        }
    }
}