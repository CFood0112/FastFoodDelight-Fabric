package net.akaneo.fastfooddelight.common;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

/**
 * Frozen original Forge 1.19 food values for the Fabric port.
 *
 * <p>These values are the behavior baseline (ADR-007). Do not replace them with
 * the archived FD Fabric port's values; known original quirks (for example
 * chicken_wrap using 10/0.8 and chicken_nuggets using 5/0.6) are intentional.
 */
public final class FFFoodValues {
    public static final FoodProperties CHEESE = food(4, 0.4F);
    public static final FoodProperties FRIES = food(5, 0.6F);
    public static final FoodProperties COD_SANDWICH = food(8, 0.8F);
    public static final FoodProperties SALMON_SANDWICH = food(8, 0.8F);
    public static final FoodProperties PORK_SANDWICH = food(11, 0.8F);
    public static final FoodProperties CHEESEBURGER = food(13, 0.8F);
    public static final FoodProperties CHICKEN_CHEESE_SANDWICH = food(12, 0.8F);
    public static final FoodProperties CROQUE_MONSIEUR = food(12, 0.8F);
    public static final FoodProperties RABBIT_SANDWICH = food(8, 0.8F);
    public static final FoodProperties CHEESE_WRAP = food(8, 0.8F);
    public static final FoodProperties CHICKEN_WRAP = food(10, 0.8F);
    public static final FoodProperties CHICKEN_NUGGETS = food(5, 0.6F);

    public static final FoodProperties SODA = new FoodProperties.Builder()
            .alwaysEat()
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 300, 0), 1.0F)
            .build();

    public static final FoodProperties ICE_CREAM_BASIC = food(5, 0.6F);
    public static final FoodProperties ICE_CREAM_ADV = food(8, 0.6F);

    private FFFoodValues() {
    }

    private static FoodProperties food(int nutrition, float saturation) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).build();
    }
}
