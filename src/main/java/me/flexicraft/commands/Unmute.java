package me.flexicraft.commands;

import me.flexicraft.FlexiCraft;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class Unmute implements CommandExecutor {

    private final FlexiCraft plugin;
    private final Set<UUID> mutedPlayers;
    private final FileConfiguration config;
    private final File mutedPlayersFile;

    public Unmute(FlexiCraft plugin, Set<UUID> mutedPlayers) {
        this.plugin = plugin;
        this.mutedPlayers = mutedPlayers;
        this.config = plugin.getConfig();
        this.mutedPlayersFile = new File(plugin.getDataFolder(), "muted_players.yml");

        // Load the muted players when the plugin is enabled
        loadMutedPlayers();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("flexicraft.unmute")) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("unmute-no-perms")));
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("unmute-usage")));
            return true;
        }

        Player target = plugin.getServer().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("unmute-no-player")));
            return true;
        }

        if (!mutedPlayers.contains(target.getUniqueId())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("unmute-player-not-muted")));
        } else {
            mutedPlayers.remove(target.getUniqueId());
            saveMutedPlayers();
            sender.sendMessage(ChatColor.YELLOW + target.getName() + " has been unmuted.");
            target.sendMessage(ChatColor.YELLOW + "You have been unmuted.");
        }

        return true;
    }

    private void loadMutedPlayers() {
        if (mutedPlayersFile.exists()) {
            config.getList("muted-players", java.util.Collections.emptyList()).forEach(uuidString -> {
                try {
                    UUID uuid = UUID.fromString(uuidString.toString());
                    mutedPlayers.add(uuid);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void saveMutedPlayers() {
        config.set("muted-players", mutedPlayers);
        try {
            config.save(mutedPlayersFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
