package malum.registry;

import malum.MalumMod;
import malum.recipes.*;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class ModRecipes
{

    private static ArrayList<ResourceFormingRecipe> resourceFormingRecipes = new ArrayList<>();

    private static ArrayList<CraftingRecipe> craftingRecipes = new ArrayList<>();

    private static ArrayList<RitualRecipe> ritualRecipes = new ArrayList<>();

    private static List<BlockTransmutationRecipe> blockTransmutationRecipes = new ArrayList<>();

    public static void addBlockTransmutationRecipe(BlockTransmutationRecipe recipe)
    {
        blockTransmutationRecipes.add(recipe);
    }

    public static void addResourceFormingRecipe(ResourceFormingRecipe recipe)
    {
        resourceFormingRecipes.add(recipe);
    }
    public static void addRitualRecipe(RitualRecipe recipe)
    {
        ritualRecipes.add(recipe);
    }

    public static void addCraftingRecipe(CraftingRecipe recipe)
    {
        craftingRecipes.add(recipe);
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

    public static CraftingRecipe getCraftingRecipe(ArrayList<Item> ingredients)
    {
        if (ingredients != null)
        {
            for (CraftingRecipe recipe : craftingRecipes)
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