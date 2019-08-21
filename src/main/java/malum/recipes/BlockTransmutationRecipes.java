package malum.recipes;

import malum.registry.ModBlocks;
import malum.registry.ModRecipes;
import net.minecraft.block.Blocks;

public class BlockTransmutationRecipes {

  public static void initRecipes()
  {
    ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.OAK_LOG, ModBlocks.wooden_beam, "test"));
   }
}
