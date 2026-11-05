package net.satisfy.vinery.core.item;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.satisfy.vinery.core.registry.ArmorRegistryClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WinemakerLegsItem extends Item {
    private final Identifier leggingsTexture;

    public WinemakerLegsItem(Properties properties, Identifier leggingsTexture) {
        super(properties);
        this.leggingsTexture = leggingsTexture;
    }

    public Identifier getLeggingsTexture() {
        return leggingsTexture;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable TooltipContext ctx, @NotNull List<Component> tooltip, @NotNull TooltipFlag context) {
        if (Minecraft.getInstance().level != null && Minecraft.getInstance().level.isClientSide()) {
            ArmorRegistryClient.appendToolTip(tooltip);
        }
    }
}
