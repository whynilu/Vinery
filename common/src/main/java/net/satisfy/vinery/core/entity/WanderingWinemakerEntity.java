package net.satisfy.vinery.core.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.wanderingtrader.WanderingTrader;
import net.minecraft.world.level.Level;

/** Wandering winemaker; its legacy in-code offers were removed with the 26.1 data-driven trade API. */
public class WanderingWinemakerEntity extends WanderingTrader {
    public WanderingWinemakerEntity(EntityType<? extends WanderingWinemakerEntity> entityType, Level level) {
        super(entityType, level);
    }
}
