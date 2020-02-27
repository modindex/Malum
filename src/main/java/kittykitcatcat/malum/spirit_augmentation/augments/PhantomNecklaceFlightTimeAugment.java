package kittykitcatcat.malum.spirit_augmentation.augments;

import kittykitcatcat.malum.MalumMod;
import kittykitcatcat.malum.spirit_augmentation.AugmentingSpirit;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nonnull;
import java.util.List;

public class PhantomNecklaceFlightTimeAugment implements AugmentingSpirit
{
    @Override
    public String augmentTag()
    {
        return MalumMod.phantom_necklace_flight_time_augment;
    }

    @Override
    public boolean isGrand()
    {
        return false;
    }

    @Override
    public int maxAmount()
    {
        return 80;
    }

    @Override
    public void handleNBT(@Nonnull CompoundNBT nbt)
    {
        if (nbt.contains(augmentTag()))
        {
            int value = nbt.getInt(augmentTag());
            nbt.putInt(augmentTag(), value + 1);
        }
        else
        {
            nbt.putInt(augmentTag(), 1);
        }
    }

    @Override
    public String augmentDescription()
    {
        return "Increases the time you can boost yourself upwards";
    }

    @Override
    public void handleTooltip(@Nonnull CompoundNBT nbt, List<ITextComponent> tooltip)
    {
        tooltip.add(new StringTextComponent((double) (nbt.getInt(augmentTag())) / 20 + (nbt.getInt(augmentTag()) == 1 ? " Second" : " Seconds") + " of extra boost time"));
    }

    @Override
    public String spirit()
    {
        return "minecraft:phantom";
    }
}
