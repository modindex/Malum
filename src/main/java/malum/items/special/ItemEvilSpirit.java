package malum.items.special;

import malum.items.curios.ItemArcaneSightRing;
import malum.items.curios.ItemDarkArtsRing;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import top.theillusivec4.curios.api.CuriosAPI;

import javax.annotation.Nullable;
import java.util.List;

public class ItemEvilSpirit extends Item
{
  public ItemEvilSpirit(Item.Properties properties)
  {
    super(properties);
  }
  @OnlyIn(Dist.CLIENT)
  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
  {
      ClientPlayerEntity player = Minecraft.getInstance().player;

      if (player != null) {
          if (CuriosAPI.getCurioEquipped(stack1 -> stack1.getItem() instanceof ItemArcaneSightRing, player).isPresent()) {
              if (Screen.hasShiftDown()) {
                  CompoundNBT tag = stack.getTag();
                  if (tag != null) {
                      String entityDisplayName = tag.getString("soul");
                      String biomeDisplayName = tag.getString("grave");
                      if (tag.getString("soul") != null) {
                          addStringToTooltip(TextFormatting.ITALIC + I18n.format("entity_type_tooltip: ") + entityDisplayName, tooltip);
                      }
                      if (tag.getString("grave") != null) {
                          addStringToTooltip(TextFormatting.ITALIC + I18n.format("biome_display_name_tooltip: ") + biomeDisplayName, tooltip);
                      }

                  }
              } else {
                  addStringToTooltip(I18n.format("sneak_info_tooltip"), tooltip);
              }
          }
      }
    super.addInformation(stack, worldIn, tooltip, flagIn);
  }
  public void addStringToTooltip(String s, List<ITextComponent> tooltip) {
    TranslationTextComponent string = new TranslationTextComponent(s);
    tooltip.add(string);
  }
}