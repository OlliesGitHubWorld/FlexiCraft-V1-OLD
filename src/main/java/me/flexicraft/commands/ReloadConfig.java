package me.flexicraft.commands;

import me.flexicraft.FlexiCraft;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.flexicraft.commands.*;
import org.bukkit.command.PluginCommand;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReloadConfig implements CommandExecutor {

    private final FlexiCraft plugin;

    public ReloadConfig(FlexiCraft plugin) {
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
        if (args.length >= 1 && args[0].equalsIgnoreCase("help")) {
            // Display help information
            sender.sendMessage(ChatColor.GOLD + "===== Help Menu =====");
            sender.sendMessage(ChatColor.YELLOW + "/flexicraft reload" + ChatColor.GRAY + " - Reload the config file.");
            sender.sendMessage(ChatColor.YELLOW + "/ban <player> [reason]" + ChatColor.GRAY + " - Ban a player from the server.");
            sender.sendMessage(ChatColor.YELLOW + "/unban <player>" + ChatColor.GRAY + " - Unban a player from the ban list.");
            sender.sendMessage(ChatColor.YELLOW + "/kick <player> [reason]" + ChatColor.GRAY + " - Kick a player from the server.");
            sender.sendMessage(ChatColor.YELLOW + "/mute <player>" + ChatColor.GRAY + " - Mute a player from the chat.");
            sender.sendMessage(ChatColor.YELLOW + "/unmute <player>" + ChatColor.GRAY + " - Unmute a player in the chat.");
            sender.sendMessage(ChatColor.YELLOW + "/fly <player>" + ChatColor.GRAY + " - Toggle fly mode for yourself.");
            sender.sendMessage(ChatColor.YELLOW + "/clear <player>" + ChatColor.GRAY + " - Clear your inventory.");
            sender.sendMessage(ChatColor.YELLOW + "/repair" + ChatColor.GRAY + " - Repair a item that is broken.");
            return true;
        }
        if (args.length >= 1 && args[0].equalsIgnoreCase("ban")) {
            String[] passedDownCommands = Arrays.copyOfRange(args, 1, args.length); // Exclude the first argument

            String Alabel = "ban"; // Replace with the appropriate label
            String[] passedDownArgs = passedDownCommands; // The modified arguments

            new Ban(plugin).onCommand(sender, command, Alabel, passedDownArgs);
            // PluginCommand.execute(sender, Alabel, passedDownArgs); //Would be more ideal but cba


            return true;
        }

        return false;
    }

    /*
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (command.getName().equalsIgnoreCase("command")) {
            return Arrays.asList("reload", "456", "789");
        }

        return null;
    }
    */
}
