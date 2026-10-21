package net.akaneo.fastfooddelight.common.registry;

import net.akaneo.fastfooddelight.FastFoodDelightFabric;
import net.akaneo.fastfooddelight.common.block.CheckoutMachineBlock;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
public final class FFBlocks {
    private static final String MOD_ID = FastFoodDelightFabric.MOD_ID;

    public static final Block CHECKOUT_MACHINE = register(
            "checkout_machine",
            new CheckoutMachineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));

    private FFBlocks() {
    }

    private static Block register(String path, Block block) {
        return Registry.register(Registry.BLOCK, new ResourceLocation(MOD_ID, path), block);
    }

    public static void init() {
        FastFoodDelightFabric.LOGGER.info(
                "[FastFood Delight Fabric] registered block {}",
                Registry.BLOCK.getKey(CHECKOUT_MACHINE));
    }
}
