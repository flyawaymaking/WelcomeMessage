package com.flyaway.welcomemessage;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ReloadCommand implements CommandExecutor, TabCompleter {
    private final ConfigManager configManager;
    private final MessageManager messageManager;

    public ReloadCommand(ConfigManager configManager, MessageManager messageManager) {
        this.configManager = configManager;
        this.messageManager = messageManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("welcomemessage.reload")) {
                sender.sendMessage(messageManager.toComponent(configManager.getMessage("no-permissions")));
                return true;
            }

            configManager.reloadConfig();
            sender.sendMessage(messageManager.toComponent(configManager.getMessage("config-reloaded")));
            return true;
        }

        sender.sendMessage(messageManager.toComponent(configManager.getMessage("usage")));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            if (sender.hasPermission("welcomemessage.reload")) {
                completions.add("reload");
            }
        }

        return completions;
    }
}
