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
import net.satisfy.vinery.core.item.WinemakerLegsItem;

/**
 * Submits the winemaker leggings using the current humanoid render state.
 *
 * <p>The old renderer depended on the legacy client-only ArmorRegistryClient,
 * which is deliberately excluded from the common module during the 26.3 port.
 * The 26.3 armor API submits model parts instead of drawing to a vertex buffer.
 */
public final class WinemakerLeggingsRenderer implements ArmorRenderer {
    @Override
    public void render(PoseStack poseStack,
                       SubmitNodeCollector submitNodeCollector,
                       ItemStack stack,
                       HumanoidRenderState renderState,
                       EquipmentSlot slot,
                       int light,
                       HumanoidModel<HumanoidRenderState> contextModel) {
        if (!(stack.getItem() instanceof WinemakerLegsItem leggings)) {
            return;
        }

        RenderType renderType = RenderType.entityCutoutNoCull(leggings.getLeggingsTexture());
        poseStack.pushPose();
        poseStack.scale(1.08F, 1.08F, 1.08F);
        poseStack.translate(0.0F, -0.095F, 0.0F);
        submitNodeCollector.submitModelPart(
                contextModel.rightLeg, poseStack, renderType, light,
                OverlayTexture.NO_OVERLAY, null
        );
        submitNodeCollector.submitModelPart(
                contextModel.leftLeg, poseStack, renderType, light,
                OverlayTexture.NO_OVERLAY, null
        );
        poseStack.popPose();
    }
}
