package me.flexicraft.commands;

import me.flexicraft.FlexiCraft;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.util.Arrays;
public class FlexiRoot implements CommandExecutor {

    private final FlexiCraft plugin;

    public FlexiRoot(FlexiCraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length >= 1 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("flexicraft.reload")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("config-reload-no-perms")));
                return true;
            }

            plugin.reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("config-reload")));
            return true;
        }

        if (args.length >= 1 && args[0].equalsIgnoreCase("ban")) {
            String[] passedDownCommands = Arrays.copyOfRange(args, 1, args.length); // Exclude the first argument

            String Alabel = "ban"; // Replace with the appropriate label
            String[] passedDownArgs = passedDownCommands; // The modified arguments

            new Ban(plugin).onCommand(sender, command, Alabel, passedDownArgs);


            return true;
        }


        // Display help information when no arguments or "help" are provided
        sender.sendMessage(ChatColor.GOLD + "===== Help Menu =====");
        sender.sendMessage(ChatColor.YELLOW + "/flexicraft reload" + ChatColor.GRAY + " - Reload the config file.");
        sender.sendMessage(ChatColor.YELLOW + "/ban <player> [reason]" + ChatColor.GRAY + " - Ban a player from the server.");
        sender.sendMessage(ChatColor.YELLOW + "/unban <player>" + ChatColor.GRAY + " - Unban a player from the ban list.");
        sender.sendMessage(ChatColor.YELLOW + "/kick <player> [reason]" + ChatColor.GRAY + " - Kick a player from the server.");
        sender.sendMessage(ChatColor.YELLOW + "/mute <player>" + ChatColor.GRAY + " - Mute a player from the chat.");
        sender.sendMessage(ChatColor.YELLOW + "/unmute <player>" + ChatColor.GRAY + " - Unmute a mob in the chat.");
        sender.sendMessage(ChatColor.YELLOW + "/fly <player>" + ChatColor.GRAY + " - Toggle fly mode for yourself.");
        sender.sendMessage(ChatColor.YELLOW + "/clear <player>" + ChatColor.GRAY + " - Clear your inventory.");
        sender.sendMessage(ChatColor.YELLOW + "/repair" + ChatColor.GRAY + " - Repair an item that is broken.");
        return true;

    }
}


