package kittykitcatcat.malum.items.gadgets;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemSpiritContainer extends Item
{
    private static final IItemPropertyGetter SPIRIT_GETTER = (stack, world, entity) ->
        stack.getTag() != null && stack.getTag().contains("spirit") && entity instanceof PlayerEntity ? 1 : 0;

    public ItemSpiritContainer(Properties builder)
    {
        super(builder);
        this.addPropertyOverride(new ResourceLocation("spirit"), SPIRIT_GETTER);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        if (stack.getTag() != null && stack.getTag().contains("spirit"))
        {
            tooltip.add(component(stack.getTag().getString("spirit")));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public StringTextComponent component(String string)
    {
        return new StringTextComponent(string);
    }
}