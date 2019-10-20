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
import net.minecraft.util.text.TranslationTextComponent;
import top.theillusivec4.curios.api.CuriosAPI;

import static net.minecraft.block.EnderChestBlock.field_220115_d;

public class ItemEndermanDoll extends ItemVoodoDoll
{
    public ItemEndermanDoll(Item.Properties builder)
    {
        super(builder);
    }
    public void Effect(Entity target, PlayerEntity attacker, ItemStack stack, Hand handIn, float chance, float strength)
    {
        if (random.nextDouble() < chance)
        {
            if (target instanceof PlayerEntity)
            {
                TranslationTextComponent warnMessage = new TranslationTextComponent(attacker.getDisplayName().getFormattedText() + "failed_to_search_ender_chest");
                target.sendMessage(warnMessage);
            }
            return;
        }
        if (target instanceof PlayerEntity)
        {
            EnderChestInventory enderchestinventory = ((PlayerEntity) target).getInventoryEnderChest();
            attacker.openContainer(new SimpleNamedContainerProvider((p_220114_1_, p_220114_2_, p_220114_3_) -> { return ChestContainer.createGeneric9X3(p_220114_1_, p_220114_2_, enderchestinventory); }, field_220115_d));
        }
    }
}