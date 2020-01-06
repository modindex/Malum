package malum.recipes;

import malum.registry.ModBlocks;
import malum.registry.ModRecipes;
import net.minecraft.item.Items;

public class ResourceFormingRecipes
{
    public static void initRecipes()
    {
        ModRecipes.addResourceFormingRecipe(new ResourceFormingRecipe(ModBlocks.resource_crystal_iron, Items.IRON_ORE));
        ModRecipes.addResourceFormingRecipe(new ResourceFormingRecipe(ModBlocks.resource_crystal_gold, Items.GOLD_ORE));
        ModRecipes.addResourceFormingRecipe(new ResourceFormingRecipe(ModBlocks.resource_crystal_diamond, Items.DIAMOND_ORE));
        ModRecipes.addResourceFormingRecipe(new ResourceFormingRecipe(ModBlocks.resource_crystal_emerald, Items.EMERALD_ORE));
        ModRecipes.addResourceFormingRecipe(new ResourceFormingRecipe(ModBlocks.resource_crystal_coal, Items.COAL_ORE));
        ModRecipes.addResourceFormingRecipe(new ResourceFormingRecipe(ModBlocks.resource_crystal_lapis, Items.LAPIS_ORE));
        ModRecipes.addResourceFormingRecipe(new ResourceFormingRecipe(ModBlocks.resource_crystal_redstone, Items.REDSTONE_ORE));
        ModRecipes.addResourceFormingRecipe(new ResourceFormingRecipe(ModBlocks.resource_crystal_soul, Items.STICK));
    }
}
