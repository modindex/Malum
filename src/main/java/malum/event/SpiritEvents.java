package malum.event;

import malum.items.curios.ItemSpiritRing;
import malum.items.gadgets.ItemSpiritContainer;
import malum.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosAPI;

import java.util.Objects;

@Mod.EventBusSubscriber
public class SpiritEvents
{

    @SubscribeEvent
    public static void SpiritHarvest(LivingDeathEvent event)
    {
        LivingEntity attacked = event.getEntityLiving();
        String tag = Objects.requireNonNull(attacked.getType().getRegistryName()).toString();
        Entity attacking = event.getSource().getTrueSource();
        if (attacking instanceof PlayerEntity)
        {
            PlayerEntity attackingPlayer = (PlayerEntity) attacking;
            if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemSpiritRing, attackingPlayer).isPresent()
                || attackingPlayer.getHeldItem((attackingPlayer).swingingHand).getItem() == ModItems.sacrificial_dagger)
            {
                for (ItemStack stack : attackingPlayer.inventory.mainInventory)
                {
                    if (stack.getItem() instanceof ItemSpiritContainer)
                    {
                        CompoundNBT nbt = stack.getOrCreateTag();
                        if (stack.getCount() == 1)
                        {
                            if (!nbt.contains("spirit"))
                            {
                                nbt.putString("spirit", tag);
                                return;
                            }
                        }
                        else
                        {
                            if (!nbt.contains("spirit"))
                            {
                                stack.shrink(1);
                                ItemStack newStack = stack.getItem().getDefaultInstance();
                                CompoundNBT newNbt = newStack.getOrCreateTag();
                                newNbt.putString("spirit", tag);
                                attackingPlayer.inventory.addItemStackToInventory(newStack);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

}