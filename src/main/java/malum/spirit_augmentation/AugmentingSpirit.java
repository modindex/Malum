package malum.spirit_augmentation;

import net.minecraft.nbt.CompoundNBT;

import java.util.List;

public interface AugmentingSpirit
{
    String augmentTag();
    public void handleNBT(CompoundNBT nbt);
    String auagmentDescription();
    String augmentTooltip(CompoundNBT nbt);
    List<String> acceptableSpirits();
}
