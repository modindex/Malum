package malum.recipes;

import malum.registry.ModItems;
import malum.registry.ModRecipes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class CraftingRecipes
{
  public static void initRecipes()
  {
      ArrayList<Item> ingredients4 = new ArrayList<>();
      ingredients4.add(ModItems.end_forged_ingot);
      ingredients4.add(ModItems.end_forged_ingot);
      ingredients4.add(ModItems.end_forged_ingot);
      ingredients4.add(ModItems.end_forged_ingot);
      ingredients4.add(Items.ENDER_PEARL);
      ModRecipes.addCraftingRecipe(new CraftingRecipe(ingredients4, ModItems.catastrophe_shoes));

      ArrayList<Item> ingredients3 = new ArrayList<>();
      ingredients3.add(ModItems.end_forged_ingot);
      ingredients3.add(ModItems.end_forged_ingot);
      ingredients3.add(ModItems.end_forged_ingot);
      ingredients3.add(ModItems.end_forged_ingot);
      ingredients3.add(ModItems.end_forged_ingot);
      ingredients3.add(Items.ENDER_PEARL);
      ModRecipes.addCraftingRecipe(new CraftingRecipe(ingredients3, ModItems.catastrophe_hood));

      ArrayList<Item> ingredients2 = new ArrayList<>();
      ingredients2.add(ModItems.end_forged_ingot);
      ingredients2.add(ModItems.end_forged_ingot);
      ingredients2.add(ModItems.end_forged_ingot);
      ingredients2.add(ModItems.end_forged_ingot);
      ingredients2.add(ModItems.end_forged_ingot);
      ingredients2.add(ModItems.end_forged_ingot);
      ingredients2.add(ModItems.end_forged_ingot);
      ingredients2.add(ModItems.end_forged_ingot);
      ingredients2.add(Items.ENDER_PEARL);
      ingredients2.add(Items.ENDER_PEARL);
      ModRecipes.addCraftingRecipe(new CraftingRecipe(ingredients2, ModItems.catastrophe_chestplate));

      ArrayList<Item> ingredients1 = new ArrayList<>();
      ingredients1.add(ModItems.end_forged_ingot);
      ingredients1.add(ModItems.end_forged_ingot);
      ingredients1.add(ModItems.end_forged_ingot);
      ingredients1.add(ModItems.end_forged_ingot);
      ingredients1.add(ModItems.end_forged_ingot);
      ingredients1.add(ModItems.end_forged_ingot);
      ingredients1.add(ModItems.end_forged_ingot);
      ingredients1.add(Items.ENDER_PEARL);
      ModRecipes.addCraftingRecipe(new CraftingRecipe(ingredients1, ModItems.catastrophe_leggings));

  }
}
