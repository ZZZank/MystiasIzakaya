
package org.hiedacamellia.mystiasizakaya.integration.jei.categories;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.hiedacamellia.mystiasizakaya.integration.jei.JeiPlugin;
import org.hiedacamellia.mystiasizakaya.integration.jei.recipes.GrillTypeRecipe;
import org.hiedacamellia.mystiasizakaya.registries.MIItem;

public class GrillTypeRecipeCategory implements IRecipeCategory<GrillTypeRecipe> {
	public final static ResourceLocation UID = new ResourceLocation("mystias_izakaya", "grill_type");
	public final static ResourceLocation TEXTURE = new ResourceLocation("mystias_izakaya", "textures/screens/recipe_gui.png");
	private final IDrawable background;
	private final IDrawable icon;

	public GrillTypeRecipeCategory(IGuiHelper helper) {
		IDrawableBuilder drawableBuilder = helper.drawableBuilder(TEXTURE, 0, 0, 176, 87).setTextureSize(176, 87);
		this.background = drawableBuilder.build();
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(MIItem.GRILL.get()));
	}

	@Override
	public mezz.jei.api.recipe.RecipeType<GrillTypeRecipe> getRecipeType() {
		return JeiPlugin.GrillType_Type;
	}

	@Override
	public Component getTitle() {
		return Component.literal(Component.translatable("jei.mystias_izakaya.Grill").getString());
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, GrillTypeRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 16, 26).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 43, 26).addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.INPUT, 70, 26).addIngredients(recipe.getIngredients().get(2));
		builder.addSlot(RecipeIngredientRole.INPUT, 97, 26).addIngredients(recipe.getIngredients().get(3));
		builder.addSlot(RecipeIngredientRole.INPUT, 124, 26).addIngredients(recipe.getIngredients().get(4));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 151, 44).addItemStack(recipe.getResultItem(null));
	}
}
