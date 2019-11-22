package malum.registry;

import malum.MalumMod;
import malum.recipes.BlockTransmutationRecipe;
import malum.recipes.BlockTransmutationRecipes;
import malum.recipes.RitualRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class ModRecipes
{

    private static ArrayList<RitualRecipe> ritualRecipes = new ArrayList<>();

    private static List<BlockTransmutationRecipe> blockTransmutationRecipes = new ArrayList<>();

    public static void addBlockTransmutationRecipe(BlockTransmutationRecipe recipe)
    {
        blockTransmutationRecipes.add(recipe);
    }

    public static void addRitualRecipe(RitualRecipe recipe)
    {
        ritualRecipes.add(recipe);
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