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

public class WinemakerChestItem extends Item {
    private final Identifier chestplateTexture;

    public WinemakerChestItem(Properties properties, Identifier chestplateTexture) {
        super(properties);
        this.chestplateTexture = chestplateTexture;
    }

    public Identifier getChestplateTexture() {
        return chestplateTexture;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        if (Minecraft.getInstance().level != null && Minecraft.getInstance().level.isClientSide()) {
            ArmorRegistryClient.appendToolTip(tooltip);
        }
    }
}
