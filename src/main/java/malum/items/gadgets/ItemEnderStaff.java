package malum.items.gadgets;

import malum.capabilities.PlayerProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemEnderStaff extends Item
{
    public ItemEnderStaff(Properties builder)
    {
        super(builder);
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
                    if (PlayerProperties.getIsTeleporting(player))
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
            PlayerProperties.setIsTeleporting(playerIn, true);
            playerIn.swingArm(handIn);

        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }
}