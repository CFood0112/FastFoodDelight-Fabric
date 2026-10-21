package net.akaneo.fastfooddelight.common.registry;

import net.akaneo.fastfooddelight.FastFoodDelightFabric;
import net.akaneo.fastfooddelight.common.FFFoodValues;
import net.akaneo.fastfooddelight.common.item.ConsumableItem;
import net.akaneo.fastfooddelight.common.item.DrinkableItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

/**
 * Item registration for the FastFood Delight Fabric port.
 *
 * <p>M3a applies the frozen original Forge 1.19 food values and consumption
 * behavior: containers for fries/ice cream, and the original 5 drink items.
 */
public final class FFItems {
    private static final String MOD_ID = FastFoodDelightFabric.MOD_ID;

    public static final CreativeModeTab CREATIVE_TAB = FabricItemGroupBuilder
            .create(new ResourceLocation(MOD_ID, "main"))
            .icon(() -> new ItemStack(Registry.ITEM.get(new ResourceLocation(MOD_ID, "french_fries"))))
            .build();

    public static final Item CHECKOUT_MACHINE = registerBlockItem("checkout_machine", FFBlocks.CHECKOUT_MACHINE);
    public static final Item FRIES_BOWL = registerBasic("fries_bowl");
    public static final Item CHEESE = registerFood("cheese", FFFoodValues.CHEESE);

    // Fries (return the fries bowl).
    public static final Item FRENCH_FRIES = registerConsumable("french_fries", FFFoodValues.FRIES, FRIES_BOWL);
    public static final Item CARROT_FRIES = registerConsumable("carrot_fries", FFFoodValues.FRIES, FRIES_BOWL);
    public static final Item FRIED_ONIONS = registerConsumable("fried_onions", FFFoodValues.FRIES, FRIES_BOWL);

    // Sandwiches / burgers / wraps.
    public static final Item COD_SANDWICH = registerFood("cod_sandwich", FFFoodValues.COD_SANDWICH);
    public static final Item SALMON_SANDWICH = registerFood("salmon_sandwich", FFFoodValues.SALMON_SANDWICH);
    public static final Item PORK_SANDWICH = registerFood("pork_sandwich", FFFoodValues.PORK_SANDWICH);
    public static final Item CHEESEBURGER = registerFood("cheeseburger", FFFoodValues.CHEESEBURGER);
    public static final Item CHICKEN_CHEESE_SANDWICH = registerFood("chicken_cheese_sandwich", FFFoodValues.CHICKEN_CHEESE_SANDWICH);
    public static final Item CROQUE_MONSIEUR = registerFood("croque_monsieur", FFFoodValues.CROQUE_MONSIEUR);
    public static final Item RABBIT_SANDWICH = registerFood("rabbit_sandwich", FFFoodValues.RABBIT_SANDWICH);
    public static final Item CHEESE_WRAP = registerFood("cheese_wrap", FFFoodValues.CHEESE_WRAP);
    public static final Item CHICKEN_WRAP = registerFood("chicken_wrap", FFFoodValues.CHICKEN_WRAP);
    public static final Item CHICKEN_NUGGETS = registerFood("chicken_nuggets", FFFoodValues.CHICKEN_NUGGETS);

    // Drinks (return a glass bottle).
    public static final Item COLA_SODA = registerDrink("cola_soda", FFFoodValues.SODA, Items.GLASS_BOTTLE);
    public static final Item ORANGE_SODA = registerDrink("orange_soda", FFFoodValues.SODA, Items.GLASS_BOTTLE);
    public static final Item LEMON_SODA = registerDrink("lemon_soda", FFFoodValues.SODA, Items.GLASS_BOTTLE);
    public static final Item CREAMING_SODA = registerDrink("creaming_soda", FFFoodValues.SODA, Items.GLASS_BOTTLE);
    public static final Item ICED_TEA = registerDrink("iced_tea", FFFoodValues.SODA, Items.GLASS_BOTTLE);

    // Desserts (return a bowl).
    public static final Item ICE_CREAM = registerConsumable("ice_cream", FFFoodValues.ICE_CREAM_BASIC, Items.BOWL);
    public static final Item CHOCOLATE_ICE_CREAM = registerConsumable("chocolate_ice_cream", FFFoodValues.ICE_CREAM_ADV, Items.BOWL);
    public static final Item SWEET_BERRY_ICE_CREAM = registerConsumable("sweet_berry_ice_cream", FFFoodValues.ICE_CREAM_ADV, Items.BOWL);
    public static final Item FRUIT_ICE_CREAM = registerConsumable("fruit_ice_cream", FFFoodValues.ICE_CREAM_ADV, Items.BOWL);
    public static final Item APPLE_SAUCE = registerConsumable("apple_sauce", FFFoodValues.ICE_CREAM_BASIC, Items.BOWL);

    private FFItems() {
    }

    private static Item registerBasic(String path) {
        return registerItem(path, new Item(new Item.Properties().tab(CREATIVE_TAB)));
    }

    private static Item registerFood(String path, FoodProperties food) {
        return registerItem(path, new Item(new Item.Properties().food(food).tab(CREATIVE_TAB)));
    }

    private static Item registerConsumable(String path, FoodProperties food, Item container) {
        return registerItem(path, new ConsumableItem(
                new Item.Properties().food(food).craftRemainder(container).tab(CREATIVE_TAB),
                container));
    }

    private static Item registerDrink(String path, FoodProperties food, Item container) {
        return registerItem(path, new DrinkableItem(
                new Item.Properties().food(food).craftRemainder(container).stacksTo(16).tab(CREATIVE_TAB),
                container));
    }

    private static Item registerBlockItem(String path, Block block) {
        return registerItem(path, new BlockItem(block, new Item.Properties().tab(CREATIVE_TAB)));
    }

    private static Item registerItem(String path, Item item) {
        return Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, path), item);
    }

    public static void init() {
        FastFoodDelightFabric.LOGGER.info(
                "[FastFood Delight Fabric] registered 26 items; checkout_machine class={} id={}",
                CHECKOUT_MACHINE.getClass().getSimpleName(),
                Registry.ITEM.getKey(CHECKOUT_MACHINE));
    }
}
