package malum.recipes;

import malum.registry.ModItems;
import malum.registry.ModRecipes;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class SpiritInfusionRecipes
{

    public static void initRecipes()
    {
        ArrayList<String> spirits1 = new ArrayList<>();
        spirits1.add("minecraft:zombie");
        spirits1.add("minecraft:creeper");
        spirits1.add("minecraft:skeleton");
        ModRecipes.addSpiritInfusionRecipe(new SpiritInfusionRecipe(Items.GLOWSTONE_DUST, ModItems.soul_dust, spirits1));
    }
}
