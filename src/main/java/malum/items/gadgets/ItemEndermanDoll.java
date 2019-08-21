package malum.items.gadgets;

import malum.items.curios.ItemDarkArtsRing;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import top.theillusivec4.curios.api.CuriosAPI;

import static net.minecraft.block.EnderChestBlock.field_220115_d;

public class ItemEndermanDoll extends ItemVoodoDoll
{
    public ItemEndermanDoll(Item.Properties builder)
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
        if (VoodooPower == 1f)
        {
            if (target.world.isRemote()) //warn target that their enderchest is being pick pocketed
            {

            }
        }
        if (attacker instanceof PlayerEntity)
        {
            if (target instanceof PlayerEntity)
            {
                EnderChestInventory enderchestinventory = ((PlayerEntity) target).getInventoryEnderChest();
                ((PlayerEntity) attacker).openContainer(new SimpleNamedContainerProvider((p_220114_1_, p_220114_2_, p_220114_3_) -> {
                    return ChestContainer.createGeneric9X3(p_220114_1_, p_220114_2_, enderchestinventory);
                }, field_220115_d));
            }
        }
    }
}