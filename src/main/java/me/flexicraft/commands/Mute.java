package me.flexicraft.commands;

import me.flexicraft.FlexiCraft;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class Mute implements CommandExecutor {

    private final FlexiCraft plugin;
    private final Set<UUID> mutedPlayers;

    public Mute(FlexiCraft plugin, Set<UUID> mutedPlayers) {
        this.plugin = plugin;
        this.mutedPlayers = mutedPlayers;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("flexicraft.mute")) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("mute-no-perms")));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /mute <player>");
            return true;
        }

        Player target = plugin.getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        if (mutedPlayers.contains(target.getUniqueId())) {
            sender.sendMessage(ChatColor.YELLOW + target.getName() + " is already muted.");
        } else {
            mutedPlayers.add(target.getUniqueId());
            sender.sendMessage(ChatColor.YELLOW + target.getName() + " has been muted.");
            target.sendMessage(ChatColor.YELLOW + "You have been muted.");
        }

        return true;
    }
}
