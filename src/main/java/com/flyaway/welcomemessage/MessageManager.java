package com.flyaway.welcomemessage;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import net.kyori.adventure.text.Component;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.cacheddata.CachedMetaData;
import org.jetbrains.annotations.NotNull;

public class MessageManager {
    private final WelcomeMessage plugin;
    private LuckPerms luckPerms;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public MessageManager(WelcomeMessage plugin) {
        this.plugin = plugin;
        setupLuckPerms();
    }

    private void setupLuckPerms() {
        try {
            this.luckPerms = LuckPermsProvider.get();
            plugin.getLogger().info("LuckPerms найден, префиксы будут загружаться из него");
        } catch (IllegalStateException e) {
            plugin.getLogger().warning("LuckPerms не найден, будут использоваться стандартные префиксы");
            this.luckPerms = null;
        }
    }

    public boolean shouldSilentJoin(Player player) {
        return player.hasPermission("essentials.silentjoin");
    }

    public String getPlayerPrefix(Player player) {
        if (luckPerms == null) {
            return "";
        }

        try {
            User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
            CachedMetaData metaData = user.getCachedData().getMetaData();
            String prefix = metaData.getPrefix();
            return prefix != null ? prefix : "";

        } catch (Exception e) {
            plugin.getLogger().warning("Не удалось получить префикс для игрока " + player.getName() + ": " + e.getMessage());
            return "";
        }
    }

    public String getPlayerSuffix(Player player) {
        if (luckPerms == null) {
            return "";
        }

        try {
            User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
            CachedMetaData metaData = user.getCachedData().getMetaData();
            String suffix = metaData.getSuffix();
            return suffix != null ? suffix : "";

        } catch (Exception e) {
            plugin.getLogger().warning("Не удалось получить суффикс для игрока " + player.getName() + ": " + e.getMessage());
            return "";
        }
    }

    public @NotNull Component createFormattedMessage(Player player, String prefix, String suffix, String rawMessage, boolean isFirstJoin) {
        if (rawMessage == null || rawMessage.trim().isEmpty()) {
            return Component.empty();
        }

        String messageFormat = plugin.getConfig().getString("message-format", "%prefix%%player%%suffix% %message%");

        String formattedMessage = messageFormat
                .replace("%prefix%", prefix)
                .replace("%player%", player.getName())
                .replace("%suffix%", suffix)
                .replace("%message%", rawMessage);

        return toComponent(formattedMessage);
    }

    public @NotNull Component toComponent(String text) {
        if (text == null || text.trim().isEmpty()) {
            return Component.empty();
        }

        return miniMessage.deserialize(text);
    }
}
