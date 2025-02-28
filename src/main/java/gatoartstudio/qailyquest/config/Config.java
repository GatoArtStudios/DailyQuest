package gatoartstudio.qailyquest.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public abstract class Config {
    protected JavaPlugin plugin;
    protected File configFile;
    protected FileConfiguration config;

    public Config(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), fileName);
        this.config = plugin.getConfig();
        saveDefaultConfig();
    }

    public void saveDefaultConfig() {
        if (!configFile.exists()) {
            plugin.saveResource(configFile.getName(), false);
        }
    }

    public void saveConfig() {
        try{
            config.save(configFile);
        } catch (Exception e) {
            // debug
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public abstract void loadConfig();
}
