package malum.tileentities;

import malum.blocks.ResourceCrystalBlock;
import malum.registry.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

import static malum.blocks.ResourceRefineryBlock.FUEL;

public class ResourceRefineryTileEntity extends TileEntity implements ITickableTileEntity
{
    public ResourceRefineryTileEntity()
    {
        super(ModTileEntities.resource_refinery_tile_entity);
    }

    int progress;

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
        return compound;
    }

    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);
        progress = compound.getInt("progress");
    }

    @Override
    public void tick()
    {
        BlockPos crystalPos = pos.up();
        assert world != null;
        BlockState crystalBlock = world.getBlockState(crystalPos);
        if (crystalBlock.getBlock() instanceof ResourceCrystalBlock)
        {
            BlockState refinery = world.getBlockState(pos);
            if (refinery.get(FUEL) > 0)
            {
                progress += 1;
                int requiredTicks = 80;
                if (progress >= requiredTicks)
                {
                    progress = 0;
                    int tier = crystalBlock.get(ResourceCrystalBlock.TIER);
                    if (tier < ResourceCrystalBlock.TIER.getAllowedValues().size() - 2)
                    {
                        world.setBlockState(pos, refinery.with(FUEL, world.getBlockState(pos).get(FUEL) - 1));
                        world.setBlockState(crystalPos, crystalBlock.with(ResourceCrystalBlock.TIER, tier + 1));
                    }

                }
            }
        }
    }
}