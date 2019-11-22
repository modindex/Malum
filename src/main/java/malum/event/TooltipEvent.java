package malum.event;

import malum.items.special.ItemModifier;
import malum.items.special.ItemWeaponAttunementCore;
import malum.registry.ModItems;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class TooltipEvent
{
    @SubscribeEvent
    public static void onAnvilUpdate(ItemTooltipEvent event)
    {

        ItemStack stack = event.getItemStack();
        if (stack.hasTag())
        {
            CompoundNBT nbt = stack.getTag();
            assert nbt != null;
            if (nbt.contains("ancient_hieroglyph_modifier"))
            {
                addStringToTooltip(TextFormatting.DARK_PURPLE + I18n.format("Runic ") + nbt.getInt("ancient_hieroglyph_modifier"), event.getToolTip());
            }
            if (nbt.contains("channeled_anomaly_modifier"))
            {
                addStringToTooltip(TextFormatting.DARK_PURPLE + I18n.format("Strange ") + nbt.getInt("channeled_anomaly_modifier"), event.getToolTip());
            }
            if (nbt.contains("tainted_heart_modifier"))
            {
                addStringToTooltip(TextFormatting.DARK_PURPLE + I18n.format("Tainted ") + nbt.getInt("tainted_heart_modifier"), event.getToolTip());
            }
        }
    }
    public static void addStringToTooltip(String s, List<ITextComponent> tooltip)
    {
        TranslationTextComponent string = new TranslationTextComponent(s);
        tooltip.add(string);
    }
}