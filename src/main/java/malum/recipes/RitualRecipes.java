package malum.recipes;

import malum.registry.ModBlocks;
import malum.registry.ModRecipes;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.List;

public class RitualRecipes {

  public static void initRecipes()
  {
      ArrayList<Item> ingredients1 = new ArrayList<>();
      ingredients1.add(Items.STICKY_PISTON);
      ingredients1.add(Items.EGG);
      ModRecipes.addRitualRecipe(new RitualRecipe(ingredients1, 1, "boom"));

      ArrayList<Item> ingredients0 = new ArrayList<>();
      ingredients0.add(Items.EGG);
      ingredients0.add(Items.STICKY_PISTON);
      ModRecipes.addRitualRecipe(new RitualRecipe(ingredients0, 0, "jump"));
  }
}
