package net.satisfy.vinery.core.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

/**
 * Persistent ageing data for a bottle of wine.
 *
 * @param brewedDay world day on which the bottle was created
 * @param daysPerYear number of in-game days in a wine year
 * @param yearsPerEffectLevel wine years required for each effect level
 * @param startDuration initial effect duration in ticks
 * @param durationPerYear additional effect duration per wine year
 * @param maxDuration maximum effect duration in ticks
 * @param maxLevel maximum effect amplifier
 */
public record WineYearComponent(
        int brewedDay,
        int daysPerYear,
        int yearsPerEffectLevel,
        int startDuration,
        int durationPerYear,
        int maxDuration,
        int maxLevel
) {
    public static final Codec<WineYearComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("brewed_day").forGetter(WineYearComponent::brewedDay),
            Codec.INT.fieldOf("days_per_year").forGetter(WineYearComponent::daysPerYear),
            Codec.INT.fieldOf("years_per_effect_level").forGetter(WineYearComponent::yearsPerEffectLevel),
            Codec.INT.fieldOf("start_duration").forGetter(WineYearComponent::startDuration),
            Codec.INT.fieldOf("duration_per_year").forGetter(WineYearComponent::durationPerYear),
            Codec.INT.fieldOf("max_duration").forGetter(WineYearComponent::maxDuration),
            Codec.INT.fieldOf("max_level").forGetter(WineYearComponent::maxLevel)
    ).apply(instance, WineYearComponent::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, WineYearComponent> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_INT, WineYearComponent::brewedDay,
                    ByteBufCodecs.VAR_INT, WineYearComponent::daysPerYear,
                    ByteBufCodecs.VAR_INT, WineYearComponent::yearsPerEffectLevel,
                    ByteBufCodecs.VAR_INT, WineYearComponent::startDuration,
                    ByteBufCodecs.VAR_INT, WineYearComponent::durationPerYear,
                    ByteBufCodecs.VAR_INT, WineYearComponent::maxDuration,
                    ByteBufCodecs.VAR_INT, WineYearComponent::maxLevel,
                    WineYearComponent::new
            );
}
