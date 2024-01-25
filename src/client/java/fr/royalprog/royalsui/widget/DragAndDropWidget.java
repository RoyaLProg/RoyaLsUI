package fr.royalprog.royalsui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.royalprog.royalsui.RoyaLsUIClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;

public class DragAndDropWidget extends ClickableWidget {

    private final Color _wcolor;
    private final String _name;

    private static final Identifier TEXTURE = new Identifier(RoyaLsUIClient.MOD_ID, "textures/gui/sprites/borders.png");
    public DragAndDropWidget(int x, int y, int width, int height, String name, Color wcolor) {
        super(x, y, width, height, Text.literal(name));
        _wcolor = wcolor;
        _name = name;
    }


    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        RenderSystem.setShaderColor(_wcolor.getRed(), _wcolor.getGreen(), _wcolor.getBlue(), 1.0F);
        context.drawTexture(TEXTURE, getX(), getY(), 0, 0, width, height, width, height);
        context.fill(RenderLayer.getGui(), getX(), getY(), width, height, _wcolor.getRGB());
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    private void setValueFromMouse(double mouseX) {
        this.setValue((mouseX - (double)(this.getX() + 4)) / (double)(this.width - 8));
    }

    private void setValue(double value) {
//        double d = this.value;
//        this.value = MathHelper.clamp(value, 0.0, 1.0);
//        if (d != this.value) {
//            this.applyValue();
//        }
//
//        this.updateMessage();
    }

    protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
        this.setValueFromMouse(mouseX);
        super.onDrag(mouseX, mouseY, deltaX, deltaY);
    }

    public void playDownSound(SoundManager soundManager) {
    }

    public void onRelease(double mouseX, double mouseY) {
        super.playDownSound(MinecraftClient.getInstance().getSoundManager());
    }
}
