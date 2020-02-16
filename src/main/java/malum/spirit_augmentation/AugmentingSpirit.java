package malum.spirit_augmentation;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import java.util.List;

public interface AugmentingSpirit
{
    String augmentTag();
    public void handleNBT(CompoundNBT nbt, ItemStack stack);
    String auagmentDescription();
    String augmentTooltip(CompoundNBT nbt);
    List<String> acceptableSpirits();
}
