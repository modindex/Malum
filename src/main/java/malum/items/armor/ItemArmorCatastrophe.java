package malum.items.armor;

import malum.items.curios.ItemArcaneSightRing;
import malum.models.ModelArmorCatastrophe;
import malum.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.CuriosAPI;

import javax.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.List;

public class ItemArmorCatastrophe extends ArmorItem {

    protected String name;

    public ItemArmorCatastrophe(IArmorMaterial materialIn, EquipmentSlotType slot, Item.Properties builder) {
        super(materialIn, slot, builder);
        this.addPropertyOverride(new ResourceLocation("dimension"), (p_210309_0_, p_210309_1_, p_210309_2_) -> {
            return p_210309_2_ != null && p_210309_2_.dimension == DimensionType.THE_END ? 1.0F : 0.0F;
        });
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    @Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type)
    {
        if (entity.dimension == DimensionType.THE_END)
        {
            return "malum:textures/armor/catastrophe_armor_glow.png";
        }
        return "malum:textures/armor/catastrophe_armor.png";
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (player != null)
        {

            ItemStack[] allPieces = new ItemStack[]{
                new ItemStack(ModItems.catastrophe_hood),
                new ItemStack(ModItems.catastrophe_chestplate),
                new ItemStack(ModItems.catastrophe_leggings),
                new ItemStack(ModItems.catastrophe_shoes)
            };
            int pieces = 0;
            if (Screen.hasShiftDown())
            {
                addStringToTooltip(I18n.format("set_bonus_tooltip: "), tooltip);
            }
            else
            {
                addStringToTooltip(I18n.format("sneak_info_tooltip"), tooltip);
            }

        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
    public void addStringToTooltip(String s, List<ITextComponent> tooltip) {
        TranslationTextComponent string = new TranslationTextComponent(s);
        tooltip.add(string);
    }
    @OnlyIn(Dist.CLIENT)
    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) new ModelArmorCatastrophe(slot);
    }
}
