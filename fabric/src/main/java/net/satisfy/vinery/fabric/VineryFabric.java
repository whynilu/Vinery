package net.satisfy.vinery.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.resources.Identifier;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Compostable;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;
import net.satisfy.vinery.core.registry.ObjectRegistry;

import java.util.List;
import net.satisfy.vinery.core.Vinery;
import net.satisfy.vinery.fabric.config.VineryFabricConfig;
import net.satisfy.vinery.fabric.core.world.VineryBiomeModification;

import java.util.Optional;

public class VineryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        AutoConfig.register(VineryFabricConfig.class, GsonConfigSerializer::new);
        registerCompostableComponents();

        Vinery.init();
        VineryBiomeModification.init();
        Vinery.commonSetup();


        Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(Vinery.MOD_ID);
        modContainer.ifPresent(container -> ResourceManagerHelper.registerBuiltinResourcePack(
                Identifier.fromNamespaceAndPath(Vinery.MOD_ID, "bushy_leaves"),
                container,
                ResourcePackActivationType.NORMAL
        ));
    }
}