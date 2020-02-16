package malum.recipes;

import malum.spirit_augmentation.AugmentingSpirit;
import net.minecraft.item.Item;

public class SpiritAugmentationData
{

    private Item item;
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
}
