package malum.registry;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModItemTiers
{
    private static class ItemTier implements IItemTier
    {
        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final LazyLoadBase<Ingredient> repair;
        public ItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> supplier) {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repair = new LazyLoadBase<Ingredient>(supplier);
        }
        @Override
        public int getMaxUses() {
            return maxUses;
        }
        @Override
        public float getEfficiency() {
            return efficiency;
        }
        @Override
        public float getAttackDamage() {
            return attackDamage;
        }
        @Override
        public int getHarvestLevel() {
            return harvestLevel;
        }
        @Override
        public int getEnchantability() {
            return enchantability;
        }
        @Override
        public Ingredient getRepairMaterial() {
            return repair.getValue();
        }
    }

    public static final ItemTier CATASTROPHE = new ItemTier(4, 3122, 10.0F, 4.0F, 40, () -> Ingredient.fromItems(Items.DIAMOND));

    public static final ItemTier NUMINOUS_CATASTROPHE = new ItemTier(5, 15610, 13.0F, 6.0F, 60, () -> Ingredient.fromItems(Items.DIAMOND));
}