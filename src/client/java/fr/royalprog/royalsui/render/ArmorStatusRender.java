package fr.royalprog.royalsui.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class ArmorStatusRender {

    private final MinecraftClient minecraft;
    private final TextRenderer textRenderer;

    public ArmorStatusRender() {
        minecraft = MinecraftClient.getInstance();
        textRenderer = minecraft.textRenderer;
    }

    public void render (DrawContext context, int x, int y)
    {
        context.drawText(textRenderer, "It is alive ! Or is it ?", x, y, 0xFF0000, false);
    }
}
