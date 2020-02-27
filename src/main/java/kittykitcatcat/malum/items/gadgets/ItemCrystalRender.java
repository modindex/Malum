package kittykitcatcat.malum.items.gadgets;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ItemCrystalRender extends Item implements IItemColor
{
    public ItemCrystalRender(Properties properties)
    {
        super(properties);
    }

    @Override
    public int getColor(ItemStack stack, int tintIndex)
    {
        if (!stack.hasTag())
        {
            return 0xffffff;
        }
        else
        {
            CompoundNBT tag = stack.getTag();
            return tag.getInt("red") << 16 + tag.getInt("green") << 8 + tag.getInt("blue");
        }
    }
}