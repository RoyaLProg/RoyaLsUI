package fr.royalprog.royalsui.gui.screen;

import fr.royalprog.royalsui.RoyaLsUIClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class SettingsScreen extends Screen {

    public SettingsScreen(Screen parent) {
        super(Text.literal("Royalsui settings"));
        this.parent = parent;
    }

    private final Screen parent;
    private String text;
    public ButtonWidget button1;

    private void changeText() {
        if (text.equals("1"))
            text = "2";
        else if (text.equals("2"))
            text = "1";

        System.out.println("text : " + text);
    }

    @Override
    protected void init() {
        text = "1";

        button1 = ButtonWidget.builder(Text.literal("Button : " + text), button -> {
                    changeText();
                })
                .dimensions(width / 2 - 400, 20, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button1")))
                .build();
        addDrawableChild(button1);
    }

    @Override
    public void close() {
        client.setScreen(parent);
    }
}
