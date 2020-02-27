package kittykitcatcat.malum.spirit_augmentation.augments;

import kittykitcatcat.malum.MalumMod;
import kittykitcatcat.malum.spirit_augmentation.AugmentingSpirit;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nonnull;
import java.util.List;

public class PillagerArmorAugment implements AugmentingSpirit
{
    @Override
    public String augmentTag()
    {
        return MalumMod.pillager_armor_augment;
    }

    @Override
    public boolean isGrand()
    {
        return false;
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
        return "Field hunter: Grants a chance to reload a held crossbow upon hitting an enemy with an arrow fired from said crossbow";
    }

    @Override
    public void handleTooltip(@Nonnull CompoundNBT nbt, List<ITextComponent> tooltip)
    {
        tooltip.add(new StringTextComponent("Field Hunter " + (nbt.getInt(augmentTag()) + nbt.getInt(MalumMod.ender_dragon_armor_augment))));
    }

    @Override
    public String spirit()
    {
        return "minecraft:pillager";
    }
}
