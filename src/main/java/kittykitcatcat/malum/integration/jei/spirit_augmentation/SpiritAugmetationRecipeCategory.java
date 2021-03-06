package kittykitcatcat.malum.integration.jei.spirit_augmentation;

import com.mojang.blaze3d.platform.GlStateManager;
import kittykitcatcat.malum.MalumMod;
import kittykitcatcat.malum.registry.ModBlocks;
import kittykitcatcat.malum.registry.ModItems;
import kittykitcatcat.malum.spirit_augmentation.SpiritAugmentationData;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static kittykitcatcat.malum.integration.jei.JEIHandler.newSpirit;
import static kittykitcatcat.malum.integration.jei.JEIHandler.newSpiritAugment;

public class SpiritAugmetationRecipeCategory implements IRecipeCategory<SpiritAugmentationData>
{

    public static final ResourceLocation UID = new ResourceLocation(MalumMod.MODID, "spirit_augmetation");
    private final IDrawable background;
    private final String localizedName;
    private final IDrawable overlay;
    private final IDrawable icon;

    public SpiritAugmetationRecipeCategory(IGuiHelper guiHelper)
    {
        background = guiHelper.createBlankDrawable(100, 24);
        localizedName = I18n.format("malum.jei.spirit_augmetation");
        overlay = guiHelper.createDrawable(new ResourceLocation(MalumMod.MODID, "textures/gui/spirit_augmetation_overlay.png"),
            0, 0, 120, 30);
        icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.spirit_augmenter));
    }

    @Override
    public void draw(SpiritAugmentationData data, double mouseX, double mouseY)
    {
        GlStateManager.enableAlphaTest();
        GlStateManager.enableBlend();
        overlay.draw(-2, -2);
        GlStateManager.disableBlend();
        GlStateManager.disableAlphaTest();
    }

    @Nonnull
    @Override
    public ResourceLocation getUid()
    {
        return UID;
    }

    @Nonnull
    @Override
    public Class<? extends SpiritAugmentationData> getRecipeClass()
    {
        return SpiritAugmentationData.class;
    }

    @Nonnull
    @Override
    public String getTitle()
    {
        return localizedName;
    }

    @Nonnull
    @Override
    public IDrawable getBackground()
    {
        return background;
    }

    @Nonnull
    @Override
    public IDrawable getIcon()
    {
        return icon;
    }

    @Override
    public void setIngredients(SpiritAugmentationData data, IIngredients iIngredients)
    {
        iIngredients.setInput(VanillaTypes.ITEM, new ItemStack(ModItems.spirit_bottle));
        iIngredients.setOutput(VanillaTypes.ITEM, new ItemStack(data.getItem()));
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, SpiritAugmentationData data, IIngredients iIngredients)
    {
        iRecipeLayout.getItemStacks().init(0, true, 1, 3);
        iRecipeLayout.getItemStacks().set(0, newSpirit(data.getSpirit().spirit()));
        iRecipeLayout.getItemStacks().init(1, true, 31, 3);
        iRecipeLayout.getItemStacks().set(1, new ItemStack(ModBlocks.spirit_augmenter));
        iRecipeLayout.getItemStacks().init(2, true, 61, 3);
        iRecipeLayout.getItemStacks().set(2, new ItemStack(data.getItem()));
        iRecipeLayout.getItemStacks().init(3, true, 83, 3);
        iRecipeLayout.getItemStacks().set(3, newSpiritAugment(data.getSpirit().augmentDescription()));
    }
}
