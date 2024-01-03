package fr.royalprog.royalsui;

import fr.royalprog.royalsui.render.ArmorStatusRender;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RoyaLsUIClient implements ClientModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("royalsui");
	public static final String MOD_ID = "royalsui";
	@Override
	public void onInitializeClient() {
		LOGGER.info("STARTING ROYALSUI");
		HudRenderCallback.EVENT.register(new ArmorStatusRender());
	}
}