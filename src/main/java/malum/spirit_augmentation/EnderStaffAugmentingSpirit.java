package malum.spirit_augmentation;

import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class EnderStaffAugmentingSpirit implements AugmentingSpirit
{
    @Override
    public String augmentTag()
    {
        return "range_buff";
    }

    @Override
    public void handleNBT(@Nonnull CompoundNBT nbt)
    {
        if (nbt.contains(augmentTag()))
        {
            int range_buff = nbt.getInt(augmentTag());
            nbt.putInt(augmentTag(), range_buff+1);
        }
        else
        {
            nbt.putInt(augmentTag(), 1);
        }
    }

    @Override
    public String auagmentDescription()
    {
        return "Increases the teleport distance";
    }

    @Override
    public String augmentTooltip(CompoundNBT nbt)
    {
        return "Bonus teleport distance:" + nbt.getInt(augmentTag());
    }

    @Override
    public List<String> acceptableSpirits()
    {
        ArrayList<String> list = new ArrayList<String>();
        list.add("minecraft:enderman");
        return list;
    }
}
