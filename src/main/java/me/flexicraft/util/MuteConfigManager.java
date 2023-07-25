package me.flexicraft.util;

import me.flexicraft.FlexiCraft;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MuteConfigManager {

    private final FlexiCraft plugin;
    private final Set<UUID> mutedPlayers;
    private final FileConfiguration config;
    private final File mutedPlayersFile;

    public MuteConfigManager(FlexiCraft plugin) {
        this.plugin = plugin;
        this.mutedPlayers = new HashSet<>();
        this.config = plugin.getConfig();
        this.mutedPlayersFile = new File(plugin.getDataFolder(), "muted_players.yml");
    }

    public Set<UUID> loadMutedPlayers() {
        if (mutedPlayersFile.exists()) {
            ConfigurationSection section = YamlConfiguration.loadConfiguration(mutedPlayersFile);
            if (section != null) {
                for (String uuidString : section.getKeys(false)) {
                    try {
                        UUID uuid = UUID.fromString(uuidString);
                        mutedPlayers.add(uuid);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return mutedPlayers;
    }

    public void saveMutedPlayers(Set<UUID> mutedPlayers) {
        FileConfiguration mutedPlayersConfig = new YamlConfiguration();

        for (UUID uuid : mutedPlayers) {
            mutedPlayersConfig.set(uuid.toString(), true);
        }

        try {
            mutedPlayersConfig.save(mutedPlayersFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
