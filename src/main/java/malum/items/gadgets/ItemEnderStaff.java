package malum.items.gadgets;

import malum.capabilities.PlayerProperties;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
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
    /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     */
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity)
        {
            PlayerEntity playerentity = (PlayerEntity)entityLiving;
            
            playerentity.addStat(Stats.ITEM_USED.get(this));
            PlayerProperties.setCanTeleport(playerentity, false);
        }
    }
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);

        PlayerProperties.setTeleportChargeTime(playerIn, PlayerProperties.getTeleportChargeTime(playerIn) < 20 ? PlayerProperties.getTeleportChargeTime(playerIn)+1 : 20);

        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }
}