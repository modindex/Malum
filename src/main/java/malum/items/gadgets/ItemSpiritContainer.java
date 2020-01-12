package malum.items.gadgets;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public class ItemSpiritContainer extends Item
{
    private static final IItemPropertyGetter SPIRIT_GETTER = (stack, world, entity) ->
        stack.getTag() != null && Objects.requireNonNull(stack.getTag()).getString("spirit") != null && entity instanceof PlayerEntity ? 1 : 0;

    public ItemSpiritContainer(Properties builder)
    {
        super(builder);
        this.addPropertyOverride(new ResourceLocation("spirit"), SPIRIT_GETTER);
    }
}