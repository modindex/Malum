package malum.spirit_augmentation.Augments;

import malum.MalumMod;
import malum.spirit_augmentation.AugmentingSpirit;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nonnull;
import java.util.List;

public class EnderStaffDistanceAugment implements AugmentingSpirit
{
    @Override
    public String augmentTag()
    {
        return MalumMod.ender_staff_distance_augment;
    }

    @Override
    public int maxAmount()
    {
        return 20;
    }

    @Override
    public void handleNBT(@Nonnull CompoundNBT nbt)
    {
        if (nbt.contains(augmentTag()))
        {
            int value = nbt.getInt(augmentTag());
            nbt.putInt(augmentTag(), value+1);
        }
        else
        {
            nbt.putInt(augmentTag(), 1);
        }
    }

    @Override
    public String augmentDescription()
    {
        return "Increases the teleport distance";
    }

    @Override
    public String augmentTooltip()
    {
        return "Teleport distance increased by ";
    }

    @Override
    public void handleTooltip(@Nonnull CompoundNBT nbt, List<ITextComponent> tooltip)
    {
        tooltip.add(new StringTextComponent(augmentTooltip() + nbt.getInt(augmentTag()) + ((nbt.getInt(augmentTag()) == 1 ? " block" : " blocks"))));
    }

    @Override
    public String spirit()
    {
        return "minecraft:enderman";
    }
}
