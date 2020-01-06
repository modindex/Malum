package malum.recipes;

import net.minecraft.item.Item;

import java.util.ArrayList;

public class CraftingRecipe
{

    private ArrayList<Item> ingredients = new ArrayList<>();
    private Item output;

  public CraftingRecipe(ArrayList<Item> ingredients, Item output) {
    this.ingredients = ingredients;
    this.output = output;
  }

  public ArrayList<Item> getIngredients() {
    return ingredients;
  }

  public Item getItem() {
    return output;
  }
}
