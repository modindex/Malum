package malum.items.gadgets;

import malum.capabilities.PlayerProperties;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemEnderStaff extends Item
{
    public ItemEnderStaff(Properties builder)
    {
        super(builder);
    }
    public int getUseDuration(ItemStack stack) {
        return 14;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (isSelected)
        {
            if (entityIn instanceof PlayerEntity)
            {
                PlayerEntity player = (PlayerEntity) entityIn;
                if (player.getHeldItemMainhand().getItem() instanceof ItemEnderStaff)
                {
                    if (PlayerProperties.getCanTeleport(player))
                    {
                        PlayerProperties.setTeleportChargeTime(player, PlayerProperties.getTeleportChargeTime(player) < 15 ? PlayerProperties.getTeleportChargeTime(player) + 1 : 15);
                    }
                }
            }
        }
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
            PlayerProperties.setCanTeleport(playerIn, true);
            playerIn.swingArm(handIn);

        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        if (stack.getTag() != null && stack.getTag().contains("range_buff"))
        {
            tooltip.add(component("Teleport Distance Bonus: " + stack.getTag().getInt("range_buff")));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
    public StringTextComponent component(String string)
    {
        return new StringTextComponent(string);
    }
}