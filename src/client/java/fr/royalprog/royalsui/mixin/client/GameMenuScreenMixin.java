package fr.royalprog.royalsui.mixin.client;

import fr.royalprog.royalsui.gui.screen.SettingsScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
    Screen settingScreen = new SettingsScreen(this);
    protected GameMenuScreenMixin(Text title) {
        super(title);
    }
    @Inject(at = @At("TAIL"), method = "initWidgets")
    protected void initWidgets(CallbackInfo info)
    {
        addDrawableChild(ButtonWidget.builder(Text.literal("RoyaL's UI settings"), button -> {
                    client.setScreen(settingScreen);
                })
                .dimensions(0, 0, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button1")))
                .build());
    }

}
