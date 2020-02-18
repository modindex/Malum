package malum.spirit_augmentation;

import malum.MalumMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class SpiritAugmentationData
{

    Item item;
    AugmentingSpirit spirit;

    public SpiritAugmentationData(Item item, AugmentingSpirit spirit)
    {
        this.item = item;
        this.spirit = spirit;
    }

    public Item getItem()
    {
        return item;
    }

    public AugmentingSpirit getSpirit()
    {
        return spirit;
    }


    public static int getAugmentAmountFromArmor(NonNullList<ItemStack> armor, String string)
    {
        int result = 0;
        for (int i = 0; i <= 3; i++)
        {
            ItemStack stack = armor.get(i);
            if (stack.getTag() != null)
            {
                if (stack.getTag().contains(string))
                {
                    result += stack.getTag().getInt(string);
                    if (stack.getTag().contains(MalumMod.ender_dragon_armor_augment))
                    {
                        result += 1;
                    }
                }
            }
        }
        return result;
    }
    public static int getAugmentAmountFromStack(ItemStack stack, String string)
    {
        if (stack.getTag() != null)
        {
            if (stack.getTag().contains(string))
            {
                return stack.getTag().getInt(string);
            }
        }
        return 0;
    }
}
