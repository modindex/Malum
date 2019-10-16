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
import net.minecraft.util.text.StringTextComponent;
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
            VoodooPower = 1f;
        }
        else
        {
            VoodooPower = 0.25f;
        }
        if (random.nextDouble() < VoodooPower)
        {
            if (target instanceof PlayerEntity)
            {
                StringTextComponent warnMessage = new StringTextComponent(attacker.getDisplayName() + "has tried to search your ender chest but failed");
                target.sendMessage(warnMessage);
            }
            return;
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