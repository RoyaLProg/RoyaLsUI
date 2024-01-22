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

public class ArmorStatusRender implements HudRenderCallback {

    private final MinecraftClient minecraft;
    private final TextRenderer textRenderer;

//    private static final Identifier part = new Identifier(RoyaLsUIClient.MOD_ID, "textures/gui/sprites/cancel.png");
    private static final Identifier boot = new Identifier(RoyaLsUIClient.MOD_ID, "textures/gui/sprites/armor_boots.png");
    private static final Identifier legging = new Identifier(RoyaLsUIClient.MOD_ID, "textures/gui/sprites/armor_leggings.png");
    private static final Identifier chestplate = new Identifier(RoyaLsUIClient.MOD_ID, "textures/gui/sprites/armor_chestplate.png");
    private static final Identifier helmet = new Identifier(RoyaLsUIClient.MOD_ID, "textures/gui/sprites/armor_helmet.png");
    public ArmorStatusRender() {
        minecraft = MinecraftClient.getInstance();
        textRenderer = minecraft.textRenderer;
    }

    public int renderPart(DrawContext drawContext, ItemStack item, Identifier part, int xoffset, int yoffset) {
        if (item.isEmpty())
            return 0;
        Color color = new Color(item.getItemBarColor());
        float[] colors = color.getRGBComponents(null);
        RenderSystem.setShaderTexture(0, part);
        RenderSystem.setShaderColor(colors[0], colors[1], colors[2], 1.0F);
        RenderSystem.setShader(GameRenderer::getRenderTypeTextProgram);
        drawContext.drawTexture(part, drawContext.getScaledWindowWidth() - 16 + xoffset, 0 + yoffset, 0, 0, 16, 16, 16, 16);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        return (18);
    }

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
//        drawContext.drawGuiTexture(part, 0, 0, 30, 30);
        ClientPlayerEntity player = minecraft.player;
        Identifier[] parts = {boot, legging, chestplate, helmet};
        int offset = 0;

        for (int i = parts.length - 1; i >= 0; i--)
            offset += renderPart(drawContext, player.getInventory().getArmorStack(i), parts[i], 0, offset);
//        Color color = new Color(player.getInventory().getArmorStack(0).getItemBarColor());
//        String text = "color r :" + color.getRed() + " g : " + color.getGreen() + " b : " + color.getBlue() + "!";
//        drawContext.drawText(minecraft.textRenderer, text, 0, offset, 0xFFFFFF, false);
    }
}
