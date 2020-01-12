package malum.tileentities;

import malum.registry.ModTileEntities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SpiritWellTileEntity extends TileEntity implements ITickableTileEntity
{
    public SpiritWellTileEntity()
    {
        super(ModTileEntities.spirit_well_tile_entity);
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
        for (int i = 0; i < 5; i++)
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

    }
}