package malum.recipes;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> createSpiritList(String spirit_1, String spirit_2, String spirit_3, String spirit_4, String spirit_5, String spirit_6, String spirit_7, String spirit_8)
    {
        ArrayList<String> list = new ArrayList<>();
        list.add(spirit_1);
        list.add(spirit_2);
        list.add(spirit_3);
        list.add(spirit_4);
        list.add(spirit_5);
        list.add(spirit_6);
        list.add(spirit_7);
        list.add(spirit_8);
        return list;
    }
    public static List<String> createSpiritList(String spirit_1, String spirit_2, String spirit_3, String spirit_4, String spirit_5, String spirit_6, String spirit_7)
    {
        ArrayList<String> list = new ArrayList<>();
        list.add(spirit_1);
        list.add(spirit_2);
        list.add(spirit_3);
        list.add(spirit_4);
        list.add(spirit_5);
        list.add(spirit_6);
        list.add(spirit_7);
        return list;
    }
    public static List<String> createSpiritList(String spirit_1, String spirit_2, String spirit_3, String spirit_4, String spirit_5, String spirit_6)
    {
        ArrayList<String> list = new ArrayList<>();
        list.add(spirit_1);
        list.add(spirit_2);
        list.add(spirit_3);
        list.add(spirit_4);
        list.add(spirit_5);
        list.add(spirit_6);
        return list;
    }
    public static List<String> createSpiritList(String spirit_1, String spirit_2, String spirit_3, String spirit_4, String spirit_5)
    {
        ArrayList<String> list = new ArrayList<>();
        list.add(spirit_1);
        list.add(spirit_2);
        list.add(spirit_3);
        list.add(spirit_4);
        list.add(spirit_5);
        return list;
    }
    public static List<String> createSpiritList(String spirit_1, String spirit_2, String spirit_3, String spirit_4)
    {
        ArrayList<String> list = new ArrayList<>();
        list.add(spirit_1);
        list.add(spirit_2);
        list.add(spirit_3);
        list.add(spirit_4);
        return list;
    }
    public static List<String> createSpiritList(String spirit_1, String spirit_2, String spirit_3)
    {
        ArrayList<String> list = new ArrayList<>();
        list.add(spirit_1);
        list.add(spirit_2);
        list.add(spirit_3);
        return list;
    }
    public static List<String> createSpiritList(String spirit_1, String spirit_2)
    {
        ArrayList<String> list = new ArrayList<>();
        list.add(spirit_1);
        list.add(spirit_2);
        return list;
    }
    public static List<String> createSpiritList(String spirit_1)
    {
        ArrayList<String> list = new ArrayList<>();
        list.add(spirit_1);
        return list;
    }
}
