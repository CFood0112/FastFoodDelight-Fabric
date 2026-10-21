package net.akaneo.fastfooddelight.common.registry;

import net.akaneo.fastfooddelight.FastFoodDelightFabric;
import net.akaneo.fastfooddelight.common.recipe.CountedSmeltingSerializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;

public final class FFRecipeSerializers {
    public static final RecipeSerializer<SmeltingRecipe> COUNTED_SMELTING = Registry.register(
            Registry.RECIPE_SERIALIZER,
            new ResourceLocation(FastFoodDelightFabric.MOD_ID, "counted_smelting"),
            CountedSmeltingSerializer.INSTANCE);

    private FFRecipeSerializers() {
    }

    public static void init() {
        FastFoodDelightFabric.LOGGER.info(
                "[FastFood Delight Fabric] registered recipe serializer {}",
                Registry.RECIPE_SERIALIZER.getKey(COUNTED_SMELTING));
    }
}
