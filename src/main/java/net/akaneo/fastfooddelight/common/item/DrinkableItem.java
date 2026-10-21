package net.akaneo.fastfooddelight.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

/** Drinkable consumable: 32-tick drink animation and glass-bottle container. */
public class DrinkableItem extends ConsumableItem {
    public DrinkableItem(Properties properties, Item containerItem) {
        super(properties, containerItem);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }
}
