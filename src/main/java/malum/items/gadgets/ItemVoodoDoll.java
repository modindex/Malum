package malum.items.gadgets;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Objects;
import java.util.UUID;

public class ItemVoodoDoll extends Item
{
    public ItemVoodoDoll(Item.Properties builder)
    {
        super(builder);
    }
    public void Effect(Entity target, LivingEntity attacker, ItemStack stack, Hand handIn)
    {
        stack.damageItem(1, attacker, (p_220045_0_) -> p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND));
    }
    public static float VoodooPower = 1f;
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (itemstack.hasTag())
        {
            CompoundNBT nbt = itemstack.getTag();
            assert nbt != null;
            UUID uuid = nbt.getUniqueId("entity");
            if (playerIn.world instanceof ServerWorld)
            {
                ServerWorld worldServer = (ServerWorld) playerIn.world;
                if (worldServer.getEntityByUuid(uuid) != null && Objects.requireNonNull(worldServer.getEntityByUuid(uuid)).isAlive())
                {
                    Effect(worldServer.getEntityByUuid(uuid), playerIn, itemstack, handIn);
                    return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
                }
            }
        }
        return new ActionResult<>(ActionResultType.FAIL, itemstack);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand)
    {
        if (!stack.hasTag())
        {
            stack.setTag(new CompoundNBT());
        }
        CompoundNBT nbt = stack.getTag();
        assert nbt != null;
        if (playerIn.world instanceof ServerWorld)
        {
            ServerWorld worldServer = (ServerWorld) playerIn.world;
            UUID uuid = nbt.getUniqueId("entity");
            if (uuid != null)
            {
                if (!(worldServer.getEntityByUuid(uuid) instanceof PlayerEntity))
                {
                    if (worldServer.getEntityByUuid(uuid) != null)
                    {
                        Objects.requireNonNull(worldServer.getEntityByUuid(uuid)).removeTag("cursed");
                    }
                }
            }
        }
        nbt.putUniqueId("entity", target.getUniqueID());
        if (!(target instanceof PlayerEntity))
        {
            target.addTag("cursed");
        }
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }
}