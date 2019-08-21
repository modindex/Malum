package malum.items.gadgets;

import malum.items.curios.ItemDarkArtsRing;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import top.theillusivec4.curios.api.CuriosAPI;

public class ItemRendingDoll extends ItemVoodoDoll
{
    public ItemRendingDoll(Item.Properties builder)
    {
        super(builder);
    }
    public void Effect(Entity target, LivingEntity attacker, ItemStack stack, Hand handIn)
    {
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemDarkArtsRing, attacker).isPresent())
        {
            VoodooPower = 2f;
        }
        else
        {
            VoodooPower = 1f;
        }
        target.attackEntityFrom(DamageSource.causeIndirectDamage(attacker, (LivingEntity) target), 1f * VoodooPower);
    }
}