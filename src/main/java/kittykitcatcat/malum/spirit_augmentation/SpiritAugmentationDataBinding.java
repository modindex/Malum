package kittykitcatcat.malum.spirit_augmentation;

import kittykitcatcat.malum.registry.ModItems;
import kittykitcatcat.malum.registry.ModRecipes;
import kittykitcatcat.malum.spirit_augmentation.augments.*;

public class SpiritAugmentationDataBinding
{

    public static void bindItemsToAugmenters()
    {
        ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.ender_staff, new EnderStaffDistanceAugment()));
        ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.ender_staff, new EnderStaffCooldownAugment()));
        ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.phantom_necklace, new PhantomNecklaceFlightTimeAugment()));
        ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_eater, new SoulEaterBoostGainAugment()));
        ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_eater, new SoulEaterBoostUpkeepAugment()));
        addAugmentToArmor(new ZombieArmorAugment());
        addAugmentToArmor(new HuskArmorAugment());
        addAugmentToArmor(new DrownedArmorAugment());
        addAugmentToArmor(new BlazeArmorAugment());
        addAugmentToArmor(new GuardianArmorAugment());
        addAugmentToArmor(new WitchArmorAugment());
        addAugmentToArmor(new PillagerArmorAugment());
        addAugmentToArmor(new VindicatorArmorAugment());
        addAugmentToArmor(new RavagerArmorAugment());
        addAugmentToArmor(new EvokerArmorAugment());
        addAugmentToArmor(new IronGolemArmorAugment());
        addAugmentToArmor(new EnderDragonArmorAugment());
        addAugmentToArmor(new ElderGuardianArmorAugment());
    }

    public static void addAugmentToArmor(AugmentingSpirit spirit)
    {
        ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_helm, spirit));
        ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_chestplate, spirit));
        ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_leggings, spirit));
        ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_crystal_shoes, spirit));

        ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_helm, spirit));
        ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_chestplate, spirit));
        ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_leggings, spirit));
        ModRecipes.addSpiritAugmentationData(new SpiritAugmentationData(ModItems.soul_steel_shoes, spirit));
    }
}
