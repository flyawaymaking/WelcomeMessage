package com.flyaway.welcomemessage;

import org.bukkit.plugin.java.JavaPlugin;

public class WelcomeMessage extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigManager configManager = new ConfigManager(this);
        MessageManager messageManager = new MessageManager(this);

        getServer().getPluginManager().registerEvents(new PlayerListener(configManager, messageManager), this);

        ReloadCommand reloadCommand = new ReloadCommand(configManager, messageManager);
        getCommand("welcomemessage").setExecutor(reloadCommand);
        getCommand("welcomemessage").setTabCompleter(reloadCommand);

        getLogger().info("WelcomeMessage включён!");
    }

    @Override
    public void onDisable() {
        getLogger().info("WelcomeMessage выключен!");
    }
}
