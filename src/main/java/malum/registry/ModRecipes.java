package malum.registry;

import malum.recipes.BlockTransmutationRecipe;
import malum.recipes.BlockTransmutationRecipes;
import net.minecraft.block.BlockState;

import java.util.ArrayList;
import java.util.List;

public class ModRecipes
{
  private static List<BlockTransmutationRecipe> blockTransmutationRecipes = new ArrayList<>();

  public static void addBlockTransmutationRecipe(BlockTransmutationRecipe recipe)
  {
    blockTransmutationRecipes.add(recipe);
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
}