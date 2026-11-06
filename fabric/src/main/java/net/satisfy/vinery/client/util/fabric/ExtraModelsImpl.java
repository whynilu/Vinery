package net.satisfy.vinery.client.util.fabric;

import net.fabricmc.fabric.api.client.model.loading.v1.ExtraModelKey;
import net.fabricmc.fabric.api.client.model.loading.v1.FabricModelManager;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.SimpleUnbakedExtraModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.resources.Identifier;
import net.satisfy.vinery.core.Vinery;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Fabric 26.3 implementation for Vinery's additional block-state models.
 * Extra models are now retrieved through typed ExtraModelKeys rather than
 * looking them up directly by Identifier on the model manager.
 */
public final class ExtraModelsImpl {
    private static final Map<Identifier, ExtraModelKey<BlockStateModel>> MODEL_KEYS = createKeys();

    private ExtraModelsImpl() {
    }

    private static Map<Identifier, ExtraModelKey<BlockStateModel>> createKeys() {
        Map<Identifier, ExtraModelKey<BlockStateModel>> keys = new LinkedHashMap<>();
        for (String color : new String[]{"red", "white"}) {
            for (int stage = 1; stage <= 6; stage++) {
                Identifier id = Vinery.identifier("block/" + color + "_grapevine_pot_content_stage" + stage);
                keys.put(id, ExtraModelKey.create(id::toString));
            }
        }
        return Map.copyOf(keys);
    }

    /** Register before the client model manager performs its initial reload. */
    public static void register() {
        ModelLoadingPlugin.register(context -> MODEL_KEYS.forEach((id, key) ->
                context.addModel(key, SimpleUnbakedExtraModel.blockStateModel(id))));
    }

    public static BlockStateModel get(Identifier id) {
        ExtraModelKey<BlockStateModel> key = MODEL_KEYS.get(id);
        if (key == null) {
            throw new IllegalArgumentException("Unregistered Vinery extra model: " + id);
        }

        BlockStateModel model = ((FabricModelManager) Minecraft.getInstance().getModelManager()).getModel(key);
        return Objects.requireNonNull(model, "Vinery extra model was not baked: " + id);
    }
}
