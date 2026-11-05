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

public class WinemakerBootsItem extends Item {
    private final Identifier bootsTexture;

    public WinemakerBootsItem(Properties properties, Identifier bootsTexture) {
        super(properties);
        this.bootsTexture = bootsTexture;
    }

    public Identifier getBootsTexture() {
        return bootsTexture;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable TooltipContext tooltipContext, @NotNull List<Component> list, @NotNull TooltipFlag tooltipFlag) {
        if (Minecraft.getInstance().level != null && Minecraft.getInstance().level.isClientSide()) {
            ArmorRegistryClient.appendToolTip(list);
        }
    }
}
