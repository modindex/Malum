package malum.spirit_augmentation;

import malum.registry.ModItems;
import malum.registry.ModRecipes;
import malum.spirit_augmentation.Augments.*;

public class SpiritAugmentationDataBinding
{

  public static void bindItemsToAugmenters()
  {
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.ender_staff, new EnderStaffDistanceAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.ender_staff, new EnderStaffCooldownAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.phantom_necklace, new PhantomNecklaceFlightTimeAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_eater, new SoulEaterBoostGainAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_eater, new SoulEaterBoostUpkeepAugment()));

      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_helm, new DrownedArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_chestplate, new DrownedArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_leggings, new DrownedArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_shoes, new DrownedArmorAugment()));

      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_helm, new DrownedArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_chestplate, new DrownedArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_leggings, new DrownedArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_shoes, new DrownedArmorAugment()));

      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_helm, new EnderDragonArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_chestplate, new EnderDragonArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_leggings, new EnderDragonArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_shoes, new EnderDragonArmorAugment()));

      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_helm, new EnderDragonArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_chestplate, new EnderDragonArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_leggings, new EnderDragonArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_shoes, new EnderDragonArmorAugment()));

      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_helm, new BlazeArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_chestplate, new BlazeArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_leggings, new BlazeArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_shoes, new BlazeArmorAugment()));

      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_helm, new BlazeArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_chestplate, new BlazeArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_leggings, new BlazeArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_shoes, new BlazeArmorAugment()));

      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_helm, new WitchArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_chestplate, new WitchArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_leggings, new WitchArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_shoes, new WitchArmorAugment()));

      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_helm, new WitchArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_chestplate, new WitchArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_leggings, new WitchArmorAugment()));
      ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_shoes, new WitchArmorAugment()));
  }
}
