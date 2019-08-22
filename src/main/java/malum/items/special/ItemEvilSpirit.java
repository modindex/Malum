package malum.items.special;

import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent;

import javax.annotation.Nullable;
import java.util.List;

public class ItemEvilSpirit extends Item
{
  public ItemEvilSpirit(Item.Properties properties)
  {
    super(properties);
  }

  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
  {
    if (Screen.hasShiftDown())
    {
      CompoundNBT tag = stack.getTag();
      if (tag != null) {
        int swordPower = tag.getInt("swordPower");
        String entityDisplayName = tag.getString("entityDisplayName");
        int dimensionID = tag.getInt("dimensionID");
        String biomeDisplayName = tag.getString("biomeDisplayName");

        String[] swordPowerString = new String[]
                {
                        "sword_power_none", //0
                        "sword_power_weak",
                        "sword_power_avarge",
                        "sword_power_strong",
                        "sword_power_uncontrorable" //4
                };
        //main aspects
        addStringToTooltip(TextFormatting.GRAY + I18n.format("sword_power_tooltip: ") + swordPowerString[swordPower], tooltip);
        addStringToTooltip(I18n.format("entity_type_tooltip: ") + entityDisplayName, tooltip);

      }
    }
    else
    {
      addStringToTooltip(I18n.format("sneak_info_tooltip"), tooltip);
    }
    super.addInformation(stack, worldIn, tooltip, flagIn);
  }
  public void addStringToTooltip(String s, List<ITextComponent> tooltip) {
    TranslationTextComponent string = new TranslationTextComponent(s);
    tooltip.add(string);
  }
}