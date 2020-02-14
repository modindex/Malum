package malum.items.gadgets;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemSpiritContainer extends Item
{
    private static final IItemPropertyGetter SPIRIT_GETTER = (stack, world, entity) ->
        stack.getTag() != null && stack.getTag().contains("spirit") && entity instanceof PlayerEntity ? 1 : 0;

    public ItemSpiritContainer(Properties builder)
    {
        super(builder);
        this.addPropertyOverride(new ResourceLocation("spirit"), SPIRIT_GETTER);
    }
}