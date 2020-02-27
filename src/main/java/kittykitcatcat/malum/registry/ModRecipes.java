package kittykitcatcat.malum.registry;

import kittykitcatcat.malum.recipes.BlockTransmutationRecipe;
import kittykitcatcat.malum.recipes.ResourceFormingRecipe;
import kittykitcatcat.malum.recipes.RitualRecipe;
import kittykitcatcat.malum.recipes.SpiritInfusionRecipe;
import kittykitcatcat.malum.spirit_augmentation.SpiritAugmentationData;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModRecipes
{
    public static ArrayList<SpiritAugmentationData> spiritAugmentationData = new ArrayList<>();

    public static ArrayList<ResourceFormingRecipe> resourceFormingRecipes = new ArrayList<>();


    public static ArrayList<RitualRecipe> ritualRecipes = new ArrayList<>();

    public static List<BlockTransmutationRecipe> blockTransmutationRecipes = new ArrayList<>();

    public static List<SpiritInfusionRecipe> spiritInfusionRecipes = new ArrayList<>();

    public static void addSpiritAugmentationData(SpiritAugmentationData data)
    {
        spiritAugmentationData.add(data);
    }

    public static void addBlockTransmutationRecipe(BlockTransmutationRecipe recipe)
    {
        blockTransmutationRecipes.add(recipe);
    }

    public static void addResourceFormingRecipe(ResourceFormingRecipe recipe)
    {
        resourceFormingRecipes.add(recipe);
    }

    public static void addSpiritInfusionRecipe(SpiritInfusionRecipe recipe)
    {
        spiritInfusionRecipes.add(recipe);
    }

    public static void addRitualRecipe(RitualRecipe recipe)
    {
        ritualRecipes.add(recipe);
    }

    public static SpiritAugmentationData getSpiritAugmentationData(Item dataHoldingItem)
    {
        if (dataHoldingItem != null)
        {
            for (SpiritAugmentationData data : spiritAugmentationData)
            {
                if (data.getItem() == dataHoldingItem)
                {
                    return data;
                }
            }
        }
        return null;
    }

    public static ResourceFormingRecipe getResourceFormingRecipe(Item formItem)
    {
        if (formItem != null)
        {
            for (ResourceFormingRecipe recipe : resourceFormingRecipes)
            {
                if (recipe.getFormItem() == formItem)
                {
                    return recipe;
                }
            }
        }
        return null;
    }

    public static SpiritInfusionRecipe getSpiritInfusionRecipe(Item formItem, List<String> spirits)
    {
        if (formItem != null)
        {
            for (SpiritInfusionRecipe recipe : spiritInfusionRecipes)
            {
                if (spirits.equals(recipe.getSpirits()))
                {
                    if (recipe.getInput_item() == formItem)
                    {
                        return recipe;
                    }
                }
            }
        }
        return null;
    }

    public static BlockTransmutationRecipe getBlockTransmutationRecipe(BlockState sourceBlock)
    {
        if (sourceBlock != null)
        {
            for (BlockTransmutationRecipe recipe : blockTransmutationRecipes)
            {
                if (recipe.getBlock() == sourceBlock.getBlock())
                {
                    return recipe;
                }
            }
        }
        return null;
    }

    public static RitualRecipe getRitualRecipe(ArrayList<Item> ingredients)
    {
        if (ingredients != null)
        {
            for (RitualRecipe recipe : ritualRecipes)
            {
                if (recipe.getIngredients().equals(ingredients))
                {
                    return recipe;
                }
            }
        }
        return null;
    }
}