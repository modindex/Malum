package malum.recipes;

import malum.registry.ModBlocks;
import malum.registry.ModRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraftforge.common.Tags;

import java.util.Objects;

public class BlockTransmutationRecipes {

  public static void initRecipes()
  {
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.STONE, ModBlocks.refined_pathway));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.STONE_STAIRS, ModBlocks.refined_pathway_stairs));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.STONE_SLAB, ModBlocks.refined_pathway_slab));

      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.STONE_BRICKS, ModBlocks.refined_bricks));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.STONE_BRICK_STAIRS, ModBlocks.refined_bricks_stairs));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.STONE_BRICK_SLAB, ModBlocks.refined_bricks_slab));

      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.SMOOTH_STONE, ModBlocks.refined_smooth_stone));
     //ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.SMOOTH_STONE_STAIRS, ModBlocks.refined_smooth_stone_stairs));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.SMOOTH_STONE_SLAB, ModBlocks.refined_smooth_stone_slab));

      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(ModBlocks.wooden_casing, ModBlocks.wooden_beam));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(ModBlocks.wooden_beam, ModBlocks.wooden_casing));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.CARVED_PUMPKIN, ModBlocks.evil_pumpkin));
      ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(Blocks.JACK_O_LANTERN, ModBlocks.lit_evil_pumpkin));

      for (Block block : BlockTags.LOGS.getAllElements())
      {
          ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(block, ModBlocks.wooden_beam));
      }
      for (Block block : BlockTags.WOODEN_STAIRS.getAllElements())
      {
          ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(block, ModBlocks.wooden_planks_stairs));
      }
      for (Block block : BlockTags.WOODEN_SLABS.getAllElements())
      {
          ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(block, ModBlocks.wooden_planks_slab));
      }
      for (Block block : BlockTags.PLANKS.getAllElements())
      {
          ModRecipes.addBlockTransmutationRecipe(new BlockTransmutationRecipe(block, ModBlocks.wooden_planks));
      }
   }
}
