package net.satisfy.vinery.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.satisfy.vinery.client.util.fabric.ExtraModelsImpl;

/** Fabric client entrypoint for client-side model setup. */
public final class VineryClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        VineryArmorModelLayers.register();
        ExtraModelsImpl.register();
    }
}
