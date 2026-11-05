package net.satisfy.vinery.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.satisfy.vinery.client.VineryClient;

/**
 * Fabric client entrypoint for Vinery.
 * Keeps platform registration in the shared client initializer.
 */
public final class VineryClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        VineryClient.preInitClient();
        VineryClient.onInitializeClient();
    }
}
