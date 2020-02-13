package net.kyrptonaught.commandlog.config;

import io.github.prospector.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.StringListListEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyrptonaught.commandlog.CommandLogMod;
import net.minecraft.client.gui.screen.Screen;

import java.util.ArrayList;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {

    @Override
    public String getModId() {
        return CommandLogMod.MOD_ID;
    }

    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return (screen) -> {
            ConfigBuilder builder = ConfigBuilder.create().setParentScreen(screen).setTitle("Command Logger Config");
        builder.setSavingRunnable(() -> CommandLogMod.config.saveConfig());
            ConfigOptions options = CommandLogMod.config.getConfig();
        ConfigCategory category = builder.getOrCreateCategory("key.commandlog.config.category.main");
        ConfigEntryBuilder entryBuilder = ConfigEntryBuilder.create();
        category.addEntry(entryBuilder.startStrField("key.commandlog.config.dateTime", options.DateTimeFormat).setDefaultValue("M-dd-yyyy hh:mm:ss").setSaveConsumer(val -> options.DateTimeFormat = val).build());
        category.addEntry(entryBuilder.startStrList("key.commandlog.config.commands", options.commands).setSaveConsumer(val ->  options.commands = val).setDefaultValue(new ArrayList<>())
                .setCreateNewInstance(baseListEntry -> new StringListListEntry.StringListCell("/", (StringListListEntry) baseListEntry)).build());

        return builder.build();
    };
}
}