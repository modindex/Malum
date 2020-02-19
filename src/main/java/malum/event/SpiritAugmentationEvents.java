package malum.event;

import malum.MalumMod;
import malum.items.armor.ItemArmorSoulCrystal;
import malum.items.armor.ItemArmorSoulSteel;
import malum.registry.ModRecipes;
import malum.spirit_augmentation.SpiritAugmentationData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class SpiritAugmentationEvents
{
    @SubscribeEvent
    public static void addTooltip(ItemTooltipEvent event)
    {
        if (event.getEntityPlayer() != null)
        {
            if (event.getEntityPlayer().world.isRemote())
            {
                for (SpiritAugmentationData augmentationData : ModRecipes.spiritAugmentationData)
                {
                    if (augmentationData != null)
                    {
                        if (augmentationData.getItem() == event.getItemStack().getItem())
                        {
                            if (event.getItemStack().getTag() != null)
                            {
                                if (event.getItemStack().getTag().contains(augmentationData.getSpirit().augmentTag()))
                                {
                                    augmentationData.getSpirit().handleTooltip(event.getItemStack().getTag(), event.getToolTip());
                                }
                            }
                        }
                    }
                }
                if (event.getItemStack().getItem() instanceof ItemArmorSoulCrystal || event.getItemStack().getItem() instanceof ItemArmorSoulSteel)
                {
                    int maxAugments = (event.getItemStack().getItem() instanceof ItemArmorSoulCrystal) ? 20 : 30;

                    if (event.getItemStack().getTag() != null)
                    {
                        for (SpiritAugmentationData augmentationData : ModRecipes.spiritAugmentationData)
                        {
                            if (augmentationData != null)
                            {
                                if (augmentationData.getItem() == event.getItemStack().getItem())
                                {
                                    if (event.getItemStack().getTag().contains(augmentationData.getSpirit().augmentTag()))
                                    {
                                        maxAugments -= event.getItemStack().getTag().getInt(augmentationData.getSpirit().augmentTag());
                                    }

                                }
                            }
                        }
                        StringTextComponent textComponent = new StringTextComponent(maxAugments + "/" + ((event.getItemStack().getItem() instanceof ItemArmorSoulCrystal) ? 20 : 30) + " Augment slots left");
                        event.getToolTip().add(textComponent);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void handleDrownedEffect(LivingAttackEvent event)
    {
        if (event.getEntityLiving() instanceof PlayerEntity)
        {
            if (event.getSource() == DamageSource.DROWN)
            {
                PlayerEntity playerEntity = (PlayerEntity) event.getEntityLiving();
                if (playerEntity.isInWater())
                {
                    int drownedAugmentStrenght = SpiritAugmentationData.getAugmentAmountFromArmor(playerEntity.inventory.armorInventory, MalumMod.drowned_armor_augment);
                    if (drownedAugmentStrenght != 0)
                    {
                        playerEntity.setAir(playerEntity.getAir() + 15 * drownedAugmentStrenght);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void handleBlazeEffect(LivingDamageEvent event)
    {
        if (event.getEntityLiving() instanceof PlayerEntity)
        {
            if (event.getSource().isFireDamage())
            {
                PlayerEntity playerEntity = (PlayerEntity) event.getEntityLiving();
                int blazeAugmentStrenght = SpiritAugmentationData.getAugmentAmountFromArmor(playerEntity.inventory.armorInventory, MalumMod.blaze_armor_augment);
                if (blazeAugmentStrenght != 0)
                {
                    if (MathHelper.nextInt(new Random(), 0, 99) <= blazeAugmentStrenght * 5)
                    {
                        playerEntity.extinguish();
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void handleVindicatorEffect(LivingHurtEvent event)
    {
        if (event.getSource().getTrueSource() instanceof PlayerEntity)
        {
            PlayerEntity playerEntity = (PlayerEntity) event.getSource().getTrueSource();
            if (playerEntity.getHeldItemMainhand().getItem() instanceof AxeItem)
            {
                int vindicatorAugmentStrenght = SpiritAugmentationData.getAugmentAmountFromArmor(playerEntity.inventory.armorInventory, MalumMod.vindicator_armor_augment);
                event.setAmount(event.getAmount() * (1f + vindicatorAugmentStrenght / 100f));
            }
        }
    }
    @SubscribeEvent
    public static void handleRavagerEffect(LivingHurtEvent event)
    {
        if (event.getSource().getTrueSource() instanceof PlayerEntity)
        {
            PlayerEntity playerEntity = (PlayerEntity) event.getSource().getTrueSource();
            int ravagerAugmentStrenght = SpiritAugmentationData.getAugmentAmountFromArmor(playerEntity.inventory.armorInventory, MalumMod.ravager_armor_augment);
            if (playerEntity.getHealth() <= playerEntity.getMaxHealth() * 0.25f)
            {
                event.setAmount(event.getAmount() + event.getAmount() * ravagerAugmentStrenght / 100);
            }
        }
    }

    @SubscribeEvent
    public static void handlePillagerEffect(LivingDamageEvent event)
    {
        if (event.getSource().getImmediateSource() instanceof ArrowEntity)
        {
            if (event.getSource().getTrueSource() instanceof PlayerEntity)
            {
                PlayerEntity playerEntity = (PlayerEntity) event.getSource().getTrueSource();
                int pillagerAugmentStrenght = SpiritAugmentationData.getAugmentAmountFromArmor(playerEntity.inventory.armorInventory, MalumMod.pillager_armor_augment);
                if (pillagerAugmentStrenght != 0)
                {
                    if (MathHelper.nextInt(new Random(), 0, 99) <= pillagerAugmentStrenght * 2)
                    {
                        if (playerEntity.getHeldItemMainhand().getItem() instanceof CrossbowItem)
                        {
                            ItemStack crossbow = playerEntity.getHeldItemMainhand();
                            if (crossbow.getTag() != null)
                            {
                                ListNBT listnbt = new ListNBT();
                                crossbow.getTag().putBoolean("Charged", true);
                                CompoundNBT compoundnbt1 = new CompoundNBT();
                                Items.ARROW.getDefaultInstance().write(compoundnbt1);
                                listnbt.add(compoundnbt1);
                                crossbow.getTag().put("ChargedProjectiles", listnbt);
                            }
                        }
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void handleWitchEffect(PotionEvent.PotionExpiryEvent event)
    {
        if (event.getEntityLiving() instanceof PlayerEntity)
        {
            if (event.getPotionEffect() != null)
            {
                if (event.getPotionEffect().getPotion().isBeneficial())
                {
                    PlayerEntity playerEntity = (PlayerEntity) event.getEntityLiving();
                    int witchAugmentStrenght = SpiritAugmentationData.getAugmentAmountFromArmor(playerEntity.inventory.armorInventory, MalumMod.witch_armor_augment);
                    if (witchAugmentStrenght != 0)
                    {
                        int a = MathHelper.nextInt(new Random(), 0, 99);
                        MalumMod.LOGGER.info(a);
                        if (a <= witchAugmentStrenght * 2)
                        {
                            MalumMod.LOGGER.info("success");
                            EffectInstance instance = new EffectInstance(event.getPotionEffect().getPotion(), 200, event.getPotionEffect().getAmplifier());
                            playerEntity.removeActivePotionEffect(event.getPotionEffect().getPotion());
                            playerEntity.addPotionEffect(instance);
                        }
                    }
                }
            }
        }
    }

}