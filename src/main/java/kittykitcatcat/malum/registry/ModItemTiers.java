package kittykitcatcat.malum.registry;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class ModItemTiers
{
    public static final ItemTier SOUL_CRYSTAL = new ItemTier(
        3,
        880,
        7.0F,
        2.5F,
        22);
    public static final ItemTier CATASTROPHE = new ItemTier(
        3,
        1820,
        9.0F,
        3.5F,
        24);

    private static class ItemTier implements IItemTier
    {
        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;

        public ItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability)
        {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
        }

        @Override
        public int getMaxUses()
        {
            return maxUses;
        }

        @Override
        public float getEfficiency()
        {
            return efficiency;
        }

        @Override
        public float getAttackDamage()
        {
            return attackDamage;
        }

        @Override
        public int getHarvestLevel()
        {
            return harvestLevel;
        }

        @Override
        public int getEnchantability()
        {
            return enchantability;
        }

        @Override
        public Ingredient getRepairMaterial()
        {
            return null;
        }
    }
}