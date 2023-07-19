package me.flexicraft.commands;

import me.flexicraft.FlexiCraft;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class Unmute implements CommandExecutor {

    private final FlexiCraft plugin;
    private final Set<UUID> mutedPlayers;

    public Unmute(FlexiCraft plugin, Set<UUID> mutedPlayers) {
        this.plugin = plugin;
        this.mutedPlayers = mutedPlayers;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("flexicraft.unmute")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /unmute <player>");
            return true;
        }

        Player target = plugin.getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        if (!mutedPlayers.contains(target.getUniqueId())) {
            sender.sendMessage(ChatColor.YELLOW + target.getName() + " is not muted.");
        } else {
            mutedPlayers.remove(target.getUniqueId());
            sender.sendMessage(ChatColor.YELLOW + target.getName() + " has been unmuted.");
            target.sendMessage(ChatColor.YELLOW + "You have been unmuted.");
        }

        return true;
    }
}
