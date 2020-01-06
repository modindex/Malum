package malum.items.tools;

import malum.registry.ModItemGroups;
import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ToolType;

public class ItemCatastropheSword extends SwordItem
{
    public ItemCatastropheSword(IItemTier material, int attackDamage, float attackSpeed, Properties properties) {
        super(material, attackDamage, attackSpeed, properties);
    }
}

