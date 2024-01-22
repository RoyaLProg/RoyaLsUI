package fr.royalprog.royalsui.config;

import com.mojang.datafixers.util.Pair;
import fr.royalprog.royalsui.RoyaLsUIClient;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static int XPOS;
    public static int YPOS;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(RoyaLsUIClient.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("armor.x", 0), "int");
        configs.addKeyValuePair(new Pair<>("armor.y", 0), "int");
    }

    private static void assignConfigs() {
        XPOS = CONFIG.getOrDefault("armor.x", 0);
        YPOS = CONFIG.getOrDefault("armor.y", 0);

        System.out.println("All " + configs.getConfigsList().size() + " configs have been set properly");
    }
}
