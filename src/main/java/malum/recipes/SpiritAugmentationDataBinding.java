package malum.recipes;

import malum.registry.ModItems;
import malum.registry.ModRecipes;
import malum.spirit_augmentation.EnderStaffAugmentingSpirit;

public class SpiritAugmentationDataBinding
{

  public static void bindItemsToAugmenters()
  {
      SpiritAugmentationData enderStaffAugmentationData = new SpiritAugmentationData(ModItems.ender_staff, new EnderStaffAugmentingSpirit());
      ModRecipes.addSpiritAugmentationData(enderStaffAugmentationData);
  }
}
