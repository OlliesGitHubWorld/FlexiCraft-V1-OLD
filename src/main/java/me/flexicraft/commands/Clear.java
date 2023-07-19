package me.flexicraft.commands;

import me.flexicraft.FlexiCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Clear implements CommandExecutor {

    private final FlexiCraft plugin;
    private final String clearPermission = "flexicraft.clear";

    public Clear(FlexiCraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-player-error")));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(clearPermission)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("clear-no-perms")));
            return true;
        }

        // Check if a player argument is provided
        if (args.length > 0) {
            String targetPlayerName = args[0];
            Player targetPlayer = Bukkit.getPlayer(targetPlayerName);

            if (targetPlayer == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("clear-player-not-found").replace("%target-player%", targetPlayerName)));
                return true;
            }

            targetPlayer.getInventory().clear();
            player.sendMessage(ChatColor.GREEN + "Inventory of " + targetPlayer.getName() + " has been cleared.");
        } else {
            player.getInventory().clear();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("clear-message")));
        }
        return true;
    }
}
