package net.satisfy.vinery.fabric.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.satisfy.vinery.client.model.StrawHatModel;
import net.satisfy.vinery.core.item.WinemakerHelmetItem;

/**
 * Renders the dedicated Vinery straw-hat mesh, rather than reusing the
 * vanilla head cube. The mesh is baked from StrawHatModel's registered layer.
 */
public final class StrawHatRenderer implements ArmorRenderer {
    private ModelPart hatPart;

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

        if (this.hatPart == null) {
            this.hatPart = Minecraft.getInstance().getEntityModels()
                    .bakeLayer(StrawHatModel.LAYER_LOCATION)
                    .getChild("top_part");
        }

        this.hatPart.copyFrom(contextModel.head);
        submitNodeCollector.submitModelPart(
                this.hatPart,
                poseStack,
                RenderType.entityCutoutNoCull(hat.getHatTexture()),
                light,
                OverlayTexture.NO_OVERLAY,
                null
        );
    }
}
