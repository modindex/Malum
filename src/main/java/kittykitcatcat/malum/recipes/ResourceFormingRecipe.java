package kittykitcatcat.malum.recipes;

import net.minecraft.item.Item;

/**
 * Transmutation recipe for Runic Shears
 */
public class ResourceFormingRecipe
{

    private Item formItem;
    private Item outputItem;
    private int red;
    private int green;
    private int blue;

    public ResourceFormingRecipe(Item formItem, Item outputItem, int red, int green, int blue)
    {
        this.formItem = formItem;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.outputItem = outputItem;
    }

    public Item getFormItem()
    {
        return formItem;
    }

    public Item getOutputItem()
    {
        return outputItem;
    }

    public int getRed()
    {
        return red;
    }

    public int getGreen()
    {
        return green;
    }

    public int getBlue()
    {
        return blue;
    }
}
