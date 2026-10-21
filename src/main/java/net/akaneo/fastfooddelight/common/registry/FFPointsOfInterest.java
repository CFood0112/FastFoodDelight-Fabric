package net.akaneo.fastfooddelight.common.registry;

import net.akaneo.fastfooddelight.FastFoodDelightFabric;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;

/**
 * Points of interest for the Fabric port. M2b registers the checkout machine
 * workstation POI only; villager profession/trades are added in M4.
 */
public final class FFPointsOfInterest {
    private static final String MOD_ID = FastFoodDelightFabric.MOD_ID;

    public static final PoiType CHECKOUT_MACHINE_POI = PointOfInterestHelper.register(
            new ResourceLocation(MOD_ID, "checkout_machine_poi"),
            1,
            1,
            FFBlocks.CHECKOUT_MACHINE);

    private FFPointsOfInterest() {
    }

    public static void init() {
        FastFoodDelightFabric.LOGGER.info(
                "[FastFood Delight Fabric] registered POI {}",
                Registry.POINT_OF_INTEREST_TYPE.getKey(CHECKOUT_MACHINE_POI));
    }
}
