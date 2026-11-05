package net.satisfy.vinery.core.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.satisfy.vinery.core.Vinery;

public final class ArmorMaterialRegistry {
    public static final int BASE_DURABILITY = 5;
    public static final ResourceKey<EquipmentAsset> WINEMAKER_ASSET =
            ResourceKey.create(EquipmentAssets.ROOT_ID, Vinery.identifier("winemaker"));

    public static final ArmorMaterial WINEMAKER_ARMOR = new ArmorMaterial(
            BASE_DURABILITY,
            ArmorMaterials.LEATHER.defense(),
            ArmorMaterials.LEATHER.enchantmentValue(),
            SoundEvents.ARMOR_EQUIP_LEATHER,
            ArmorMaterials.LEATHER.toughness(),
            ArmorMaterials.LEATHER.knockbackResistance(),
            ArmorMaterials.LEATHER.repairIngredient(),
            WINEMAKER_ASSET
    );

    private ArmorMaterialRegistry() {
    }
}
