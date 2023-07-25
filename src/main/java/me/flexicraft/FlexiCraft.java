package me.flexicraft;

import me.flexicraft.commands.*;
import me.flexicraft.listeners.ChatListener;
import me.flexicraft.listeners.JoinLeaveListener;
import me.flexicraft.util.FlexiCraftTabCompleter;
import me.flexicraft.util.MuteConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlexiCraft extends JavaPlugin {
    private MuteConfigManager muteConfigManager;
    private Mute muteManager;

    public FlexiCraft() {
        this.muteConfigManager = new MuteConfigManager(this);
        this.muteManager = new Mute(this, muteConfigManager);
    }

    @Override
    public void onEnable() {
        this.muteManager.mutedPlayers.addAll(muteConfigManager.loadMutedPlayers());

//        getCommand("flexicraft").setExecutor(new Help(this));
        getCommand("flexicraft").setTabCompleter(new FlexiCraftTabCompleter());
        getCommand("flexicraft").setExecutor(new FlexiRoot(this));

        getCommand("ban").setExecutor(new Ban(this));
        getCommand("fly").setExecutor(new Fly(this));
        getCommand("unban").setExecutor(new Unban(this));
        getCommand("kick").setExecutor(new Kick(this));
        getCommand("clear").setExecutor(new Clear(this));
        getCommand("mute").setExecutor(muteManager);
        getCommand("unmute").setExecutor(new Unmute(this, muteManager.mutedPlayers));
        getCommand("repair").setExecutor(new Repair(this));

        // Register event listener
        JoinLeaveListener joinLeaveListener = new JoinLeaveListener(this);
        getServer().getPluginManager().registerEvents(joinLeaveListener, this);
        ChatListener chatListener = new ChatListener(this, muteManager.mutedPlayers);
        getServer().getPluginManager().registerEvents(chatListener, this);

        // Load config.yml
        getConfig().options().copyDefaults();
        saveDefaultConfig();


    }

    @Override
    public void onDisable() {
        muteConfigManager.saveMutedPlayers(muteManager.mutedPlayers);
    }
}
