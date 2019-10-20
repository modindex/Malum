package malum.items.gadgets;

import malum.capabilities.PlayerProperties;
import malum.items.curios.ItemDarkArtsRing;
import malum.items.curios.ItemLuckRing;
import net.minecraft.client.audio.Sound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import top.theillusivec4.curios.api.CuriosAPI;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

public class ItemVoodoDoll extends Item
{
    public ItemVoodoDoll(Item.Properties builder)
    {
        super(builder);
    }
    public void Effect(Entity target, PlayerEntity attacker, ItemStack stack, Hand handIn, float chance, float strength)
    {
    }
    public void PreEffect(Entity target, PlayerEntity attacker, ItemStack stack, Hand handIn)
    {
        float VoodooPower = 1f;
        float VoodooChance = 0.1f;
        if (!attacker.abilities.isCreativeMode)
        {
            stack.damageItem(-1, attacker, (p_220045_0_) -> p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND));
        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemDarkArtsRing, attacker).isPresent())
        {
            VoodooPower += 1D;
        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemLuckRing, attacker).isPresent())
        {
            VoodooChance += 0.4D;
        }
        if (random.nextDouble() < VoodooChance)
        {
            VoodooPower += 1D;
        }
        Effect(target, attacker, stack, handIn, VoodooChance, VoodooPower);
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (playerIn.isSneaking()) {
            return new ActionResult<>(ActionResultType.FAIL, itemstack);
        }
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
                    PreEffect(worldServer.getEntityByUuid(uuid), playerIn, itemstack, handIn);
                }
            }
            return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
        }
        return new ActionResult<>(ActionResultType.FAIL, itemstack);
    }

    public void Bind(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand)
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
                if (worldServer.getEntityByUuid(uuid) != null)
                {
                    Objects.requireNonNull(worldServer.getEntityByUuid(uuid)).removeTag("cursed");
                }
            }
        }
        nbt.putUniqueId("entity", target.getUniqueID());
        if (!(target instanceof PlayerEntity))
        {
            target.addTag("cursed");
        }
    }
    public void AttemptBind(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand)
    {
        double chance = 0.1D;
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemDarkArtsRing, playerIn).isPresent())
        {
            chance += 0.25D;
        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemLuckRing, playerIn).isPresent())
        {
            chance += 0.25D;
        }
        if (!playerIn.velocityChanged)
        {
            chance += 0.15D;
        }
        if(playerIn.isInvisible() || random.nextDouble() < chance) // success!
        {
            Bind(stack, playerIn, target, hand);
            if (playerIn.world.isRemote())
            {
                playerIn.playSound(SoundEvents.ENTITY_ENDER_PEARL_THROW, 2, 4);
            }
            TranslationTextComponent successMessage = new TranslationTextComponent("you_bound_successfully " + target.getDisplayName().getFormattedText());
            playerIn.sendMessage(successMessage);

            //doStuff
        }
        else //fail :(
        {
            TranslationTextComponent failMessage = new TranslationTextComponent("you_failed_to_bind " + target.getDisplayName().getFormattedText() + ", they_know");
            playerIn.sendMessage(failMessage);
            if (target instanceof PlayerEntity)
            {
                TranslationTextComponent warnMessage = new TranslationTextComponent(playerIn.getDisplayName().getFormattedText() + " tried_to_bind");
                target.sendMessage(warnMessage);
            }
            if (playerIn.world.isRemote())
            {
                playerIn.playSound(SoundEvents.ENTITY_SPLASH_POTION_BREAK, 2, 4);
            }
        }
    }
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand)
    {
		playerIn.getCapability(PlayerProperties.PLAYER_MADE_DOLL).ifPresent(note ->
        {
            if (note.hasPlayerMadeDoll() < 2)
            {
                note.setPlayerMadeDoll(2);
            }
        });
        if (stack.hasTag())
        {
            CompoundNBT nbt = stack.getTag();
            assert nbt != null;
            UUID uuid = nbt.getUniqueId("entity");
            if (uuid != null)
            {
                if (uuid == target.getUniqueID())
                {
                    return super.itemInteractionForEntity(stack, playerIn, target, hand);
                }
            }
        }
        AttemptBind(stack, playerIn, target, hand);
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }
}