package malum.recipes;

import malum.registry.ModBlocks;
import malum.registry.ModRecipes;
import net.minecraft.block.Blocks;

public class BlockTransmutationRecipes {

  public static void initRecipes()
  {
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.PUMPKIN, Blocks.MELON, "pumpkinMelon"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.MELON, Blocks.PUMPKIN, "melonPumpkin"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.NETHER_BRICKS, Blocks.NETHER_WART_BLOCK, "brickWart"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.DIAMOND_BLOCK, Blocks.EMERALD_BLOCK, "diamondEmerald"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.EMERALD_BLOCK, Blocks.DIAMOND_BLOCK, "emeraldDiamond"));

      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.SMOOTH_STONE, ModBlocks.refined_pathway, "stoneRefined"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.OAK_LOG, ModBlocks.wooden_beam, "woodenBeam"));
   }
}
