package malum.spirit_augmentation.Augments;

import malum.MalumMod;
import malum.spirit_augmentation.AugmentingSpirit;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nonnull;
import java.util.List;

public class SoulEaterBoostUpkeepAugment implements AugmentingSpirit
{
    @Override
    public String augmentTag()
    {
        return MalumMod.soul_eater_boost_upkeep_augment;
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
        return "Decreases the rate at which the damage boost decreases";
    }

    @Override
    public void handleTooltip(@Nonnull CompoundNBT nbt, List<ITextComponent> tooltip)
    {
        tooltip.add(new StringTextComponent("Decreases the rate at which damage boost decreases by " + nbt.getInt(augmentTag()) + "%"));
    }

    @Override
    public String spirit()
    {
        return "minecraft:endermite";
    }
}
