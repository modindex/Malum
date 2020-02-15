package malum.registry;

import malum.recipes.*;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModRecipes
{

    public static ArrayList<ResourceFormingRecipe> resourceFormingRecipes = new ArrayList<>();


    public static ArrayList<RitualRecipe> ritualRecipes = new ArrayList<>();

    public static List<BlockTransmutationRecipe> blockTransmutationRecipes = new ArrayList<>();

    public static List<SpiritInfusionRecipe> spiritInfusionRecipes = new ArrayList<>();

    public static void addBlockTransmutationRecipe(BlockTransmutationRecipe recipe)
    {
        blockTransmutationRecipes.add(recipe);
    }

    public static void addResourceFormingRecipe(ResourceFormingRecipe recipe)
    {
        resourceFormingRecipes.add(recipe);
    }

    public static void addSpiritInfusionRecipe(SpiritInfusionRecipe recipe)
    {
        spiritInfusionRecipes.add(recipe);
    }
    public static void addRitualRecipe(RitualRecipe recipe)
    {
        ritualRecipes.add(recipe);
    }

    public static ResourceFormingRecipe getResourceFormingRecipe(Item formItem)
    {
        if (formItem != null)
        {
            for (ResourceFormingRecipe recipe : resourceFormingRecipes)
            {
                if (recipe.getFormItem() == formItem)
                {
                    return recipe;
                }
            }
        }
        return null;
    }
    public static SpiritInfusionRecipe getSpiritInfusionRecipe(Item formItem, List<String> spirits)
    {
        if (formItem != null)
        {
            for (SpiritInfusionRecipe recipe : spiritInfusionRecipes)
            {
                if (spirits.containsAll(recipe.getSpirits()) && spirits.size() == recipe.getSpirits().size())
                {
                    if (recipe.getInput_item() == formItem)
                    {
                        return recipe;
                    }
                }
            }
        }
        return null;
    }
    public static BlockTransmutationRecipe getBlockTransmutationRecipe(BlockState sourceBlock)
    {
        if (sourceBlock != null)
        {
            for (BlockTransmutationRecipe recipe : blockTransmutationRecipes)
            {
                if (recipe.getBlock() == sourceBlock.getBlock())
                {
                    return recipe;
                }
            }
        }
        return null;
    }
    public static RitualRecipe getRitualRecipe(ArrayList<Item> ingredients)
    {
        if (ingredients != null)
        {
            for (RitualRecipe recipe : ritualRecipes)
            {
                if (recipe.getIngredients().equals(ingredients))
                {
                    return recipe;
                }
            }
        }
        return null;
    }
}