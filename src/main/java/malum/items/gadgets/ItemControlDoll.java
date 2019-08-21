package malum.items.gadgets;

import malum.items.curios.ItemDarkArtsRing;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import top.theillusivec4.curios.api.CuriosAPI;

import javax.vecmath.Vector3d;

public class ItemControlDoll extends ItemVoodoDoll
{
    public ItemControlDoll(Item.Properties builder)
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
        double velX = (random.nextDouble() * 0.5 * (random.nextDouble() > 0.5 ? -1 : 1)) * 0.7D * VoodooPower;
        double velY = (random.nextDouble() * 0.5 * (random.nextDouble() > 0.5 ? -1 : 1)) * 0.4D * VoodooPower;
        double velZ = (random.nextDouble() * 0.5 * (random.nextDouble() > 0.5 ? -1 : 1)) * 0.7D * VoodooPower;
        Vector3d Velocity = new Vector3d(velX, velY, velZ);
        target.setVelocity(Velocity.getX(), Velocity.getY(), Velocity.getZ());
    }
}