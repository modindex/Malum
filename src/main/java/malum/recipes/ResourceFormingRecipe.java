package malum.recipes;

import malum.registry.ModRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.state.IProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

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
