package fr.royalprog.royalsui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.royalprog.royalsui.RoyaLsUIClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.awt.*;

public class DragAndDropWidget extends ClickableWidget {

    private final Color _wcolor;
    private final String _name;

    private final int _screenHeight;
    private final int _screenWidth;

    private double mouseDeltaX;
    private double mouseDeltaY;

    private int value;

    private final TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

    private static final Identifier TEXTURE = new Identifier(RoyaLsUIClient.MOD_ID, "textures/gui/sprites/border2.png");
    public DragAndDropWidget(int x, int y, int width, int height, String name, Color wcolor, int screenWidth, int screenHeight) {
        super(x, y, width, height, Text.literal(name));
        _wcolor = wcolor;
        _name = name;
        _screenHeight = screenHeight;
        _screenWidth = screenWidth;
    }

    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        RenderSystem.setShaderColor(_wcolor.getRed(), _wcolor.getGreen(), _wcolor.getBlue(), 1.0F);
        context.drawTexture(TEXTURE, getX(), getY(), 0, 0, width, height, width, height);

//        context.drawText(textRenderer, _name, getX() + ((width / 2) - textRenderer.getWidth(_name) / 2), getY() + 5, 0xFFFFFF, false);
//        context.fill(RenderLayer.getGui(), getX(), getY(), width, height, _wcolor.getRGB()); // why doesn't it work ???

        /*
        context.drawBorder(...); //or fill


        Not working ? maybe because of tryDraw ?
         */
        context.drawBorder(0, 0, 100, 100, 0xFFFFFF);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    private void setValueFromMouse(double mouseX, double mouseY) {
        setX((int)MathHelper.clamp(mouseX - mouseDeltaX, 0, _screenWidth - width));
        setY((int) MathHelper.clamp(mouseY - mouseDeltaY, 0, _screenHeight - height));
    }

//    private void setValue(double mouseX, mouse) {
//        setX();
//    }

    protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
        this.setValueFromMouse(mouseX, mouseY);
        super.onDrag(mouseX, mouseY, deltaX, deltaY);
    }

    public void playDownSound(SoundManager soundManager) {
    }

    public void onRelease(double mouseX, double mouseY) {
        super.playDownSound(MinecraftClient.getInstance().getSoundManager());
    }

    public void onClick(double mouseX, double mouseY) {
        mouseDeltaX = mouseX - getX();
        mouseDeltaY = mouseY - getY();
    }

}
