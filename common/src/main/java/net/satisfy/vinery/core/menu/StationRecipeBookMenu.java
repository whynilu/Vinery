package net.satisfy.vinery.core.menu;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;
import java.util.Map;

/**
 * Local contract used by Vinery's recipe-book aware workstation menus.
 * Kept independent of the legacy Foundation mod so the common module can
 * compile and run without a second mod's internal menu classes.
 */
public interface StationRecipeBookMenu {
    default List<RecipeType<?>> recipeBookTypes() {
        return List.of(recipeBookType());
    }

    default RecipeType<?> recipeBookType() {
        return null;
    }

    default int[] recipeBookInputSlots() {
        return new int[0];
    }

    default int[] recipeBookInputSlots(RecipeType<?> type) {
        return recipeBookInputSlots();
    }

    default Component recipeBookTabName(RecipeType<?> type) {
        return Component.empty();
    }

    default Map<Integer, Ingredient> recipeBookExtraInputs(RecipeHolder<?> recipe) {
        return Map.of();
    }

    default Map<Integer, Ingredient> recipeBookExtraGhosts(RecipeHolder<?> recipe) {
        return Map.of();
    }

    default boolean recipeBookExtrasMet(RecipeHolder<?> recipe) {
        return true;
    }

    default int recipeBookResultSlot(RecipeType<?> type) {
        return -1;
    }

    default void appendRecipeBookTooltip(RecipeHolder<?> recipe, List<Component> tooltip) {
    }
}
