package fr.royalprog.royalsui.render;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.royalprog.royalsui.RoyaLsUIClient;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;

public class ArmorStatusRender implements HudRenderCallback {

    private final MinecraftClient minecraft;
    private final TextRenderer textRenderer;
    private static final Identifier part = new Identifier(RoyaLsUIClient.MOD_ID, "textures/gui/sprites/cancel.png");
    public ArmorStatusRender() {
        minecraft = MinecraftClient.getInstance();
        textRenderer = minecraft.textRenderer;
    }

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
//        drawContext.drawGuiTexture(part, 0, 0, 30, 30);
        RenderSystem.setShaderTexture(0, part);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getRenderTypeTextProgram);
        drawContext.drawTexture(part, 0, 0, 0, 0, 12, 12, 12, 12);
    }
}
