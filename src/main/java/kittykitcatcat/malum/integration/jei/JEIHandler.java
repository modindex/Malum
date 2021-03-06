package kittykitcatcat.malum.integration.jei;

import kittykitcatcat.malum.MalumMod;
import kittykitcatcat.malum.integration.jei.spirit_augmentation.SpiritAugmetationRecipeCategory;
import kittykitcatcat.malum.integration.jei.spirit_infusion.SpiritInfusionRecipeCategory;
import kittykitcatcat.malum.integration.jei.transmutation.TransmutationRecipeCategory;
import kittykitcatcat.malum.registry.ModBlocks;
import kittykitcatcat.malum.registry.ModItems;
import kittykitcatcat.malum.registry.ModRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

@JeiPlugin
public class JEIHandler implements IModPlugin
{
    private static final ResourceLocation ID = new ResourceLocation(MalumMod.MODID, "main");

    public static ItemStack newSpirit(String tooltip)
    {
        ItemStack stack = new ItemStack(ModItems.spirit);
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("tooltip", tooltip);
        nbt.putInt("type", (int) (Math.random() * 8));
        stack.setTag(nbt);

        return stack;
    }

    public static ItemStack newSpiritAugment(String toolTip)
    {
        ItemStack stack = new ItemStack(ModItems.spirit_augment);
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("tooltip", toolTip);
        stack.setTag(nbt);

        return stack;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        registry.addRecipeCategories(
            new TransmutationRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
            new SpiritInfusionRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
            new SpiritAugmetationRecipeCategory(registry.getJeiHelpers().getGuiHelper())

        );
    }

    @Override
    public void registerRecipes(@Nonnull IRecipeRegistration registry)
    {
        registry.addRecipes(ModRecipes.blockTransmutationRecipes, TransmutationRecipeCategory.UID);
        registry.addRecipes(ModRecipes.spiritInfusionRecipes, SpiritInfusionRecipeCategory.UID);
        registry.addRecipes(ModRecipes.spiritAugmentationData, SpiritAugmetationRecipeCategory.UID);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry)
    {
        registry.addRecipeCatalyst(new ItemStack(ModItems.transmutation_gem), TransmutationRecipeCategory.UID);
        registry.addRecipeCatalyst(new ItemStack(ModItems.spirit_altar), SpiritInfusionRecipeCategory.UID);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.spirit_augmenter), SpiritInfusionRecipeCategory.UID);
    }

    @Nonnull
    @Override
    public ResourceLocation getPluginUid()
    {
        return ID;
    }
}
