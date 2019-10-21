package malum.items.tools;

import malum.registry.ModItemGroups;
import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ToolType;

public class ItemCatastrophePickaxe extends PickaxeItem
{
    public ItemCatastrophePickaxe(IItemTier material, int damage, float speed, Properties properties)
    {
        super(material, damage, speed, properties.addToolType(ToolType.PICKAXE, material.getHarvestLevel()));

        this.addPropertyOverride(new ResourceLocation("dimension"), (p_210309_0_, p_210309_1_, p_210309_2_) -> {
            return p_210309_2_ != null && p_210309_2_.dimension == DimensionType.THE_END ? 1.0F : 0.0F;
        });
    }
}

