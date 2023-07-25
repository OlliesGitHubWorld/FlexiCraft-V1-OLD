package me.flexicraft.listeners;

import me.flexicraft.FlexiCraft;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {
    private FlexiCraft plugin;

    public JoinLeaveListener(FlexiCraft plugin) { this.plugin = plugin; }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player player = e.getPlayer();


        if (player.hasPlayedBefore()){
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("join-message").replace("%player%", player.getDisplayName())));
        }else{
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("first-time-join-message").replace("%player%", player.getDisplayName())));
        }

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player player = e.getPlayer();

        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("leave-message").replace("%player%", player.getDisplayName())));

    }

}
