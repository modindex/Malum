package malum.spirit_augmentation.Augments;

import malum.MalumMod;
import malum.spirit_augmentation.AugmentingSpirit;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nonnull;
import java.util.List;

public class ElderGuardianArmorAugment implements AugmentingSpirit
{
    @Override
    public String augmentTag()
    {
        return MalumMod.elder_guardian_armor_augment;
    }

    @Override
    public boolean isGrand()
    {
        return true;
    }

    @Override
    public int maxAmount()
    {
        return 5;
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
        return "King of the sea: Doubles the power of the guardian augment";
    }

    @Override
    public void handleTooltip(@Nonnull CompoundNBT nbt, List<ITextComponent> tooltip)
    {
        tooltip.add(new StringTextComponent("King of the sea " + (nbt.getInt(augmentTag()))));
    }

    @Override
    public String spirit()
    {
        return "minecraft:elder_guardian";
    }
}
