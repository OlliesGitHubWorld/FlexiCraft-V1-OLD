package me.flexicraft.commands;

import me.flexicraft.FlexiCraft;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Kick implements CommandExecutor {

    private final FlexiCraft plugin;
    private final String kickPermission = "flexicraft.kick";

    public Kick(FlexiCraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-player-error")));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(kickPermission)) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("kick-usage")));
            return true;
        }

        String playerName = args[0];
        Player targetPlayer = plugin.getServer().getPlayer(playerName);

        if (targetPlayer == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("kick-no-player")));
            return true;
        }

        String reason = args.length > 1 ? String.join(" ", Arrays.copyOfRange(args, 1, args.length)) : "You have been kicked.";
        targetPlayer.kickPlayer(reason);

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("kick-message")
                .replace("%target-player%", targetPlayer.getName())
                .replace("%reason%", reason)));

        return true;
    }
}
