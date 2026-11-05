package net.satisfy.vinery.client.util.fabric;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.Identifier;

public class ExtraModelsImpl {
    public static BakedModel get(Identifier id) {
        return Minecraft.getInstance().getModelManager().getModel(id);
    }
}
