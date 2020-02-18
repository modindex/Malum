package malum.spirit_augmentation.Augments;

import malum.MalumMod;
import malum.spirit_augmentation.AugmentingSpirit;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nonnull;
import java.util.List;

public class DrownedArmorAugment implements AugmentingSpirit
{
    @Override
    public String augmentTag()
    {
        return MalumMod.drowned_armor_augment;
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
        return "Grants air bubbles when drowning";
    }

    @Override
    public void handleTooltip(@Nonnull CompoundNBT nbt, List<ITextComponent> tooltip)
    {
        tooltip.add(new StringTextComponent("Drowning grants " + (((nbt.getInt(augmentTag()) + nbt.getInt(MalumMod.ender_dragon_armor_augment)) * 15d) / 20d) + (nbt.getInt(augmentTag()) == 1 ? " second" : " seconds") + " of breathing time"));
    }

    @Override
    public String spirit()
    {
        return "minecraft:drowned";
    }
}
