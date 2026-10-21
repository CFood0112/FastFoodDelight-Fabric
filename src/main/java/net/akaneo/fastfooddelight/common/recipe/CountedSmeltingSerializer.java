package net.akaneo.fastfooddelight.common.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;

/**
 * 1.17.1's standard smelting serializer only accepts a string result, so it
 * cannot express the original recipe "1 milk bucket -> 8 cheese". This
 * serializer keeps the vanilla furnace recipe behavior while allowing an
 * object result with a count, preserving the frozen baseline exactly.
 */
public final class CountedSmeltingSerializer implements RecipeSerializer<SmeltingRecipe> {
    public static final CountedSmeltingSerializer INSTANCE = new CountedSmeltingSerializer();

    @Override
    public SmeltingRecipe fromJson(ResourceLocation id, JsonObject json) {
        String group = GsonHelper.getAsString(json, "group", "");
        Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "ingredient"));

        JsonElement resultElement = json.get("result");
        ItemStack result;
        if (resultElement.isJsonPrimitive()) {
            Item item = Registry.ITEM.get(new ResourceLocation(resultElement.getAsString()));
            result = new ItemStack(item);
        } else {
            JsonObject resultObject = resultElement.getAsJsonObject();
            Item item = Registry.ITEM.get(new ResourceLocation(GsonHelper.getAsString(resultObject, "item")));
            int count = GsonHelper.getAsInt(resultObject, "count", 1);
            result = new ItemStack(item, count);
        }

        float experience = GsonHelper.getAsFloat(json, "experience", 0.0F);
        int cookingTime = GsonHelper.getAsInt(json, "cookingtime", 200);
        return new SmeltingRecipe(id, group, ingredient, result, experience, cookingTime);
    }

    @Override
    public SmeltingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
        return RecipeSerializer.SMELTING_RECIPE.fromNetwork(id, buffer);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, SmeltingRecipe recipe) {
        RecipeSerializer.SMELTING_RECIPE.toNetwork(buffer, recipe);
    }
}
