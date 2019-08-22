package malum.items.special;

import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
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

public class ItemWeaponAttunementCore extends Item
{
  public ItemWeaponAttunementCore(Item.Properties properties)
  {
    super(properties);
  }
  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
  {
      addStringToTooltip(I18n.format("weapon_attunement_core_tooltip"), tooltip);
    super.addInformation(stack, worldIn, tooltip, flagIn);
  }
  public void addStringToTooltip(String s, List<ITextComponent> tooltip) {
    TranslationTextComponent string = new TranslationTextComponent(s);
    tooltip.add(string);
  }
}