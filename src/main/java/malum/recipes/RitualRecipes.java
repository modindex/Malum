package malum.recipes;

import malum.registry.ModBlocks;
import malum.registry.ModRecipes;
import malum.rituals.TransmutationRitualEffect;
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
      ArrayList<Item> ingredients2 = new ArrayList<>();
      ingredients2.add(Items.GOLD_INGOT);
      ingredients2.add(Items.QUARTZ);
      ingredients2.add(Items.QUARTZ);
      ingredients2.add(Items.REDSTONE);
      ingredients2.add(Items.REDSTONE);
      ModRecipes.addRitualRecipe(new RitualRecipe(ingredients2, new TransmutationRitualEffect(), "position"));
  }
}
