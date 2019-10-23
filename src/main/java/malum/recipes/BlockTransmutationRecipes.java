package malum.recipes;

import malum.registry.ModBlocks;
import malum.registry.ModRecipes;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.Tags;

public class BlockTransmutationRecipes {

  public static void initRecipes()
  {
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.PUMPKIN, Blocks.MELON, "pumpkinMelon"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.MELON, Blocks.PUMPKIN, "melonPumpkin"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.NETHER_BRICKS, Blocks.NETHER_WART_BLOCK, "brickWart"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.DIAMOND_BLOCK, Blocks.EMERALD_BLOCK, "diamondEmerald"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.EMERALD_BLOCK, Blocks.DIAMOND_BLOCK, "emeraldDiamond"));

      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.STONE, ModBlocks.refined_pathway, "refinedPathway"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.BRICKS, ModBlocks.refined_bricks, "refinedBricks"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(ModBlocks.wooden_casing, ModBlocks.wooden_beam, "woodenBeam"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(ModBlocks.wooden_beam, ModBlocks.wooden_casing, "woodenCasing"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.OAK_LOG, ModBlocks.wooden_beam, "woodenBeamOak"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.BIRCH_LOG, ModBlocks.wooden_beam, "woodenBeamBirch"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.SPRUCE_LOG, ModBlocks.wooden_beam, "woodenBeamSpruce"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.JUNGLE_LOG, ModBlocks.wooden_beam, "woodenBeamJungle"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.DARK_OAK_LOG, ModBlocks.wooden_beam, "woodenBeamDarkOak"));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.ACACIA_LOG, ModBlocks.wooden_beam, "woodenBeamAcacia"));
   }
}
