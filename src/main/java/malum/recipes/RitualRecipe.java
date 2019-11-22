package malum.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class RitualRecipe {

    private ArrayList<Item> ingredients = new ArrayList<>();
  private int ritualID;
  private String name;

  public RitualRecipe(ArrayList<Item> ingredients, int ritualID, String name) {
    this.ingredients = ingredients;
    this.ritualID = ritualID;
    this.name = name;
  }

  public ArrayList<Item> getIngredients() {
    return ingredients;
  }

  public int getRitualID() {
    return ritualID;
  }

  public String getName() {
    return name;
  }
}
