package fr.royalprog.royalsui.render;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.royalprog.royalsui.RoyaLsUIClient;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.lang.reflect.Array;

public class ArmorStatusRender implements HudRenderCallback {

    private final MinecraftClient minecraft;
    private final TextRenderer textRenderer;

    private static final Identifier part = new Identifier(RoyaLsUIClient.MOD_ID, "textures/gui/sprites/cancel.png");
    public ArmorStatusRender() {
        minecraft = MinecraftClient.getInstance();
        textRenderer = minecraft.textRenderer;
    }

    public int renderPart(DrawContext drawContext, ItemStack item, Identifier part, int xoffset, int yoffset) {
        if (item.isEmpty())
            return 0;
        Color color = new Color(item.getItemBarColor());
        RenderSystem.setShaderTexture(0, part);
        RenderSystem.setShaderColor(color.getRed(), color.getGreen(), color.getBlue(), 1.0F);
        RenderSystem.setShader(GameRenderer::getRenderTypeTextProgram);
        drawContext.drawTexture(part, 0 + xoffset, 0 + yoffset, 0, 0, 12, 12, 12, 12);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        return (15);
    }

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
//        drawContext.drawGuiTexture(part, 0, 0, 30, 30);
        ClientPlayerEntity player = minecraft.player;
        Identifier[] parts = {part, part, part, part};
        int offset = 0;

        for (int i = 0; i < parts.length; i++)
            offset += renderPart(drawContext, player.getInventory().getArmorStack(i), parts[i], 0, offset);
    }
}
