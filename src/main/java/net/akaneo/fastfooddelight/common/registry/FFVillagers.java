package net.akaneo.fastfooddelight.common.registry;

import com.google.common.collect.ImmutableSet;
import net.akaneo.fastfooddelight.FastFoodDelightFabric;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;

/**
 * Fast Food waiter profession and its original 30 trades (frozen baseline).
 */
public final class FFVillagers {
    private static final String MOD_ID = FastFoodDelightFabric.MOD_ID;
    private static final ResourceLocation PROFESSION_ID = new ResourceLocation(MOD_ID, "fast_food_waiter");

    public static final VillagerProfession FAST_FOOD_WAITER = Registry.register(
            Registry.VILLAGER_PROFESSION,
            PROFESSION_ID,
            new VillagerProfession(
                    "fast_food_waiter",
                    FFPointsOfInterest.CHECKOUT_MACHINE_POI,
                    ImmutableSet.of(),
                    ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_BUTCHER));

    private FFVillagers() {
    }

    public static void init() {
        int tradeCount = 0;

        // Level 1
        tradeCount += sell(1, 3, FFItems.FRENCH_FRIES);
        tradeCount += sell(1, 3, FFItems.CARROT_FRIES);
        tradeCount += sell(1, 3, FFItems.FRIED_ONIONS);
        tradeCount += sell(1, 4, fd("mixed_salad"));

        // Level 2
        tradeCount += sell(2, 4, fd("egg_sandwich"));
        tradeCount += sell(2, 4, FFItems.COD_SANDWICH);
        tradeCount += sell(2, 4, FFItems.SALMON_SANDWICH);
        tradeCount += sell(2, 5, fd("chicken_sandwich"));
        tradeCount += sell(2, 5, fd("mutton_wrap"));
        tradeCount += sell(2, 5, FFItems.CHEESE_WRAP);
        tradeCount += sell(2, 5, FFItems.CHICKEN_WRAP);
        tradeCount += sell(2, 5, fd("bacon_sandwich"));
        tradeCount += sell(2, 5, FFItems.RABBIT_SANDWICH);
        tradeCount += sell(2, 3, FFItems.CHICKEN_NUGGETS);

        // Level 3
        tradeCount += sell(3, 6, fd("hamburger"));
        tradeCount += sell(3, 6, FFItems.PORK_SANDWICH);
        tradeCount += sell(3, 7, FFItems.CHEESEBURGER);
        tradeCount += sell(3, 7, FFItems.CHICKEN_CHEESE_SANDWICH);
        tradeCount += sell(3, 6, FFItems.CROQUE_MONSIEUR);

        // Level 4
        tradeCount += sell(4, 2, FFItems.COLA_SODA);
        tradeCount += sell(4, 2, FFItems.ORANGE_SODA);
        tradeCount += sell(4, 2, FFItems.LEMON_SODA);
        tradeCount += sell(4, 2, FFItems.CREAMING_SODA);
        tradeCount += sell(4, 2, FFItems.ICED_TEA);

        // Level 5
        tradeCount += sell(5, 3, FFItems.ICE_CREAM);
        tradeCount += sell(5, 4, FFItems.CHOCOLATE_ICE_CREAM);
        tradeCount += sell(5, 4, FFItems.SWEET_BERRY_ICE_CREAM);
        tradeCount += sell(5, 4, FFItems.FRUIT_ICE_CREAM);
        tradeCount += sell(5, 3, FFItems.APPLE_SAUCE);
        tradeCount += sell(5, 3, fd("fruit_salad"));

        FastFoodDelightFabric.LOGGER.info(
                "[FastFood Delight Fabric] registered profession {} with {} trades",
                Registry.VILLAGER_PROFESSION.getKey(FAST_FOOD_WAITER), tradeCount);
    }

    private static int sell(int level, int goldNuggets, Item item) {
        TradeOfferHelper.registerVillagerOffers(FAST_FOOD_WAITER, level, factories ->
                factories.add((trader, random) -> new MerchantOffer(
                        new ItemStack(Items.GOLD_NUGGET, goldNuggets),
                        new ItemStack(item),
                        10,
                        5,
                        0.0F)));
        return 1;
    }

    private static Item fd(String path) {
        return Registry.ITEM.get(new ResourceLocation("farmersdelight", path));
    }
}
