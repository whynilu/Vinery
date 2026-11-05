package net.satisfy.vinery.core.item;

import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WinemakerHelmetItem extends Item {
    private final Identifier hatTexture;

    public WinemakerHelmetItem(Properties properties, Identifier hatTexture) {
        super(properties);
        this.hatTexture = hatTexture;
    }

    public Identifier getHatTexture() {
        return hatTexture;
    }

}
