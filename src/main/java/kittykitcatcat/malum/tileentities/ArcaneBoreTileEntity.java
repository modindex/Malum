package kittykitcatcat.malum.tileentities;

import kittykitcatcat.malum.registry.ModItems;
import kittykitcatcat.malum.registry.ModTileEntities;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public class ArcaneBoreTileEntity extends TileEntity implements ITickableTileEntity
{
    public int progress;
    public int fuel;
    public int timeDigging;

    public ArcaneBoreTileEntity()
    {
        super(ModTileEntities.arcane_bore_tile_entity);
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
        compound.putInt("progress", progress);
        compound.putInt("fuel", fuel);
        compound.putInt("timeDigging", timeDigging);
        return compound;
    }

    @Override
    public void read(CompoundNBT compound)
    {
        progress = compound.getInt("progress");
        fuel = compound.getInt("v");
        timeDigging = compound.getInt("timeDigging");
        super.read(compound);
    }

    @Override
    public void tick()
    {
        assert world != null;
        if (fuel > 0)
        {
            progress += 1;
            int requiredTicks = 80;
            if (progress >= requiredTicks)
            {
                progress = 0;
                world.addEntity(new ItemEntity(world, pos.getX(), pos.getY() + 1, pos.getZ(), ModItems.drained_shard.getDefaultInstance()));
            }
            fuel -= 1;
            if (timeDigging < requiredTicks)
            {
                timeDigging++;
            }
        }
        else
        {
            if (timeDigging > 0)
            {
                timeDigging--;
            }
        }
    }
}