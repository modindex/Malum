package malum.spirit_augmentation;

import malum.registry.ModItems;
import malum.registry.ModRecipes;
import malum.spirit_augmentation.Augments.EnderStaffCooldownAugment;
import malum.spirit_augmentation.Augments.EnderStaffDistanceAugment;

public class SpiritAugmentationDataBinding
{

  public static void bindItemsToAugmenters()
  {
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.ender_staff, new EnderStaffDistanceAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.ender_staff, new EnderStaffCooldownAugment()));
  }
}
