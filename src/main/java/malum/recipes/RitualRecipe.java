package malum.recipes;

import malum.rituals.RitualEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class RitualRecipe {

    private ArrayList<Item> ingredients = new ArrayList<>();
  private RitualEffect ritualEffect;
  private String target;

  public RitualRecipe(ArrayList<Item> ingredients, RitualEffect ritualEffect, String target) {
    this.ingredients = ingredients;
    this.ritualEffect = ritualEffect;
    this.target = target;
  }

  public ArrayList<Item> getIngredients() {
    return ingredients;
  }

  public RitualEffect getRitualEffect() {
    return ritualEffect;
  }

  public String getTarget() {
    return target;
  }
}
