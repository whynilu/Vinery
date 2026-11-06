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
    private static void registerCompostableComponents() {
        DefaultItemComponentEvents.MODIFY.register(context -> {
            List<Item> compostables = List.of(
                    ObjectRegistry.WHITE_GRAPE.get(), ObjectRegistry.WHITE_GRAPE_SEEDS.get().asItem(),
                    ObjectRegistry.RED_GRAPE.get(), ObjectRegistry.RED_GRAPE_SEEDS.get().asItem(),
                    ObjectRegistry.DARK_CHERRY_LEAVES.get().asItem(), ObjectRegistry.GRAPEVINE_LEAVES.get().asItem(),
                    ObjectRegistry.CHERRY.get(), ObjectRegistry.ROTTEN_CHERRY.get(),
                    ObjectRegistry.APPLE_TREE_SAPLING.get().asItem(), ObjectRegistry.APPLE_LEAVES.get().asItem(),
                    ObjectRegistry.DARK_CHERRY_SAPLING.get().asItem(), ObjectRegistry.APPLE_MASH.get(),
                    ObjectRegistry.STRAW_HAT.get(), ObjectRegistry.JUNGLE_RED_GRAPE_SEEDS.get().asItem(),
                    ObjectRegistry.JUNGLE_RED_GRAPE.get(), ObjectRegistry.JUNGLE_WHITE_GRAPE_SEEDS.get().asItem(),
                    ObjectRegistry.JUNGLE_WHITE_GRAPE.get(), ObjectRegistry.TAIGA_RED_GRAPE_SEEDS.get().asItem(),
                    ObjectRegistry.TAIGA_RED_GRAPE.get(), ObjectRegistry.TAIGA_WHITE_GRAPE_SEEDS.get().asItem(),
                    ObjectRegistry.TAIGA_WHITE_GRAPE.get(), ObjectRegistry.SAVANNA_RED_GRAPE_SEEDS.get().asItem(),
                    ObjectRegistry.SAVANNA_RED_GRAPE.get(), ObjectRegistry.SAVANNA_WHITE_GRAPE_SEEDS.get().asItem(),
                    ObjectRegistry.SAVANNA_WHITE_GRAPE.get()
            );
            Compostable compostable = new Compostable(ResolvableInt.fromKey(
                    ResourceKey.create(Registries.CONTEXT_INT_PROVIDER, Vinery.identifier("compost_40_percent"))));
            context.modify(compostables, (builder, item) -> builder.set(DataComponents.COMPOSTABLE, compostable));
        });
    }

}