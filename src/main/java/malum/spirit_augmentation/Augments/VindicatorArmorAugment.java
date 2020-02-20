package malum.spirit_augmentation.Augments;

import malum.MalumMod;
import malum.spirit_augmentation.AugmentingSpirit;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nonnull;
import java.util.List;

public class VindicatorArmorAugment implements AugmentingSpirit
{
    @Override
    public String augmentTag()
    {
        return MalumMod.vindicator_armor_augment;
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
        return "Brutal: Hitting an enemy with a critical axe attack deals extra damage";
    }

    @Override
    public void handleTooltip(@Nonnull CompoundNBT nbt, List<ITextComponent> tooltip)
    {
        tooltip.add(new StringTextComponent("Brutal " + (nbt.getInt(augmentTag()) + nbt.getInt(MalumMod.ender_dragon_armor_augment))));
    }

    @Override
    public String spirit()
    {
        return "minecraft:vindicator";
    }
}
