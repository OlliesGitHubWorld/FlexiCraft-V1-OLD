package me.flexicraft.commands;

import me.flexicraft.FlexiCraft;
import me.flexicraft.util.MuteConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Mute implements CommandExecutor {

    private final FlexiCraft plugin;
    private final MuteConfigManager muteConfigManager;
    public final Set<UUID> mutedPlayers;
    private final File mutedPlayersFile;

    public Mute(FlexiCraft plugin, MuteConfigManager muteConfigManager) {
        this.plugin = plugin;
        this.muteConfigManager = muteConfigManager;
        this.mutedPlayers = new HashSet<>();
        this.mutedPlayersFile = new File(plugin.getDataFolder(), "muted-players.yml");

        loadMutedPlayers();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("flexicraft.mute")) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("mute-no-perms")));
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("mute-usage")));
            return true;
        }

        Player target = plugin.getServer().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("mute-no-player")));
            return true;
        }

        String reason = args.length > 1 ? String.join(" ", args).substring(args[0].length() + 1) : "No reason provided.";

        if (mutedPlayers.contains(target.getUniqueId())) {
            sender.sendMessage(ChatColor.YELLOW + target.getName() + " is already muted.");
        } else {
            mutedPlayers.add(target.getUniqueId());
            saveMutedPlayers();
            sender.sendMessage(ChatColor.YELLOW + target.getName() + " has been muted. Reason: " + reason);
            target.sendMessage(ChatColor.YELLOW + "You have been muted. Reason: " + reason);
        }

        return true;
    }

    private void loadMutedPlayers() {
        mutedPlayers.addAll(muteConfigManager.loadMutedPlayers());
    }

    private void saveMutedPlayers() {
        muteConfigManager.saveMutedPlayers(mutedPlayers);
    }
}