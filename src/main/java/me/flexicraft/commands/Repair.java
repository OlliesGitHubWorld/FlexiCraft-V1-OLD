package me.flexicraft.commands;

import me.flexicraft.FlexiCraft;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class Repair implements CommandExecutor {

    private final FlexiCraft plugin;
    private final String repairPermission = "flexicraft.repair";

    public Repair(FlexiCraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-player-error")));
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission(repairPermission)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("repair-no-perms")));
            return true;
        }

        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType() == Material.AIR) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("repair-no-item")));
            return true;
        }

        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta instanceof Damageable) {
            Damageable damageable = (Damageable) itemMeta;
            if (damageable.hasDamage()) {
                damageable.setDamage(0);
                item.setItemMeta(itemMeta);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("repair")));
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("repair-error")));
            }
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("repair-error")));
        }

        return true;
    }
}
