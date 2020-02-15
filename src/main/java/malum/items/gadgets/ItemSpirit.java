package malum.items.gadgets;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemSpirit extends Item
{
    private static final IItemPropertyGetter TYPE_GETTER = (stack, world, entity) ->
        stack.getTag() != null && stack.getTag().contains("type") ? stack.getTag().getInt("type") : 0;

    public ItemSpirit(Properties builder)
    {
        super(builder);
        this.addPropertyOverride(new ResourceLocation("b"), TYPE_GETTER);
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