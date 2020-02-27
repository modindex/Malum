package kittykitcatcat.malum.tileentities;

import kittykitcatcat.malum.blocks.ResourceCrystalBlock;
import kittykitcatcat.malum.registry.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class ResourceRefineryTileEntity extends TileEntity implements ITickableTileEntity
{
    public int fuel;

    public ResourceRefineryTileEntity()
    {
        super(ModTileEntities.resource_refinery_tile_entity);
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
        compound.putInt("fuel", fuel);
        return compound;
    }

    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);
        fuel = compound.getInt("fuel");
    }

    @Override
    public void tick()
    {
        BlockPos crystalPos = pos.up();
        BlockState crystalBlock = world.getBlockState(crystalPos);
        if (crystalBlock.getBlock() instanceof ResourceCrystalBlock)
        {
            if (fuel > 0)
            {
                if (world.getTileEntity(crystalPos) instanceof ResourceCrystalTileEntity)
                {
                    ResourceCrystalTileEntity tileEntity = (ResourceCrystalTileEntity) world.getTileEntity(crystalPos);
                    if (tileEntity.progress < 1000)
                    {
                        fuel--;
                        world.notifyBlockUpdate(pos.up(), tileEntity.getBlockState(), tileEntity.getBlockState(), 3);
                    }
                }
            }
        }
    }
}