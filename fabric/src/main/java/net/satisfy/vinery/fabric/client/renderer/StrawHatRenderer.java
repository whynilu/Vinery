package net.satisfy.vinery.fabric.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.satisfy.vinery.core.item.WinemakerHelmetItem;

/**
 * 26.3 armor renderer for Vinery's straw hat.
 *
 * The legacy custom-model registry is excluded from the common module during
 * the renderer migration. Reuse the humanoid head part so the hat follows the
 * wearer head transform, and submit it through the 26.3 render collector.
 */
public final class StrawHatRenderer implements ArmorRenderer {
    @Override
    public void render(PoseStack poseStack,
                       SubmitNodeCollector submitNodeCollector,
                       ItemStack stack,
                       HumanoidRenderState renderState,
                       EquipmentSlot slot,
                       int light,
                       HumanoidModel<HumanoidRenderState> contextModel) {
        if (!(stack.getItem() instanceof WinemakerHelmetItem hat)) {
            return;
        }

        RenderType renderType = RenderType.entityCutoutNoCull(hat.getHatTexture());
        poseStack.pushPose();
        poseStack.scale(1.05F, 1.05F, 1.05F);
        submitNodeCollector.submitModelPart(
                contextModel.head, poseStack, renderType, light,
                OverlayTexture.NO_OVERLAY, null
        );
        poseStack.popPose();
    }
}
