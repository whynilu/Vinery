package net.satisfy.vinery.core.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.satisfy.vinery.core.block.entity.FermentationBarrelBlockEntity;
import net.satisfy.vinery.core.recipe.input.FermentationBarrelRecipeInput;
import net.satisfy.vinery.core.registry.ObjectRegistry;
import net.satisfy.vinery.core.registry.RecipeTypesRegistry;
import org.jetbrains.annotations.NotNull;

public class FermentationBarrelRecipe implements Recipe<FermentationBarrelRecipeInput> {
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;
    private final FermentationBarrelRecipeInput.JuiceData juiceData;
    private final boolean wineBottleRequired;
    public static RecipeType<FermentationBarrelRecipe> Type = RecipeTypesRegistry.FERMENTATION_BARREL_RECIPE_TYPE.get();

    public FermentationBarrelRecipe(NonNullList<Ingredient> inputs, FermentationBarrelRecipeInput.JuiceData data, ItemStack output, boolean wineBottleRequired) {
        this.inputs = inputs;
        this.juiceData = data;
        this.output = output;

        this.wineBottleRequired = wineBottleRequired;
    }

    public FermentationBarrelRecipeInput.JuiceData getJuiceData()
    {
        return this.juiceData;
    }

    public boolean isWineBottleRequired() {
        return wineBottleRequired;
    }

    @Override
    public boolean matches(FermentationBarrelRecipeInput input, Level world) {
        if (this.juiceData.amount() > 0) {
            if (input.data().amount() < this.juiceData.amount()) return false;
            if (!this.juiceData.type().equals(input.data().type())) return false;
        }

        if (this.wineBottleRequired) {
            ItemStack wineBottle = input.getItem(FermentationBarrelRecipeInput.WINE_BOTTLE_SLOT);
            if (wineBottle.isEmpty() || !wineBottle.is(ObjectRegistry.WINE_BOTTLE.get())) return false;
        }

        java.util.List<ItemStack> stacks = input.getIngredientSlots();
        if (stacks.size() != this.inputs.size()) return false;
        boolean[] used = new boolean[stacks.size()];
        for (Ingredient ingredient : this.inputs) {
            boolean found = false;
            for (int i = 0; i < stacks.size(); i++) {
                if (!used[i] && !stacks.get(i).isEmpty() && ingredient.test(stacks.get(i))) {
                    used[i] = true;
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    @Override
    public @NotNull ItemStack assemble(FermentationBarrelRecipeInput input) {
        return this.output.copy();
    }


    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return this.inputs;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return this.output.copy();
    }

    public NonNullList<Ingredient> getInputs() {
        return inputs;
    }

    @Override
    public RecipeSerializer<FermentationBarrelRecipe> getSerializer() {
        return RecipeTypesRegistry.FERMENTATION_BARREL_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<FermentationBarrelRecipe> getType() {
        return RecipeTypesRegistry.FERMENTATION_BARREL_RECIPE_TYPE.get();
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public net.minecraft.world.item.crafting.RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    private static final MapCodec<Boolean> WINE_BOTTLE_CODEC = RecordCodecBuilder.mapCodec(inst ->
            inst.group(Codec.BOOL.fieldOf("required").forGetter(b -> b)).apply(inst, b -> b)
    );

    public static final MapCodec<FermentationBarrelRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC.listOf().fieldOf("ingredients")
                    .xmap(list -> NonNullList.of(Ingredient.of(), list.toArray(new Ingredient[0])), ingredients -> ingredients)
                    .forGetter(FermentationBarrelRecipe::getInputs),
            FermentationBarrelRecipeInput.JuiceData.CODEC.fieldOf("juice").forGetter(FermentationBarrelRecipe::getJuiceData),
            ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.output),
            WINE_BOTTLE_CODEC.fieldOf("wine_bottle").forGetter(FermentationBarrelRecipe::isWineBottleRequired)
    ).apply(instance, FermentationBarrelRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, FermentationBarrelRecipe> STREAM_CODEC = StreamCodec.of(
            (buf, recipe) -> {
                buf.writeVarInt(recipe.inputs.size());
                for (Ingredient ingredient : recipe.inputs) {
                    Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ingredient);
                }
                FermentationBarrelRecipeInput.JuiceData.STREAM_CODEC.encode(buf, recipe.getJuiceData());
                ItemStack.STREAM_CODEC.encode(buf, recipe.output);
                buf.writeBoolean(recipe.wineBottleRequired);
            },
            buf -> {
                int size = buf.readVarInt();
                NonNullList<Ingredient> inputs = NonNullList.withSize(size, Ingredient.EMPTY);
                for (int i = 0; i < size; i++) {
                    inputs.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
                }
                FermentationBarrelRecipeInput.JuiceData juiceData = FermentationBarrelRecipeInput.JuiceData.STREAM_CODEC.decode(buf);
                ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
                boolean wineBottleRequired = buf.readBoolean();
                return new FermentationBarrelRecipe(inputs, juiceData, output, wineBottleRequired);
            }
    );

    public static final RecipeSerializer<FermentationBarrelRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);

}