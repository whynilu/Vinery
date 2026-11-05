package net.satisfy.vinery.core.registry;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public final class ArmorSetRegistry {
    private ArmorSetRegistry() {
    }

    public static void init() {
        // Armor set bonuses are evaluated directly against equipped Vinery pieces.
    }

    public static boolean hasWinemakerSet(LivingEntity entity) {
        return isPiece(entity.getItemBySlot(EquipmentSlot.HEAD), ObjectRegistry.STRAW_HAT.get())
                && isPiece(entity.getItemBySlot(EquipmentSlot.CHEST), ObjectRegistry.WINEMAKER_APRON.get())
                && isPiece(entity.getItemBySlot(EquipmentSlot.LEGS), ObjectRegistry.WINEMAKER_LEGGINGS.get())
                && isPiece(entity.getItemBySlot(EquipmentSlot.FEET), ObjectRegistry.WINEMAKER_BOOTS.get());
    }

    private static boolean isPiece(ItemStack equipped, net.minecraft.world.item.Item expected) {
        return equipped.is(expected);
    }
}
