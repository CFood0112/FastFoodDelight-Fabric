package net.akaneo.fastfooddelight.common.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * An edible item that returns a container item after consumption.
 *
 * <p>Vanilla's craft-remainder is applied by crafting, not by eating, so the
 * original Forge implementation used a custom consumable item for fries bowls,
 * ice-cream bowls and drinks. This class reproduces that behavior.
 */
public class ConsumableItem extends Item {
    private final Item containerItem;

    public ConsumableItem(Properties properties, Item containerItem) {
        super(properties);
        this.containerItem = containerItem;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        int countBefore = stack.getCount();
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide && containerItem != null) {
            boolean creativePlayer = entity instanceof Player player && player.isCreative();
            boolean consumed = !creativePlayer
                    && (stack.isEmpty() || stack.getCount() < countBefore
                    || result.isEmpty() || result.getCount() < countBefore);

            if (consumed) {
                ItemStack container = new ItemStack(containerItem);
                if (entity instanceof Player player) {
                    if (!player.getInventory().add(container)) {
                        player.drop(container, false);
                    }
                } else {
                    entity.spawnAtLocation(container);
                }
            }
        }
        return result;
    }
}
