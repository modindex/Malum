package malum.items.tools;

import malum.registry.ModItemGroups;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ToolType;

public class ItemCatastropheShovel extends ShovelItem
{
    public ItemCatastropheShovel(IItemTier material,int damage, float speed, Properties properties) {
        super(material, damage, speed, properties.addToolType(ToolType.SHOVEL, material.getHarvestLevel()));
    }
}