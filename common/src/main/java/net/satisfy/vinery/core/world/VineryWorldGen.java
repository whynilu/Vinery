package net.satisfy.vinery.core.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.satisfy.vinery.core.Vinery;

/** Keys for data-driven placed features. ConfiguredFeature was removed in Minecraft 26.3. */
public final class VineryWorldGen {
    public static final ResourceKey<PlacedFeature> TREE_CHERRY_PLACED_KEY = placed("tree_cherry");
    public static final ResourceKey<PlacedFeature> TREE_APPLE_PLACED_KEY = placed("tree_apple");
    public static final ResourceKey<PlacedFeature> RED_GRAPE_PATCH_CHANCE_KEY = placed("red_grape_bush_chance");
    public static final ResourceKey<PlacedFeature> WHITE_GRAPE_PATCH_CHANCE_KEY = placed("white_grape_bush_chance");
    public static final ResourceKey<PlacedFeature> TAIGA_RED_GRAPE_PATCH_CHANCE_KEY = placed("taiga_red_grape_bush_chance");
    public static final ResourceKey<PlacedFeature> TAIGA_WHITE_GRAPE_PATCH_CHANCE_KEY = placed("taiga_white_grape_bush_chance");
    public static final ResourceKey<PlacedFeature> SAVANNA_RED_GRAPE_PATCH_CHANCE_KEY = placed("savanna_red_grape_bush_chance");
    public static final ResourceKey<PlacedFeature> SAVANNA_WHITE_GRAPE_PATCH_CHANCE_KEY = placed("savanna_white_grape_bush_chance");
    public static final ResourceKey<PlacedFeature> JUNGLE_RED_GRAPE_PATCH_CHANCE_KEY = placed("jungle_red_grape_bush_chance");
    public static final ResourceKey<PlacedFeature> JUNGLE_WHITE_GRAPE_PATCH_CHANCE_KEY = placed("jungle_white_grape_bush_chance");
    private static ResourceKey<PlacedFeature> placed(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Vinery.identifier(name));
    }
}
