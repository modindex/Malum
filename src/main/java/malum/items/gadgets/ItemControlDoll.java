package malum.items.gadgets;

import malum.items.curios.ItemDarkArtsRing;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.CuriosAPI;

import javax.vecmath.Vector3d;

public class ItemControlDoll extends ItemVoodoDoll
{
    public ItemControlDoll(Item.Properties builder)
    {
        super(builder);
    }
    public void Effect(Entity target, PlayerEntity attacker, ItemStack stack, Hand handIn, float chance, float strength)
    {
        double velX = (random.nextDouble() * 0.5 * (random.nextDouble() > 0.5 ? -1 : 1)) * 0.7D * strength;double velY = (random.nextDouble() * 0.5 * (random.nextDouble() > 0.5 ? -1 : 1)) * 0.4D * strength;double velZ = (random.nextDouble() * 0.5 * (random.nextDouble() > 0.5 ? -1 : 1)) * 0.7D * strength;
        target.setVelocity(velX, velY, velZ);
    }
}