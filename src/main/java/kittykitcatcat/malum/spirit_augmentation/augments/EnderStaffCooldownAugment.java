package kittykitcatcat.malum.spirit_augmentation.augments;

import kittykitcatcat.malum.MalumMod;
import kittykitcatcat.malum.spirit_augmentation.AugmentingSpirit;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nonnull;
import java.util.List;

public class EnderStaffCooldownAugment implements AugmentingSpirit
{
    @Override
    public String augmentTag()
    {
        return MalumMod.ender_staff_cooldown_augment;
    }

    @Override
    public boolean isGrand()
    {
        return false;
    }

    @Override
    public int maxAmount()
    {
        return 10;
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
        return "Decreases the teleport cooldown";
    }

    @Override
    public void handleTooltip(@Nonnull CompoundNBT nbt, List<ITextComponent> tooltip)
    {
        tooltip.add(new StringTextComponent("Teleport cooldown decreased by " + nbt.getInt(augmentTag()) + (nbt.getInt(augmentTag()) == 1 ? " second" : " seconds")));
    }

    @Override
    public String spirit()
    {
        return "minecraft:endermite";
    }
}
