package kittykitcatcat.malum.integration.jei.spirit_infusion;

import com.mojang.blaze3d.platform.GlStateManager;
import kittykitcatcat.malum.MalumMod;
import kittykitcatcat.malum.recipes.SpiritInfusionRecipe;
import kittykitcatcat.malum.registry.ModItems;
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


public class SpiritInfusionRecipeCategory implements IRecipeCategory<SpiritInfusionRecipe>
{

    public static final ResourceLocation UID = new ResourceLocation(MalumMod.MODID, "spirit_infusion");
    private final IDrawable background;
    private final String localizedName;
    private final IDrawable overlay;
    private final IDrawable icon;

    public SpiritInfusionRecipeCategory(IGuiHelper guiHelper)
    {
        background = guiHelper.createBlankDrawable(120, 112);
        localizedName = I18n.format("malum.jei.spirit_infusion");
        overlay = guiHelper.createDrawable(new ResourceLocation("malum", "textures/gui/spirit_infusion_overlay.png"),
            0, 0, 112, 112);
        icon = guiHelper.createDrawableIngredient(new ItemStack(ModItems.spirit_altar));
    }

    @Override
    public void draw(SpiritInfusionRecipe recipe, double mouseX, double mouseY)
    {
        GlStateManager.enableAlphaTest();
        GlStateManager.enableBlend();
        overlay.draw(4, 0);
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
    public Class<? extends SpiritInfusionRecipe> getRecipeClass()
    {
        return SpiritInfusionRecipe.class;
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
    public void setIngredients(SpiritInfusionRecipe spiritInfusionRecipe, IIngredients iIngredients)
    {
        iIngredients.setInput(VanillaTypes.ITEM, new ItemStack(spiritInfusionRecipe.getInput_item()));
        iIngredients.setInput(VanillaTypes.ITEM, new ItemStack(ModItems.spirit_bottle));
        iIngredients.setOutput(VanillaTypes.ITEM, new ItemStack(spiritInfusionRecipe.getOutput_item()));
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, SpiritInfusionRecipe spiritInfusionRecipe, IIngredients iIngredients)
    {
        iRecipeLayout.getItemStacks().init(0, true, 51, 93);
        iRecipeLayout.getItemStacks().set(0, new ItemStack(spiritInfusionRecipe.getInput_item()));

        iRecipeLayout.getItemStacks().init(1, true, 51, 33);
        iRecipeLayout.getItemStacks().set(1, new ItemStack(spiritInfusionRecipe.getOutput_item()));

        //souls
        if (spiritInfusionRecipe.spirits.size() >= 1)
        {
            iRecipeLayout.getItemStacks().init(2, true, 51, 65);
            iRecipeLayout.getItemStacks().set(2, newSpirit(spiritInfusionRecipe.spirits.get(0)));
        }
        if (spiritInfusionRecipe.spirits.size() >= 2)
        {
            iRecipeLayout.getItemStacks().init(3, true, 27, 57);
            iRecipeLayout.getItemStacks().set(3, newSpirit(spiritInfusionRecipe.spirits.get(1)));
        }
        if (spiritInfusionRecipe.spirits.size() >= 3)
        {
            iRecipeLayout.getItemStacks().init(4, true, 19, 33);
            iRecipeLayout.getItemStacks().set(4, newSpirit(spiritInfusionRecipe.spirits.get(2)));
        }
        if (spiritInfusionRecipe.spirits.size() >= 4)
        {
            iRecipeLayout.getItemStacks().init(5, true, 27, 9);
            iRecipeLayout.getItemStacks().set(5, newSpirit(spiritInfusionRecipe.spirits.get(3)));
        }
        if (spiritInfusionRecipe.spirits.size() >= 5)
        {
            iRecipeLayout.getItemStacks().init(6, true, 51, 1);
            iRecipeLayout.getItemStacks().set(6, newSpirit(spiritInfusionRecipe.spirits.get(4)));
        }
        if (spiritInfusionRecipe.spirits.size() >= 6)
        {
            iRecipeLayout.getItemStacks().init(7, true, 75, 9);
            iRecipeLayout.getItemStacks().set(7, newSpirit(spiritInfusionRecipe.spirits.get(5)));
        }
        if (spiritInfusionRecipe.spirits.size() >= 7)
        {
            iRecipeLayout.getItemStacks().init(8, true, 83, 33);
            iRecipeLayout.getItemStacks().set(8, newSpirit(spiritInfusionRecipe.spirits.get(6)));
        }
        if (spiritInfusionRecipe.spirits.size() == 8)
        {
            iRecipeLayout.getItemStacks().init(9, true, 75, 57);
            iRecipeLayout.getItemStacks().set(9, newSpirit(spiritInfusionRecipe.spirits.get(7)));
        }
    }
}
