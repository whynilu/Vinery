package net.satisfy.vinery.core.util;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.component.Consumables;

public class WineSettings {
    private final Properties properties;
    private final int baseDuration;

    public WineSettings(Holder<MobEffect> effect, int duration, int strength) {
        this.baseDuration = duration;
        FoodProperties food = new FoodProperties.Builder().alwaysEdible().build();
        this.properties = new Properties().food(food, Consumables.defaultDrink().build());
    }

    public Properties getProperties() {
        return properties;
    }

    public int getBaseDuration() {
        return baseDuration;
    }
}
