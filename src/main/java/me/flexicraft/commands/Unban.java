package me.flexicraft.commands;

import me.flexicraft.FlexiCraft;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Unban implements CommandExecutor {

    private final FlexiCraft plugin;
    private final String unbanPermission = "flexicraft.unban";

    public Unban(FlexiCraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-player-error")));
            return true;
        }

        if (!sender.hasPermission(unbanPermission)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("unban-no-perms")));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("unban-usage")));
            return true;
        }

        String playerName = args[0];
        BanList banList = plugin.getServer().getBanList(BanList.Type.NAME);
        if (!banList.isBanned(playerName)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("unban-player-not-banned").replace("%target-player%", playerName)));
            return true;
        }


        banList.pardon(playerName);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("unban-player-unbanned").replace("%target-player%", playerName)));
        return true;
    }
}