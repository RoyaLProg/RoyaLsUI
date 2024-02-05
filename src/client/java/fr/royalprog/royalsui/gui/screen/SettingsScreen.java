package fr.royalprog.royalsui.gui.screen;

import com.mojang.datafixers.util.Pair;
import fr.royalprog.royalsui.config.ModConfigs;
import fr.royalprog.royalsui.widget.DragAndDropWidget;
import fr.royalprog.royalsui.widget.PositionSlider;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.*;

public class SettingsScreen extends Screen {

    public SettingsScreen(Screen parent) {
        super(Text.literal("Royalsui settings"));
        this.parent = parent;
    }

    private final Screen parent;
    public PositionSlider xSlider;
    public PositionSlider ySlider;
    public DragAndDropWidget test;

    @Override
    protected void init() {
//        xSlider =  new PositionSlider(width / 2 - 100, height / 2 - 10 - 15, 200, ModConfigs.XPOS, 0, width - 16, "pos x");
//        ySlider =  new PositionSlider(width / 2 - 100, height / 2 + 5, 200, ModConfigs.YPOS, 0, height - (16 * 4), "pos y");
        test = new DragAndDropWidget(ModConfigs.XPOS, ModConfigs.YPOS, 18, 80, "armor", new Color(0, 0, 255, 0), width, height);
//        addDrawableChild(xSlider);
//        addDrawableChild(ySlider);
        addDrawableChild(test);
    }

    private void modifyConfig() {
        ModConfigs.XPOS = test.getX();
        ModConfigs.YPOS = test.getY();

        ModConfigs.modify(new Pair<>("armor.x", test.getX()));
        ModConfigs.modify(new Pair<>("armor.y", test.getY()));
    }

    private void saveConfig() {
        ModConfigs.save();
    }
    @Override
    public void close() {
        modifyConfig();
        saveConfig();
        client.setScreen(parent);
    }
}
