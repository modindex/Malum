package malum.items.curios;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.capability.ICurio;

public class ItemRottenBelt extends Item implements ICurio
{

    public ItemRottenBelt(Properties builder) {
        super(builder);
    }
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused) {
        return CapCurioItem.createProvider(new ICurio() {
            @Override
            public void playEquipSound(LivingEntity entityLivingBase) {
                entityLivingBase.world.playSound(null, entityLivingBase.getPosition(),
                        SoundEvents.ITEM_ARMOR_EQUIP_GOLD, SoundCategory.NEUTRAL,
                        1.0f, 1.0f);
            }
            @Override
            public boolean canRightClickEquip() {

                return true;
            }
        });
    }
}