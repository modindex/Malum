package kittykitcatcat.malum.spirit_augmentation.augments;

import kittykitcatcat.malum.MalumMod;
import kittykitcatcat.malum.spirit_augmentation.AugmentingSpirit;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nonnull;
import java.util.List;

public class BlazeArmorAugment implements AugmentingSpirit
{
    @Override
    public String augmentTag()
    {
        return MalumMod.blaze_armor_augment;
    }

    @Override
    public boolean isGrand()
    {
        return false;
    }

    @Override
    public int maxAmount()
    {
        return 4;
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
        return "Firefighter: Grants a chance to extinguish when damaged by fire";
    }

    @Override
    public void handleTooltip(@Nonnull CompoundNBT nbt, List<ITextComponent> tooltip)
    {
        tooltip.add(new StringTextComponent("Firefighter " + (nbt.getInt(augmentTag()) + nbt.getInt(MalumMod.ender_dragon_armor_augment))));
    }

    @Override
    public String spirit()
    {
        return "minecraft:blaze";
    }
}
