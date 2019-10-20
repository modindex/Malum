package malum.items.gadgets;

import malum.items.curios.ItemDarkArtsRing;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
    public void Effect(Entity target, PlayerEntity attacker, ItemStack stack, Hand handIn, float chance, float strength)
    {
        target.attackEntityFrom(DamageSource.causeIndirectDamage(target, attacker), 1f * strength);
    }
}