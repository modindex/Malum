package malum.event;

import malum.items.curios.*;
import malum.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosAPI;

import java.util.Objects;
import java.util.UUID;

import static net.minecraft.entity.LivingEntity.SWIM_SPEED;

@Mod.EventBusSubscriber
public class PlayerEventHandler
{
    private static final UUID ID = UUID.fromString("6d3be89e-37e6-453f-8654-2fd37d85b2ab");
    @SubscribeEvent
    public static void Tick (TickEvent.PlayerTickEvent event)
    {
        LivingEntity entityLivingBase = event.player;
        if (entityLivingBase.isInWater())
        {
            IAttributeInstance attributeInstance = entityLivingBase.getAttributes().getAttributeInstance(SWIM_SPEED);
            assert attributeInstance != null;
            double Y = entityLivingBase.getPosition().getY();
            double ValueofY = 110 - Y;
            double swimSpeed = 0.1D;
            if (ValueofY > 0)
            {
                swimSpeed += (float) (ValueofY / 80);
            }
            final AttributeModifier SWIM_SPEED_INCREASE = new AttributeModifier(ID, "Swim Speed modifier", swimSpeed, AttributeModifier.Operation.ADDITION);

            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemWaterNecklace, entityLivingBase).isPresent())
            {
                if (!entityLivingBase.getEntityWorld().isRemote && entityLivingBase.ticksExisted % 19 == 0)
                {
                    if (attributeInstance.getModifier(ID) != null)
                    {
                        attributeInstance.removeModifier(SWIM_SPEED_INCREASE);
                    }
                }
                if (attributeInstance.getModifier(ID) == null)
                {
                    attributeInstance.applyModifier(SWIM_SPEED_INCREASE);
                }
            }
            else
            {
                if (attributeInstance.getModifier(ID) != null)
                {
                    attributeInstance.removeModifier(SWIM_SPEED_INCREASE);
                }
            }
        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemThornsBelt, entityLivingBase).isPresent())
        {
        }
    }
    @SubscribeEvent
    public static void Hurt(LivingHurtEvent event)
    {
        LivingEntity entityLivingBase = event.getEntityLiving();
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemThornsBelt, entityLivingBase).isPresent())
        {
            float damage = 1f;
            float health = entityLivingBase.getHealth();
            float value = 15 - health;
            if (value > 0)
            {
                damage += value;
            }
            Objects.requireNonNull(event.getSource().getTrueSource()).attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity) entityLivingBase), damage);

        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemHealingBelt, entityLivingBase).isPresent())
        {
            float maxhealth = entityLivingBase.getMaxHealth();
            float health = entityLivingBase.getHealth();
            float modifier = 0.5f;
            float value = (maxhealth - health) * modifier;
            entityLivingBase.setAbsorptionAmount(entityLivingBase.getAbsorptionAmount() + value);
        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemNetherNecklace, entityLivingBase).isPresent() || CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemWitherNecklace, entityLivingBase).isPresent())
        {
            if (event.getSource() == DamageSource.ON_FIRE || event.getSource() == DamageSource.IN_FIRE)
            {
                entityLivingBase.playSound(SoundEvents.ENTITY_BLAZE_BURN, 0.6f, 1.4f);
                event.setAmount(event.getAmount() / 2);
            }
            if (entityLivingBase.isInLava())
            {
                entityLivingBase.playSound(SoundEvents.ENTITY_BLAZE_HURT, 0.8f, 1.2f);
                event.setAmount(event.getAmount() / 3);

            }
        }
        if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemWitherNecklace, entityLivingBase).isPresent())
        {
            entityLivingBase.playSound(SoundEvents.ENTITY_WITHER_HURT, 0.5f, 1.5f);
            entityLivingBase.playSound(SoundEvents.ENTITY_WITHER_SKELETON_HURT, 0.5f, 1.5f);
            event.setAmount(event.getAmount() * 0.85f);

        }
    }
    @SubscribeEvent
    public static void Death(LivingDeathEvent event)
    {
        if (event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof LivingEntity)
        {
            LivingEntity entityLivingBase = (LivingEntity) event.getSource().getTrueSource();
            if (entityLivingBase != null)
            {
                Hand hand = entityLivingBase.swingingHand;
                if (hand != null)
                {
                    ItemStack stack = entityLivingBase.getHeldItem(hand);
                    if (stack.getItem() == Items.WOODEN_SWORD)
                    {
                        Entity target = event.getEntityLiving();
                        if (target instanceof WitherSkeletonEntity)
                        {
                            if (((WitherSkeletonEntity) target).getHealth() <= 0)
                            {
                                stack.setDamage(entityLivingBase.getHeldItem(hand).getMaxDamage());
                                entityLivingBase.setHeldItem(hand, ModItems.withering_rapier.getDefaultInstance());
                            }
                        }
                    }

                    CompoundNBT nbt = stack.getTag();
                    if (nbt != null)
                    {
                        if (nbt.get("swordPower") != null)
                        {
                            int swordPower = nbt.getInt("swordPower");
                            LivingEntity target = event.getEntityLiving();
                            if (target.getHealth() <= 0)
                            {
                                ItemEntity spawnedItem = (new ItemEntity(target.world, target.posX, target.posY, target.posZ, new ItemStack(ModItems.evil_spirit)));
                                ItemStack spawnedStack = spawnedItem.getItem();
                                if (spawnedStack.getTag() == null)
                                {
                                    spawnedStack.setTag(new CompoundNBT());
                                }
                                CompoundNBT spawnedNBT = spawnedStack.getTag();
                                assert spawnedNBT != null;

                                spawnedNBT.putInt("swordPower", swordPower);
                                if (swordPower >= 1)
                                {
                                    spawnedNBT.putString("entityDisplayName", target.getDisplayName().getString());
                                }
                                if (swordPower >= 2)
                                {
                                    spawnedNBT.putInt("dimensionID", entityLivingBase.dimension.getId());
                                }
                                if (swordPower >= 3)
                                {
                                    spawnedNBT.putString("biomeDisplayName", entityLivingBase.world.getBiome(entityLivingBase.getPosition()).getDisplayName().getString());

                                }
                                if (swordPower >= 4)
                                {
                                    //   spawnedNBT.putString("entityDisplayName", target.getDisplayName().getString());
                                }
                                target.getEntityWorld().addEntity(spawnedItem);
                            }
                        }
                    }
                }
            }
        }
    }
}