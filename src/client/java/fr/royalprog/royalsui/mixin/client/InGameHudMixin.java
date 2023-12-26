package fr.royalprog.royalsui.mixin.client;

import fr.royalprog.royalsui.render.ArmorStatusRender;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At("RETURN"), cancellable = true)
    public void onRender (DrawContext context, float tickDelta, CallbackInfo info) {
        ArmorStatusRender asr = new ArmorStatusRender();

        asr.render(context, 0, 0);
    }
}
