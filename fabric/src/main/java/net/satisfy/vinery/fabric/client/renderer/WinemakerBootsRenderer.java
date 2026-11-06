package net.satisfy.vinery.fabric.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.satisfy.vinery.client.model.WinemakerBootsModel;
import net.satisfy.vinery.core.item.WinemakerBootsItem;

/**
 * Renders Vinery's custom boots geometry with the current humanoid leg pose.
 */
public final class WinemakerBootsRenderer implements ArmorRenderer {
    private WinemakerBootsModel<?> model;

    @Override
    public void render(PoseStack poseStack,
                       SubmitNodeCollector submitNodeCollector,
                       ItemStack stack,
                       HumanoidRenderState renderState,
                       EquipmentSlot slot,
                       int light,
                       HumanoidModel<HumanoidRenderState> contextModel) {
        if (!(stack.getItem() instanceof WinemakerBootsItem boots)) {
            return;
        }

        if (this.model == null) {
            ModelPart root = Minecraft.getInstance().getEntityModels()
                    .bakeLayer(WinemakerBootsModel.LAYER_LOCATION);
            this.model = new WinemakerBootsModel<>(root);
        }

        this.model.copyLegs(contextModel.rightLeg, contextModel.leftLeg);

        var renderType = RenderTypes.armorCutoutNoCull(boots.getBootsTexture());
        poseStack.pushPose();
        poseStack.scale(1.08F, 1.08F, 1.08F);
        poseStack.translate(0.0F, -0.125F, 0.0F);
        submitNodeCollector.submitModelPart(
                this.model.rightLegPart(), poseStack, renderType, light,
                OverlayTexture.NO_OVERLAY, null
        );
        submitNodeCollector.submitModelPart(
                this.model.leftLegPart(), poseStack, renderType, light,
                OverlayTexture.NO_OVERLAY, null
        );
        poseStack.popPose();
    }
}
