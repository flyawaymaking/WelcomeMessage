package com.flyaway.welcomemessage;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private final WelcomeMessage plugin;
    private FileConfiguration config;

    private String welcomeMessage;
    private String firstTimeMessage;
    private String quitMessage;

    public ConfigManager(WelcomeMessage plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
        loadMessages();
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        config = plugin.getConfig();
        loadMessages();
    }

    private void loadMessages() {
        welcomeMessage = config.getString("welcome-message", "<green>зашёл на сервер!");
        firstTimeMessage = config.getString("first-time-message", "<green>зашёл на сервер впервые!");
        quitMessage = config.getString("quit-message", "<red>вышел с сервера.");
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public String getFirstTimeMessage() {
        return firstTimeMessage;
    }

    public String getQuitMessage() {
        return quitMessage;
    }

    public String getMessage(String key) {
        return config.getString("messages." + key, "<red>message-" + key + " not found");
    }
}
