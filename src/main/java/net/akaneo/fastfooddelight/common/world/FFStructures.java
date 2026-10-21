package net.akaneo.fastfooddelight.common.world;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.akaneo.fastfooddelight.FastFoodDelightFabric;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.List;

/**
 * Injects the five FastFood village houses into the vanilla village house pools
 * at server start, preserving the original weight (25). This is the M4b
 * implementation of ADR-012.
 */
public final class FFStructures {
    private static final String MOD_ID = FastFoodDelightFabric.MOD_ID;
    private static final String[] BIOMES = {"plains", "desert", "savanna", "snowy", "taiga"};
    private static final int WEIGHT = 25;

    private FFStructures() {
    }

    public static void init() {
        ServerLifecycleEvents.SERVER_STARTING.register(FFStructures::onServerStarting);
    }

    private static void onServerStarting(MinecraftServer server) {
        RegistryAccess access = server.registryAccess();
        Registry<StructureTemplatePool> pools = access.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);
        StructureProcessorList empty = access.registryOrThrow(Registry.PROCESSOR_LIST_REGISTRY)
                .get(new ResourceLocation("minecraft", "empty"));

        int added = 0;
        int skipped = 0;
        for (String biome : BIOMES) {
            ResourceLocation poolId = new ResourceLocation("minecraft", "village/" + biome + "/houses");
            StructureTemplatePool pool = pools.get(poolId);
            if (pool == null) {
                FastFoodDelightFabric.LOGGER.error("[FastFood Delight Fabric] missing village pool {}", poolId);
                continue;
            }

            ResourceLocation structureId = new ResourceLocation(MOD_ID, "village/houses/" + biome + "_fast_food");
            if (isAlreadyInjected(pool, structureId)) {
                skipped++;
                FastFoodDelightFabric.LOGGER.info(
                        "[FastFood Delight Fabric] village house already present, skipping {}",
                        structureId);
                continue;
            }

            StructurePoolElement element = (empty == null
                    ? StructurePoolElement.legacy(structureId.toString())
                    : StructurePoolElement.legacy(structureId.toString(), empty))
                    .apply(StructureTemplatePool.Projection.RIGID);

            List<Pair<StructurePoolElement, Integer>> rawTemplates = Lists.newArrayList(pool.rawTemplates);
            rawTemplates.add(Pair.of(element, WEIGHT));
            pool.rawTemplates = rawTemplates;
            // The vanilla pool constructor expands weighted entries into this list,
            // so add the same number of copies to preserve the intended weight.
            for (int i = 0; i < WEIGHT; i++) {
                pool.templates.add(element);
            }
            added++;
            FastFoodDelightFabric.LOGGER.info(
                    "[FastFood Delight Fabric] injected {} into {} (weight {})",
                    structureId, poolId, WEIGHT);
        }

        FastFoodDelightFabric.LOGGER.info(
                "[FastFood Delight Fabric] village pool injection complete: added={}, skipped={}/{} houses",
                added, skipped, BIOMES.length);
    }

    /**
     * Checks the target pool itself instead of using a static one-shot flag.
     * A new integrated/dedicated server gets new registry pool instances and
     * must be injected again; a repeated lifecycle call on the same registry
     * must not add a duplicate weighted entry.
     */
    private static boolean isAlreadyInjected(StructureTemplatePool pool, ResourceLocation structureId) {
        for (Pair<StructurePoolElement, Integer> entry : pool.rawTemplates) {
            if (entry.getFirst().toString().contains(structureId.toString())) {
                return true;
            }
        }
        return false;
    }
}
