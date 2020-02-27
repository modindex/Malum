package kittykitcatcat.malum.recipes;

import kittykitcatcat.malum.registry.ModRecipes;
import net.minecraft.item.Items;

public class ResourceFormingRecipes
{
    public static void initRecipes()
    {
        ModRecipes.addResourceFormingRecipe(new ResourceFormingRecipe(Items.IRON_ORE, Items.IRON_INGOT, 161, 251, 232));
    }
}