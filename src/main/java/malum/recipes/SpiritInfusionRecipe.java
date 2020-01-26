package malum.recipes;

import net.minecraft.item.Item;

import java.util.List;

/**
 * Transmutation recipe for Runic Shears
 */
public class SpiritInfusionRecipe
{

    private Item input_item;
    private Item output_item;
    public List<String> spirits;

    public SpiritInfusionRecipe(Item input_item, Item output_item, List<String> spirits)
    {
        this.input_item = input_item;
        this.output_item = output_item;
        this.spirits = spirits;
    }
    public Item getInput_item()
    {
        return input_item;
    }

    public Item getOutput_item()
    {
        return output_item;
    }

    public List<String> getSpirits()
    {
        return spirits;
    }
}
