package malum.integration.jei;

import malum.MalumMod;
import malum.integration.jei.spirit_infusion.SpiritInfusionRecipeCategory;
import malum.integration.jei.transmutation.TransmutationRecipeCategory;
import malum.registry.ModItems;
import malum.registry.ModRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

@JeiPlugin
public class JEIHandler implements IModPlugin
{
	private static final ResourceLocation ID = new ResourceLocation(MalumMod.MODID, "main");

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(
				new TransmutationRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
            new SpiritInfusionRecipeCategory(registry.getJeiHelpers().getGuiHelper())

        );
	}

    @Override
	public void registerRecipes(@Nonnull IRecipeRegistration registry) {
		registry.addRecipes(ModRecipes.blockTransmutationRecipes, TransmutationRecipeCategory.UID);
        registry.addRecipes(ModRecipes.spiritInfusionRecipes, SpiritInfusionRecipeCategory.UID);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
		registry.addRecipeCatalyst(new ItemStack(ModItems.transmutation_gem), TransmutationRecipeCategory.UID);
        registry.addRecipeCatalyst(new ItemStack(ModItems.spirit_altar), SpiritInfusionRecipeCategory.UID);
	}
	@Nonnull
	@Override
	public ResourceLocation getPluginUid() {
		return ID;
	}
}
