package net.satisfy.vinery.core.mixin;

import net.minecraft.world.entity.npc.wanderingtrader.WanderingTraderSpawner;
import org.spongepowered.asm.mixin.Mixin;

/** Custom winemaker trader spawning is disabled pending the 26.3 spawner API port. */
@Mixin(WanderingTraderSpawner.class)
public abstract class WanderingTraderSpawnerMixin { }
