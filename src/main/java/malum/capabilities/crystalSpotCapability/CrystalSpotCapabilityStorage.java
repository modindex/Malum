package malum.capabilities.crystalSpotCapability;


import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CrystalSpotCapabilityStorage implements Capability.IStorage<ICrystalSpotCapability>
{
    @Nullable
    @Override
    public INBT writeNBT(Capability<ICrystalSpotCapability> capability, ICrystalSpotCapability instance, Direction side)
    {
        CompoundNBT compoundNBT = new CompoundNBT();

        List<Integer> listPos = new ArrayList<>();
        listPos.add(instance.pos().getX());
        listPos.add(instance.pos().getY());
        listPos.add(instance.pos().getZ());

        compoundNBT.putFloat("potency", instance.potency());
        compoundNBT.putIntArray("pos", listPos);
        return compoundNBT;
    }

    @Override
    public void readNBT(Capability<ICrystalSpotCapability> capability, ICrystalSpotCapability instance, Direction side, INBT nbt)
    {
        if (nbt instanceof CompoundNBT)
        {
            CompoundNBT compoundNBT = (CompoundNBT) nbt.copy();

            int[] listPos = compoundNBT.getIntArray("pos");
            BlockPos blockPos = new BlockPos(listPos[0], listPos[1], listPos[2]);

            instance.setPotency(compoundNBT.getInt("pos"));
            instance.setBlockPos(blockPos);
        }
    }
}
