package net.kyrptonaught.commandlog.config;

import blue.endless.jankson.Comment;

import java.util.ArrayList;
import java.util.List;

public class ConfigOptions {

    @Comment("https://www.google.com/search?q=java+datetime+format")
    public String DateTimeFormat = "M-dd-yyyy hh:mm:ss";
    @Comment("List of commands you'd like to log, ie: \"/kill\"")
    public List<String> commands = new ArrayList<>();
}
