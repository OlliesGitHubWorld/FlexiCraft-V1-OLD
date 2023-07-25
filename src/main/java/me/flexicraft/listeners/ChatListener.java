package me.flexicraft.listeners;

import me.flexicraft.FlexiCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ChatListener implements Listener {

    private final FlexiCraft plugin;
    private final Set<UUID> mutedPlayers;

    public ChatListener(FlexiCraft plugin, Set<UUID> mutedPlayers) {
        this.plugin = plugin;
        this.mutedPlayers = mutedPlayers;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Bukkit.getLogger().info("test ik heb test gegeten met een dikke balzak");
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (mutedPlayers.contains(uuid)) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("mute-chat-message")));
        }
    }
}