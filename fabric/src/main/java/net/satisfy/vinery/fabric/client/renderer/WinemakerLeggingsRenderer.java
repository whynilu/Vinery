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
import net.satisfy.vinery.client.model.WinemakerLeggingsModel;
import net.satisfy.vinery.core.item.WinemakerLegsItem;

/** Renders the original custom Vinemaker leggings mesh against the animated leg pose. */
public final class WinemakerLeggingsRenderer implements ArmorRenderer {
    private WinemakerLeggingsModel model;

    @Override
    public void render(PoseStack poseStack, SubmitNodeCollector collector, ItemStack stack,
                       HumanoidRenderState state, EquipmentSlot slot, int light,
                       HumanoidModel<HumanoidRenderState> contextModel) {
        if (!(stack.getItem() instanceof WinemakerLegsItem leggings)) return;

        if (model == null) {
            ModelPart root = Minecraft.getInstance().getEntityModels()
                    .bakeLayer(WinemakerLeggingsModel.LAYER_LOCATION);
            model = new WinemakerLeggingsModel(root);
        }
        model.copyLegs(contextModel.rightLeg, contextModel.leftLeg);

        var renderType = RenderTypes.armorCutoutNoCull(leggings.getLeggingsTexture());
        poseStack.pushPose();
        poseStack.scale(1.08F, 1.08F, 1.08F);
        poseStack.translate(0.0F, -0.095F, 0.0F);
        collector.submitModelPart(model.rightLegPart(), poseStack, renderType, light, OverlayTexture.NO_OVERLAY, null);
        collector.submitModelPart(model.leftLegPart(), poseStack, renderType, light, OverlayTexture.NO_OVERLAY, null);
        poseStack.popPose();
    }
}
