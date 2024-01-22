package fr.royalprog.royalsui.render;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.royalprog.royalsui.RoyaLsUIClient;
import fr.royalprog.royalsui.config.ModConfigs;
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

    public int[] renderPart(DrawContext drawContext, ItemStack item, Identifier part, int xpos, int ypos, int[] offsets) {
        if (item.isEmpty())
            return offsets;
        Color color = new Color(item.getItemBarColor());
        float[] colors = color.getRGBComponents(null);
        RenderSystem.setShaderTexture(0, part);
        RenderSystem.setShaderColor(colors[0], colors[1], colors[2], 1.0F);
        RenderSystem.setShader(GameRenderer::getRenderTypeTextProgram);
        drawContext.drawTexture(part, xpos + offsets[0], ypos + offsets[1], 0, 0, 16, 16, 16, 16);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        offsets[1] += 18;
        return offsets;
    }

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
//        drawContext.drawGuiTexture(part, 0, 0, 30, 30);
        ClientPlayerEntity player = minecraft.player;
        Identifier[] parts = {boot, legging, chestplate, helmet};
        int xpos = ModConfigs.XPOS;
        int ypos = ModConfigs.YPOS;
        int[] offsets = {0, 0};

        for (int i = parts.length - 1; i >= 0; i--)
            offsets = renderPart(drawContext, player.getInventory().getArmorStack(i), parts[i], xpos, ypos, offsets);

    }
}
