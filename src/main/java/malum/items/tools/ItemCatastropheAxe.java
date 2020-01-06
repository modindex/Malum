package malum.items.tools;

import malum.registry.ModItemGroups;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ToolType;

public class ItemCatastropheAxe extends AxeItem
{
    public ItemCatastropheAxe(IItemTier material, int damage, float speed, Properties properties) {
        super(material, damage, speed, properties.addToolType(ToolType.AXE, material.getHarvestLevel()));
    }
}


