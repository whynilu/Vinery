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
import net.satisfy.vinery.client.model.WinemakerChestplateModel;
import net.satisfy.vinery.core.item.WinemakerChestItem;

/**
 * Renders Vinery's dedicated apron mesh using the current humanoid pose.
 */
public final class WinemakerChestplateRenderer implements ArmorRenderer {
    private WinemakerChestplateModel<?> model;

    @Override
    public void render(PoseStack poseStack,
                       SubmitNodeCollector submitNodeCollector,
                       ItemStack stack,
                       HumanoidRenderState renderState,
                       EquipmentSlot slot,
                       int light,
                       HumanoidModel<HumanoidRenderState> contextModel) {
        if (!(stack.getItem() instanceof WinemakerChestItem chestplate)) {
            return;
        }

        if (this.model == null) {
            ModelPart root = Minecraft.getInstance().getEntityModels()
                    .bakeLayer(WinemakerChestplateModel.LAYER_LOCATION);
            this.model = new WinemakerChestplateModel<>(root);
        }

        this.model.copyBody(
                contextModel.body,
                contextModel.leftArm,
                contextModel.rightArm,
                contextModel.leftLeg,
                contextModel.rightLeg
        );

        var renderType = RenderTypes.armorCutoutNoCull(chestplate.getChestplateTexture());
        poseStack.pushPose();
        poseStack.scale(1.075F, 1.075F, 1.075F);
        submitNodeCollector.submitModelPart(this.model.bodyPart(), poseStack, renderType, light, OverlayTexture.NO_OVERLAY, null);
        submitNodeCollector.submitModelPart(this.model.rightArmPart(), poseStack, renderType, light, OverlayTexture.NO_OVERLAY, null);
        submitNodeCollector.submitModelPart(this.model.leftArmPart(), poseStack, renderType, light, OverlayTexture.NO_OVERLAY, null);
        submitNodeCollector.submitModelPart(this.model.rightLegPart(), poseStack, renderType, light, OverlayTexture.NO_OVERLAY, null);
        submitNodeCollector.submitModelPart(this.model.leftLegPart(), poseStack, renderType, light, OverlayTexture.NO_OVERLAY, null);
        poseStack.popPose();
    }
}
