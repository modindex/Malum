package malum.recipes;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Transmutation recipe for Runic Shears
 */
public class BlockTransmutationRecipe {

  private Block block;
  private Block replacementBlock;
  private String name;

  public BlockTransmutationRecipe(Block block, Block replacementBlock, String name) {
    this.block = block;
    this.replacementBlock = replacementBlock;
    this.name = name;
  }

  public BlockTransmutationRecipe(Block block, Block replacementBlock, ItemStack drop, String name) {
    this(block, replacementBlock, name);
  }
  public Block getBlock() {
    return block;
  }

  public Block getReplacementBlock() {
    return replacementBlock;
  }

  public String getName() {
    return name;
  }
}
