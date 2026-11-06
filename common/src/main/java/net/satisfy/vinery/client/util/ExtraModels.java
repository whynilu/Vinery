package net.satisfy.vinery.client.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.resources.Identifier;
import net.satisfy.vinery.core.Vinery;

import java.util.ArrayList;
import java.util.List;

public final class ExtraModels {
    public static final int POT_STAGES = 6;
    private static final Identifier[] RED_POT_CONTENT = potContent("red");
    private static final Identifier[] WHITE_POT_CONTENT = potContent("white");

    private ExtraModels() {
    }

    public static List<Identifier> all() {
        List<Identifier> models = new ArrayList<>(List.of(RED_POT_CONTENT));
        models.addAll(List.of(WHITE_POT_CONTENT));
        return models;
    }

    public static Identifier potContent(boolean red, int stage) {
        return (red ? RED_POT_CONTENT : WHITE_POT_CONTENT)[stage - 1];
    }

    private static Identifier[] potContent(String color) {
        Identifier[] models = new Identifier[POT_STAGES];
        for (int stage = 1; stage <= POT_STAGES; stage++) {
            models[stage - 1] = Vinery.identifier("block/" + color + "_grapevine_pot_content_stage" + stage);
        }
        return models;
    }

    @ExpectPlatform
    public static BlockStateModel get(Identifier id) {
        throw new AssertionError();
    }
}
