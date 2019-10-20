package malum.event;

import com.sun.jna.StringArray;
import malum.items.curios.*;
import malum.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.IntArray;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosAPI;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.UUID;

import static net.minecraft.entity.LivingEntity.SWIM_SPEED;

@Mod.EventBusSubscriber
public class SpiritDropEvent
{
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

                                if (swordPower >= 0)
                                {
                                    spawnedNBT.putString("eNam", target.getType().getRegistryType().toString());
                                }
                                if (swordPower >= 1)
                                {
                                    spawnedNBT.putString("bCat", target.world.getBiome(target.getPosition()).getCategory().getName());
                                }
                                if (swordPower >= 2) //player variable stuff
                                {
                                }

                                GenerateLatinName(spawnedItem.getItem(), target, swordPower);
                                target.getEntityWorld().addEntity(spawnedItem);
                            }
                        }
                    }
                }
            }
        }
    }
    public static void GenerateLatinName(ItemStack stack, LivingEntity entity, int strenght)
    {
        String enemyName = "";
        if (entity instanceof ZombieEntity)
        {
            enemyName = "Corpus";
        }
        if (entity instanceof EndermanEntity)
        {
            enemyName = "Magicae";
        }
        if (entity instanceof CreeperEntity)
        {
            enemyName = "Praemium";
        }
        if (entity instanceof SkeletonEntity)
        {
            enemyName = "Osseus";
        }
        String biomeName = "";
        String[] spiritName = {" Weak Spirit", " Spirit", " Grand Spirit"};
        StringTextComponent itemFinalName = new StringTextComponent(enemyName + biomeName + spiritName[strenght]);
        stack.setDisplayName(itemFinalName);
    }
}