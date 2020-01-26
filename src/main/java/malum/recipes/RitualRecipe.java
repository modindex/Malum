package malum.recipes;

import malum.rituals.RitualEffect;
import net.minecraft.item.Item;

import java.util.ArrayList;

public class RitualRecipe {

    private ArrayList<Item> ingredients;
  private RitualEffect ritualEffect;

  public RitualRecipe(ArrayList<Item> ingredients, RitualEffect ritualEffect) {
    this.ingredients = ingredients;
    this.ritualEffect = ritualEffect;
  }

  public ArrayList<Item> getIngredients() {
    return ingredients;
  }

    public RitualEffect getRitualEffect() {
    return ritualEffect;
  }
}
