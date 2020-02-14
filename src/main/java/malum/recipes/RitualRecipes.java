package malum.recipes;

import malum.registry.ModRecipes;
import malum.rituals.CrystalGrowthRitualEffect;
import malum.rituals.TransmutationRitualEffect;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class RitualRecipes {

  public static void initRecipes()
  {
      ArrayList<Item> transmutationItems = new ArrayList<>();
      transmutationItems.add(Items.GOLD_INGOT);
      transmutationItems.add(Items.QUARTZ);
      transmutationItems.add(Items.QUARTZ);
      transmutationItems.add(Items.REDSTONE);
      transmutationItems.add(Items.REDSTONE);
      ModRecipes.addRitualRecipe(new RitualRecipe(transmutationItems, new TransmutationRitualEffect()));

      ArrayList<Item> crystalGrowthItems = new ArrayList<>();
      crystalGrowthItems.add(Items.BLAZE_POWDER);
      crystalGrowthItems.add(Items.NETHERRACK);
      crystalGrowthItems.add(Items.BLAZE_POWDER);
      crystalGrowthItems.add(Items.IRON_INGOT);
      crystalGrowthItems.add(Items.BLAZE_POWDER);
      crystalGrowthItems.add(Items.FLINT);
      ModRecipes.addRitualRecipe(new RitualRecipe(crystalGrowthItems, new CrystalGrowthRitualEffect()));
  }
}
