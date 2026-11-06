package net.satisfy.vinery.fabric.client;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.satisfy.vinery.client.model.StrawHatModel;
import net.satisfy.vinery.client.model.WinemakerBootsModel;
import net.satisfy.vinery.client.model.WinemakerChestplateModel;
import net.satisfy.vinery.client.model.WinemakerLeggingsModel;

/** Registers the custom Vinery armor meshes before the entity model set is baked. */
public final class VineryArmorModelLayers {
    private VineryArmorModelLayers() {}

    public static void register() {
        ModelLayerRegistry.registerModelLayer(StrawHatModel.LAYER_LOCATION, StrawHatModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(WinemakerBootsModel.LAYER_LOCATION, WinemakerBootsModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(WinemakerChestplateModel.LAYER_LOCATION, WinemakerChestplateModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(WinemakerLeggingsModel.LAYER_LOCATION, WinemakerLeggingsModel::createBodyLayer);
    }
}
