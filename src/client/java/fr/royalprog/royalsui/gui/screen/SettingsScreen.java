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
    public DragAndDropWidget test2;
    public DragAndDropWidget test3;

    @Override
    protected void init() {
//        xSlider =  new PositionSlider(width / 2 - 100, height / 2 - 10 - 15, 200, ModConfigs.XPOS, 0, width - 16, "pos x");
//        ySlider =  new PositionSlider(width / 2 - 100, height / 2 + 5, 200, ModConfigs.YPOS, 0, height - (16 * 4), "pos y");
        test = new DragAndDropWidget(0, 0, 100, 100, "test", new Color(0, 0, 255, 0), width, height);
        test2 = new DragAndDropWidget(0, 0, 100, 200, "test", new Color(0, 0, 255, 0), width, height);
        test3 = new DragAndDropWidget(0, 0, 200, 100, "test", new Color(0, 0, 255, 0), width, height);
//        addDrawableChild(xSlider);
//        addDrawableChild(ySlider);
        addDrawableChild(test);
        addDrawableChild(test2);
        addDrawableChild(test3);
    }

    private void modifyConfig() {
//        ModConfigs.XPOS = xSlider.getValue();
//        ModConfigs.YPOS = ySlider.getValue();
//
//        ModConfigs.modify(new Pair<>("armor.x", xSlider.getValue()));
//        ModConfigs.modify(new Pair<>("armor.y", ySlider.getValue()));
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
