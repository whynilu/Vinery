package net.satisfy.vinery.core.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

/** Custom food-effect hooks are disabled pending the 26.3 data-component port. */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin { }
