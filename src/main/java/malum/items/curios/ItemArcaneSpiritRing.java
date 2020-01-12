package malum.items.curios;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.capability.ICurio;

public class ItemArcaneSpiritRing extends Item implements ICurio
{
    public ItemArcaneSpiritRing(Properties builder) {
        super(builder);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused) {
        return CapCurioItem.createProvider(new ICurio() {
            @Override
            public void playEquipSound(LivingEntity entityLivingBase) {
                entityLivingBase.world.playSound(null, entityLivingBase.getPosition(),
                        SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.NEUTRAL,
                        1.0f, 1.0f);
                entityLivingBase.world.playSound(null, entityLivingBase.getPosition(),
                    SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.NEUTRAL,
                    1.0f, 1.0f);
            }

            @Override
            public boolean canRightClickEquip() {

                return true;
            }
        });
    }
}