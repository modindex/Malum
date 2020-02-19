package malum.items.curios;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.capability.ICurio;

public class ItemBerryRing extends Item implements ICurio
{
    public ItemBerryRing(Properties builder) {
        super(builder);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused) {
        return CapCurioItem.createProvider(new ICurio() {

            @Override
            public boolean canRightClickEquip() {
                return true;
            }
        });
    }
}