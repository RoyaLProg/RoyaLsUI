package fr.royalprog.royalsui.gui.screen;

import fr.royalprog.royalsui.config.ModConfigs;
import fr.royalprog.royalsui.widget.PositionSlider;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public class SettingsScreen extends Screen {

    public SettingsScreen(Screen parent) {
        super(Text.literal("Royalsui settings"));
        this.parent = parent;
    }

    private final Screen parent;
    public PositionSlider xSlider;
    public SliderWidget ySlider;

    @Override
    protected void init() {
        xSlider =  new PositionSlider(width / 2 - 100, height / 2 - 10, 200, ModConfigs.XPOS, 0, width - 16);

        addDrawableChild(xSlider);
    }

    @Override
    public void close() {
        ModConfigs.XPOS = xSlider.getValue();
        client.setScreen(parent);
    }
}
