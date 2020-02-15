package malum.tileentities;

import malum.blocks.SpiritAltarBlock;
import malum.registry.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SpiritAltarTileEntity extends TileEntity implements ITickableTileEntity
{
    public SpiritAltarTileEntity()
    {
        super(ModTileEntities.spirit_altar_tile_entity);
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

    public List<String> spirits = new ArrayList<String>();
    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        super.write(compound);
        for (int i = 0; i < spirits.size(); i++)
        {
            compound.putString("spirits_index_" + i, spirits.get(i));
        }
        return compound;
    }
    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);
        for (int i = 0; i < 8; i++)
        {
            if (!compound.getString("spirits_index_" + i).isEmpty())
            {
                spirits.add(compound.getString("spirits_index_" + i));
            }
        }
    }

    @Override
    public void tick()
    {
        if (!this.world.isRemote())
        {
            BlockState state = world.getBlockState(this.pos);
            if (state.getBlock() instanceof SpiritAltarBlock)
            {
                int spirits = state.get(SpiritAltarBlock.SPIRITS);
                if (spirits != this.spirits.size())
                {
                    if (this.spirits.size() <= 8)
                    {
                        world.setBlockState(this.pos, state.with(SpiritAltarBlock.SPIRITS, this.spirits.size()));
                    }
                }
            }
        }
    }
}