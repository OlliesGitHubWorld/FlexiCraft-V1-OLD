//package me.flexicraft.commands;
//
//import me.flexicraft.FlexiCraft;
//import org.bukkit.ChatColor;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
//
//public class Help implements CommandExecutor {
//
//    private final FlexiCraft plugin;
//
//    public Help(FlexiCraft plugin) {
//        this.plugin = plugin;
//    }
//
//
//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        if (args.length >= 1 && args[0].equalsIgnoreCase("help")) {
//            if (!(sender instanceof Player)) {
//                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-player-error")));
//                return true;
//            }
//        }
//            // Display help information
//            sender.sendMessage(ChatColor.GOLD + "===== Help Menu =====");
//            sender.sendMessage(ChatColor.YELLOW + "/flexicraft reload" + ChatColor.GRAY + " - Reload the config file.");
//            sender.sendMessage(ChatColor.YELLOW + "/ban <player> [reason]" + ChatColor.GRAY + " - Ban a player from the server.");
//            sender.sendMessage(ChatColor.YELLOW + "/unban <player>" + ChatColor.GRAY + " - Unban a player from the ban list.");
//            sender.sendMessage(ChatColor.YELLOW + "/kick <player> [reason]" + ChatColor.GRAY + " - Kick a player from the server.");
//            sender.sendMessage(ChatColor.YELLOW + "/mute <player>" + ChatColor.GRAY + " - Mute a player from the chat.");
//            sender.sendMessage(ChatColor.YELLOW + "/unmute <player>" + ChatColor.GRAY + " - Unmute a player in the chat.");
//            sender.sendMessage(ChatColor.YELLOW + "/fly <player>" + ChatColor.GRAY + " - Toggle fly mode for yourself.");
//            sender.sendMessage(ChatColor.YELLOW + "/clear <player>" + ChatColor.GRAY + " - Clear your inventory.");
//            sender.sendMessage(ChatColor.YELLOW + "/repair" + ChatColor.GRAY + " - Repair a item that is broken.");
//            return true;
//        }
//    }