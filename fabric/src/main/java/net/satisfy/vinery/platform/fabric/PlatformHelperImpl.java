package net.satisfy.vinery.platform.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.satisfy.vinery.api.VineryPlugin;
import net.satisfy.vinery.fabric.config.VineryFabricConfig;
import net.satisfy.vinery.platform.PlatformHelper;

import java.util.List;
import java.util.function.Supplier;

/**
 * Fabric implementation for Vinery's platform configuration hooks.
 * Kept as a subclass for the service-provider declaration and as the
 * Architectury @ExpectPlatform implementation class.
 */
public final class PlatformHelperImpl extends PlatformHelper {
    public PlatformHelperImpl() {}

    private static VineryFabricConfig config() {
        return AutoConfig.getConfigHolder(VineryFabricConfig.class).getConfig();
    }

    public static int getTotalFermentationTime() { return config().blocks.totalFermentationTime; }
    public static int getMaxFluidLevel() { return config().blocks.maxFluidLevel; }
    public static int getMaxFluidIncrease() { return config().blocks.maxFluidIncrease; }
    public static int getApplePressMashingTime() { return config().blocks.applePressMashingTime; }
    public static int getApplePressFermentationTime() { return config().blocks.applePressFermentationTime; }
    public static double getCherryGrowthChance() { return config().blocks.cherryGrowthChance / 100.0; }
    public static double getAppleGrowthChance() { return config().blocks.appleGrowthChance / 100.0; }
    public static double getGrapeGrowthChance() { return config().blocks.grapeGrowthChance / 100.0; }
    public static double getGrapeGrowthMultiplier() { return config().blocks.grapeGrowthMultiplier; }
    public static double getCellarAgingMultiplier() { return config().wine.cellarAgingMultiplier; }
    public static int getGrapevinePotStomps() { return config().blocks.grapevinePotStomps; }
    public static int getGrapevinePotHeavyArmorBonus() { return config().blocks.grapevinePotHeavyArmorBonus; }
    public static boolean shouldShowGrapevinePotSplash() { return config().blocks.enableGrapevinePotSplash; }
    public static boolean shouldGrapevineLeavesGrow() { return config().blocks.enableGrapevineLeaves; }
    public static int getWineMaxLevel() { return config().wine.maxLevel; }
    public static List<? extends String> getWineEffects() { return config().wine.effects; }
    public static boolean isWineAgingEnabled() { return config().wine.enableWineAging; }
    public static boolean shouldWineAgeOnlyInStorage() { return config().wine.ageOnlyInStorage; }
    public static boolean infoTooltipsNeedStrawHat() { return config().infoTooltips.needStrawHat; }
    public static boolean showGrapevinePotInfo() { return config().infoTooltips.showGrapevinePotInfo; }
    public static boolean showStorageInfo() { return config().infoTooltips.showStorageInfo; }

    public static List<? extends String> getVillagerTrades(int level) {
        VineryFabricConfig.VillagerTradesSettings trades = config().villagerTrades;
        return switch (level) {
            case 1 -> trades.level1Trades;
            case 2 -> trades.level2Trades;
            case 3 -> trades.level3Trades;
            case 4 -> trades.level4Trades;
            case 5 -> trades.level5Trades;
            default -> List.of();
        };
    }

    public static int getWineDurationPerYear() { return config().wine.durationPerYear; }
    public static int getWineDaysPerYear() { return config().wine.daysPerYear; }
    public static int getWineYearsPerEffectLevel() { return config().wine.yearsPerEffectLevel; }
    public static int getWineMaxDuration() { return config().wine.maxDuration; }
    public static boolean shouldGiveEffect() { return config().effects.completionistBannerEffect.bannerGiveEffect; }
    public static boolean shouldShowTooltip() { return config().effects.completionistBannerEffect.bannerShowTooltip; }
    public static double getTraderSpawnChance() { return config().wanderingTrader.spawnChance / 100.0; }
    public static boolean shouldSpawnWithMules() { return config().wanderingTrader.spawnWithMules; }
    public static int getTraderSpawnDelay() { return config().wanderingTrader.spawnDelay; }

    public static <T extends Entity> Supplier<EntityType<T>> registerBoatType(
            String name, EntityType.EntityFactory<T> factory, MobCategory category,
            float width, float height, int clientTrackingRange) {
        throw new UnsupportedOperationException("Boat registration must be migrated to the Minecraft 26.3 registry API");
    }

    public static boolean reloadConfig() {
        return false;
    }

    public static List<VineryPlugin> getPlugins() {
        return List.of();
    }
}
