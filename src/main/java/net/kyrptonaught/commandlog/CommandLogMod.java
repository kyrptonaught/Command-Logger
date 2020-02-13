package net.kyrptonaught.commandlog;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.kyrptonaught.commandlog.config.ConfigManager;

import java.io.*;
import java.nio.file.Files;

public class CommandLogMod implements ClientModInitializer {
    public static final String MOD_ID = "commandlog";
    private static final File dir = new File(FabricLoader.getInstance().getGameDirectory()+ "/commandlogger");
    private static final File writeFile = new File(dir + "/log.txt");
    public static ConfigManager config = new ConfigManager();
    public static void writeToFile(String command) {
        try {
          BufferedWriter out = new BufferedWriter(new FileWriter(writeFile, true));
            out.append(command+"\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onInitializeClient() {
        if (!Files.exists(dir.toPath())) {
            try {
                Files.createDirectories(dir.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config.loadConfig();
    }
}