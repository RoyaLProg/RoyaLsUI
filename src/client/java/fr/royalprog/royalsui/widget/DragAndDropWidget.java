package fr.royalprog.royalsui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.royalprog.royalsui.RoyaLsUIClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.awt.*;

public class DragAndDropWidget extends ClickableWidget {

    private final Color _wcolor;
    private final String _name;

    private int maxX;
    private int maxY;

    private double valueX;
    private double valueY;


    private final int _screenHeight;
    private final int _screenWidth;

    private double mouseDeltaX;
    private double mouseDeltaY;
    private int value;

    private final TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

    private static final Identifier TEXTURE = new Identifier(RoyaLsUIClient.MOD_ID, "textures/gui/sprites/border2.png");
    public DragAndDropWidget(int x, int y, int width, int height, String name, Color wcolor, int screenWidth, int screenHeight) {
        super(MathHelper.clamp(x, 0, screenWidth - width), MathHelper.clamp(y, 0, screenHeight - height), width, height, Text.literal(name));


        _wcolor = wcolor;
        _name = name;
        _screenHeight = screenHeight;
        _screenWidth = screenWidth;
        maxX = _screenWidth - width;
        maxY = _screenHeight - height;
        valueX = MathHelper.map(x, 0, maxX, 0, 1);
        valueY = MathHelper.map(y, 0, maxY, 0, 1);
        setX((int) (valueX * maxX));
        setY((int) (valueY * maxY));
    }

    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        RenderSystem.setShaderColor(_wcolor.getRed(), _wcolor.getGreen(), _wcolor.getBlue(), 1.0F);
        context.drawTexture(TEXTURE, (int) (valueX * maxX), (int) (valueY * maxY), 0, 0, width, height, width, height);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        if (this.isHovered())
            context.drawTooltip(textRenderer, Text.literal(_name).withColor(0xFFFFFF), mouseX, mouseY);
//        context.fill(RenderLayer.getGui(), getX(), getY(), width, height, _wcolor.getRGB()); // why doesn't it work ???

        /*
        context.drawBorder(...); //or fill


        Not working ? maybe because of tryDraw ?
         */
        context.fill(0, 0, 500, 500, -50,0xFFFFFF);
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    private void setValueFromMouse(double mouseX, double mouseY) {
        setX((int)MathHelper.clamp(mouseX - mouseDeltaX, 0, maxX));
        setY((int)MathHelper.clamp(mouseY - mouseDeltaY, 0, maxY));
        valueX = MathHelper.map(getX(), 0, maxX, 0, 1);
        valueY = MathHelper.map(getY(), 0, maxY, 0, 1);
    }

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
