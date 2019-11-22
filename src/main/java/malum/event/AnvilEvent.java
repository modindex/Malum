package malum.event;

import malum.items.curios.ItemDarkArtsRing;
import malum.items.special.ItemModifier;
import malum.items.special.ItemWeaponAttunementCore;
import malum.registry.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AnvilEvent
{
    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event)
    {
        if (event.getLeft().isEmpty() || event.getRight().isEmpty())
        {      	        
        	return;
        }
        ItemStack item = event.getLeft();
        ItemStack modifier = event.getRight();
        if (!item.isEmpty() && modifier.getItem() instanceof ItemWeaponAttunementCore && !modifier.isEmpty())
        {
            ItemStack innercopy = item.copy();
            if (!innercopy.hasTag())
            {
                innercopy.setTag(new CompoundNBT());
            }
            CompoundNBT nbt = innercopy.getTag();
            if (nbt != null)
            {
                int tier = -1;
                if (modifier.getItem() == ModItems.weapon_attunement_core)
                {
                    tier = 0;
                }
                if (modifier.getItem() == ModItems.weapon_attunement_core_strong)
                {
                    tier = 1;
                }
                if (tier == -1)
                {
                    return;
                }
                nbt.putInt("swordPower", tier);
                event.setCost(4);
                event.setOutput(innercopy);
            }
        }
        if (!item.isEmpty() && modifier.getItem() instanceof ItemModifier && !modifier.isEmpty())
        {
            ItemStack innercopy = item.copy();
            if (!innercopy.hasTag())
            {
                innercopy.setTag(new CompoundNBT());
            }
            CompoundNBT nbt = innercopy.getTag();
            if (nbt != null)
            {
                if (modifier.getItem() == ModItems.channeled_anomaly_modifier)
                {
                    int power = 1;
                    if (nbt.contains("channeled_anomaly_modifier"))
                    {
                        power = nbt.getInt("channeled_anomaly_modifier") + 1;
                    }
                    nbt.putInt("channeled_anomaly_modifier", power);
                }
                if (modifier.getItem() == ModItems.tainted_heart_modifier)
                {
                    int power = 1;
                    if (nbt.contains("tainted_heart_modifier"))
                    {
                        power = nbt.getInt("tainted_heart_modifier") + 1;
                    }
                    nbt.putInt("tainted_heart_modifier", power);
                }
                if (modifier.getItem() == ModItems.ancient_hieroglyph_modifier)
                {
                    int power = 1;
                    if (nbt.contains("ancient_hieroglyph_modifier"))
                    {
                        power = nbt.getInt("ancient_hieroglyph_modifier") + 1;
                    }
                    nbt.putInt("ancient_hieroglyph_modifier", power);
                }
                event.setCost(4);
                event.setOutput(innercopy);
            }
        }
    }
}