package kittykitcatcat.malum.recipes;

import kittykitcatcat.malum.registry.ModItems;
import kittykitcatcat.malum.registry.ModRecipes;
import net.minecraft.item.Items;

public class SpiritInfusionRecipes
{

    public static void initRecipes()
    {
        ModRecipes.addSpiritInfusionRecipe(new SpiritInfusionRecipe(
            Items.GLOWSTONE_DUST,
            ModItems.soul_dust,
            SpiritInfusionRecipe.createSpiritList
                ("minecraft:zombie",
                    "minecraft:skeleton",
                    "minecraft:creeper")
        ));
        ModRecipes.addSpiritInfusionRecipe(new SpiritInfusionRecipe(
            Items.LEATHER,
            ModItems.leather_belt,
            SpiritInfusionRecipe.createSpiritList
                ("minecraft:cow",
                    "minecraft:creeper")
        ));
        ModRecipes.addSpiritInfusionRecipe(new SpiritInfusionRecipe(
            Items.LEATHER,
            ModItems.leather_ring,
            SpiritInfusionRecipe.createSpiritList
                ("minecraft:cow",
                    "minecraft:skeleton")
        ));
        ModRecipes.addSpiritInfusionRecipe(new SpiritInfusionRecipe(
            Items.LEATHER,
            ModItems.leather_necklace,
            SpiritInfusionRecipe.createSpiritList
                ("minecraft:cow",
                    "minecraft:zombie")
        ));
        ModRecipes.addSpiritInfusionRecipe(new SpiritInfusionRecipe(
            Items.DIRT,
            Items.DIAMOND,
            SpiritInfusionRecipe.createSpiritList
                ("minecraft:ender_dragon",
                    "minecraft:ender_dragon",
                    "minecraft:ender_dragon",
                    "minecraft:ender_dragon",
                    "minecraft:ender_dragon",
                    "minecraft:ender_dragon",
                    "minecraft:ender_dragon",
                    "minecraft:ender_dragon")
        ));
    }
}
