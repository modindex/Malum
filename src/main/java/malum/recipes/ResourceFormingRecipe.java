package malum.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * Transmutation recipe for Runic Shears
 */
public class ResourceFormingRecipe
{

    private Block block;
    private Item formItem;

    public ResourceFormingRecipe(Block block, Item formItem)
    {
        this.block = block;
        this.formItem = formItem;
    }
    public Block getBlock()
    {
        return block;
    }

    public Item getFormItem()
    {
        return formItem;
    }
}
