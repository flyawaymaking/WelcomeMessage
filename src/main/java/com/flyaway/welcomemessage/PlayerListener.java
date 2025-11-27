package com.flyaway.welcomemessage;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    private final ConfigManager configManager;
    private final MessageManager messageManager;

    public PlayerListener(ConfigManager configManager, MessageManager messageManager) {
        this.configManager = configManager;
        this.messageManager = messageManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.joinMessage(null);

        if (messageManager.shouldSilentJoin(player)) {
            return;
        }

        String playerPrefix = messageManager.getPlayerPrefix(player);
        String playerSuffix = messageManager.getPlayerSuffix(player);

        Component message;

        if (!player.hasPlayedBefore()) {
            String rawMessage = configManager.getFirstTimeMessage();
            message = messageManager.createFormattedMessage(player, playerPrefix, playerSuffix, rawMessage, true);
        } else {
            String rawMessage = configManager.getWelcomeMessage();
            message = messageManager.createFormattedMessage(player, playerPrefix, playerSuffix, rawMessage, false);
        }

        if (!message.equals(Component.empty())) {
            Bukkit.broadcast(message);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.quitMessage(null);

        if (messageManager.shouldSilentJoin(player)) {
            return;
        }

        String playerPrefix = messageManager.getPlayerPrefix(player);
        String playerSuffix = messageManager.getPlayerSuffix(player);

        String rawMessage = configManager.getQuitMessage();
        Component message = messageManager.createFormattedMessage(player, playerPrefix, playerSuffix, rawMessage, false);

        if (!message.equals(Component.empty())) {
            Bukkit.broadcast(message);
        }
    }
}
