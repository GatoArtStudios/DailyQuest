package gatoartstudio.qailyquest.config;

import gatoartstudio.qailyquest.quests.Quest;
import gatoartstudio.qailyquest.quests.types.*;
import gatoartstudio.qailyquest.utils.Logger;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfigManager extends Config {
    private static FileConfiguration config;

    public ConfigManager(JavaPlugin plugin) {
        super(plugin, "config.yml");
    }

    @Override
    public void loadConfig() {
        // logic
        if (!configFile.exists()) {
            plugin.saveResource(configFile.getName(), false);
        }
        config = plugin.getConfig();
    }

    /**
     * Obtiene la lista de quests disponibles.
     *
     * Este método recorre la configuración del sistema y devuelve una lista de quests que están disponibles para ser realizados.
     *
     * @return una lista de quests disponibles
     */
    public List<Quest> getAvailableQuests() {
        List<Quest> quests = new ArrayList<>();

        // Procesar misiones de plantación
        if (config.contains("quests.plant")) {
            for (Map<?, ?> questData : config.getMapList("quests.plant")) {
                String seedName = (String) questData.get("seed");
                int required = (int) questData.get("required");
                Material seed = Material.getMaterial(seedName);
                if (seed != null) {
                    quests.add(new PlantQuest(seed, required));
                    Logger.success("Loaded PLANT quest: " + required + "x " + seed.name());
                }
            }
        }

        // Procesar misiones de matar entidades
        if (config.contains("quests.kills")) {
            for (Map<?, ?> questData : config.getMapList("quests.kills")) {
                String entityName = (String) questData.get("entity");
                int required = (int) questData.get("required");
                EntityType entity = EntityType.valueOf(entityName);
                if (entity != null) {
                    quests.add(new KillEntityQuest(entity, required));
                    Logger.success("Loaded KILL quest: " + required + "x " + entity.name());
                }
            }
        }

        // Procesar misiones de crafteo
        if (config.contains("quests.craft")) {
            for (Map<?, ?> questData : config.getMapList("quests.craft")) {
                String itemName = (String) questData.get("item");
                int required = (int) questData.get("required");
                Material item = Material.getMaterial(itemName);
                if (item != null) {
                    quests.add(new CraftQuest(item, required));
                    Logger.success("Loaded CRAFT quest: " + required + "x " + item.name());
                }
            }
        }

        // Procesar misiones de caminata
        if (config.contains("quests.walk")) {
            for (Map<?, ?> questData : config.getMapList("quests.walk")) {
                int distance = (int) questData.get("distance");
                quests.add(new WalkQuest(distance));
                Logger.success("Loaded WALK quest: " + distance + " blocks");
            }
        }

        // Procesar misiones de completación de misiones
        if (config.contains("quests.mquest_completation")) {
            for (Map<?, ?> questData : config.getMapList("quests.mquest_completation")) {
                int level = (int) questData.get("level");
                quests.add(new MQuestCompletation(level));
                Logger.success("Loaded MQUEST_COMPLETION quest: level " + level);
            }
        }
        return quests;
    }

}
