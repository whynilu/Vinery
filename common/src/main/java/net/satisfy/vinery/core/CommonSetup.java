package net.satisfy.vinery.core;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.satisfy.vinery.core.registry.CompostableRegistry;


import static net.satisfy.vinery.core.registry.ObjectRegistry.*;

public final class CommonSetup {
    private CommonSetup() {
    }

    public static void init() {
        registerFlammables();
    }

    private static void registerFlammables() {
        addFlammable(5, 20, DARK_CHERRY_PLANKS.get(), DARK_CHERRY_SLAB.get(), DARK_CHERRY_STAIRS.get(), DARK_CHERRY_FENCE.get(),
                DARK_CHERRY_FENCE_GATE.get());
        addFlammable(5, 5, STRIPPED_DARK_CHERRY_LOG.get(), DARK_CHERRY_LOG.get(), APPLE_LOG.get(),
                STRIPPED_DARK_CHERRY_WOOD.get(), DARK_CHERRY_WOOD.get(), APPLE_WOOD.get());
        addFlammable(30, 60, DARK_CHERRY_LEAVES.get(), GRAPEVINE_LEAVES.get(), APPLE_LEAVES.get());
    }

    private static void addFlammable(int burnOdd, int igniteOdd, Block... blocks) {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        for (Block block : blocks) {
            fireBlock.setFlammable(block, burnOdd, igniteOdd);
        }
    }

}
