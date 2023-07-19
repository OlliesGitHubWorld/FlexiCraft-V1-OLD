package me.flexicraft.commands;

import me.flexicraft.FlexiCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Fly implements CommandExecutor {
    private FlexiCraft plugin;
    private ArrayList<Player> list_of_flying_players = new ArrayList<>();

    public Fly(FlexiCraft plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-player-error")));
            return true;
        } else {
            Player player = (Player) sender;
            if (args.length == 0) {
                flyMethod(player);
            } else if (args.length == 1) {
                if (player.hasPermission("flexicraft.fly.others")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        otherFlyMethod(target);
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-not-found")));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-other-permission-message")));
                }
            }
        }

        return true;
    }

    private void flyMethod(Player player) {

        if (player.hasPermission("flexicraft.fly")) {
            if (list_of_flying_players.contains(player)) {
                list_of_flying_players.remove(player);
                player.setAllowFlight(false);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("off-message")));
            } else if (!list_of_flying_players.contains(player)) {
                list_of_flying_players.add(player);
                player.setAllowFlight(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("on-message")));
            }
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission-message")));
        }
    }
    private void otherFlyMethod(Player player) {

        if (player.hasPermission("flexicraft.fly")) {
            if (list_of_flying_players.contains(player)) {
                list_of_flying_players.remove(player);
                player.setAllowFlight(false);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("other-off-message").replace("%other_player%", player.getName())));
            } else if (!list_of_flying_players.contains(player)) {
                list_of_flying_players.add(player);
                player.setAllowFlight(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("other-on-message").replace("%other_player%", player.getName())));
            }
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission-message")));
        }
    }
}