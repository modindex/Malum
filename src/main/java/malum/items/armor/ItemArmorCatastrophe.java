package malum.items.armor;

import malum.models.ModelArmorCatastrophe;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

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
    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) new ModelArmorCatastrophe(slot);
    }
}
