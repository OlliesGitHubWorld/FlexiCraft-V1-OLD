package me.flexicraft;

import me.flexicraft.commands.*;
import me.flexicraft.util.*;
import me.flexicraft.util.FlexiCraftTabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class FlexiCraft extends JavaPlugin {
    private final Set<UUID> mutedPlayers;

    public FlexiCraft() {
        this.mutedPlayers = new HashSet<>();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("flexicraft").setTabCompleter(new FlexiCraftTabCompleter());

        getCommand("ban").setExecutor(new Ban(this));
        getCommand("fly").setExecutor(new Fly(this));
        getCommand("unban").setExecutor(new Unban(this));
        getCommand("kick").setExecutor(new Kick(this));
        getCommand("clear").setExecutor(new Clear(this));
        getCommand("mute").setExecutor(new Mute(this, mutedPlayers));
        getCommand("unmute").setExecutor(new Unmute(this, mutedPlayers));
        getCommand("repair").setExecutor(new Repair(this));

        getCommand("flexicraft").setExecutor(new ReloadConfig(this));
        getCommand("flexicraft").setExecutor(new Help(this));

        // Register event listener
        JoinLeaveListener joinLeaveListener = new JoinLeaveListener(this);
        getServer().getPluginManager().registerEvents(joinLeaveListener, this);
        getServer().getPluginManager().registerEvents(new me.flexicraft.listeners.ChatListener(this, mutedPlayers), this);

        // Load config.yml
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
}