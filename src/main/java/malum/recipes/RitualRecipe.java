package malum.recipes;

import malum.rituals.RitualEffect;
import net.minecraft.item.Item;

import java.util.ArrayList;

public class RitualRecipe {

    private ArrayList<Item> ingredients;
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
