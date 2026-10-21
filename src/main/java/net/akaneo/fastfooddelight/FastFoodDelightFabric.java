package net.akaneo.fastfooddelight;

import net.akaneo.fastfooddelight.common.registry.FFBlocks;
import net.akaneo.fastfooddelight.common.registry.FFItems;
import net.akaneo.fastfooddelight.common.registry.FFPointsOfInterest;
import net.akaneo.fastfooddelight.common.registry.FFRecipeSerializers;
import net.akaneo.fastfooddelight.common.registry.FFVillagers;
import net.akaneo.fastfooddelight.common.world.FFStructures;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Minimal Fabric entry point for the FastFood Delight 1.17.1 port.
 *
 * <p>This class is intentionally minimal for the B2a toolchain probe. Gameplay
 * registrations will be added incrementally after the build/launch probe passes.
 */
public class FastFoodDelightFabric implements ModInitializer {
    public static final String MOD_ID = "fastfooddelight_fabric";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[FastFood Delight Fabric] initializing (M4b village structures)");
        FFBlocks.init();
        FFItems.init();
        FFPointsOfInterest.init();
        FFVillagers.init();
        FFRecipeSerializers.init();
        FFStructures.init();
    }
}
