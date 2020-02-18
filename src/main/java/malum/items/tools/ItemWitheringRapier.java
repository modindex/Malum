package malum.items.tools;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ItemWitheringRapier extends SwordItem {
  public ItemWitheringRapier(ItemTier material, int attackDamage, float attackSpeed, Properties properties) {
    super(material, attackDamage, attackSpeed, properties);
  }
  public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
  {
    target.addPotionEffect(new EffectInstance(Effects.WITHER, 30, 1));
    return true;
  }
}