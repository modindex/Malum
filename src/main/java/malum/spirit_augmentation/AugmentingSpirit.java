package malum.spirit_augmentation;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import java.util.List;

public interface AugmentingSpirit
{
    String augmentTag();
    boolean isGrand();
    int maxAmount();
    void handleNBT(@Nonnull CompoundNBT nbt);
    String augmentDescription();
    void handleTooltip(@Nonnull CompoundNBT nbt, List<ITextComponent> tooltip);
    String spirit();
}
