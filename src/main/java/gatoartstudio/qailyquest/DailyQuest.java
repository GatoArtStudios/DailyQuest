package gatoartstudio.qailyquest;

import gatoartstudio.qailyquest.api.MQuestAPI;
import gatoartstudio.qailyquest.commands.DailyQuestDevCommand;
import gatoartstudio.qailyquest.commands.tabCompleter.DailyQuestDevTabCompleter;
import gatoartstudio.qailyquest.config.ConfigManager;
import gatoartstudio.qailyquest.listeners.PlantListener;
import gatoartstudio.qailyquest.listeners.PlayerJoinListener;
import gatoartstudio.qailyquest.quests.QuestManager;
import gatoartstudio.qailyquest.quests.types.PlantQuest;
import gatoartstudio.qailyquest.utils.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class DailyQuest extends JavaPlugin {
    private QuestManager questManager;
    private MQuestAPI mQuestAPI;
    private ConfigManager configManager;


    @Override
    public void onEnable() {
        printBanner();
        Logger.info("Plugin enabled");

        // instanciar y cargamos las configuraciones
        configManager = new ConfigManager(this);
        configManager.loadConfig();

        // Acceder a los datos de configuración
        boolean sqliteEnabled = configManager.getConfig().getBoolean("database.sqlite.enabled");
        String sqlitePath = configManager.getConfig().getString("database.sqlite.path");

        Logger.info("SQLite enabled: " + sqliteEnabled);
        Logger.info("SQLite path: " + sqlitePath);

        mQuestAPI = new MQuestAPI();
        questManager = new QuestManager(mQuestAPI, this, configManager);

        registerEvents();
        registerCommand();
    }

    @Override
    public void onDisable() {
        Logger.info("Plugin disabled");
    }

    void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlantListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    void registerCommand() {
        getCommand("dailyquestdev").setExecutor(new DailyQuestDevCommand(this));
        getCommand("dailyquestdev").setTabCompleter(new DailyQuestDevTabCompleter(this));
    }

    private void printBanner() {
        String banner = "\n" +
                "***************************************************************\n" +
                "*                                                             *\n" +
                "*      ____        _ __      ____                  __         *\n" +
                "*     / __ \\____ _(_) /_  __/ __ \\__  _____  _____/ /_        *\n" +
                "*    / / / / __ `/ / / / / / / / / / / / _ \\/ ___/ __/        *\n" +
                "*   / /_/ / /_/ / / / /_/ / /_/ / /_/ /  __(__  ) /_          *\n" +
                "*  /_____/\\__,_/_/_/\\__, /\\___\\_\\__,_/\\___/____/\\__/          *\n" +
                "*                /____/                                       *\n" +
                "*                                                             *\n" +
                "*                 GatoArtStudio x Minemu Network              *\n" +
                "*                                                             *\n" +
                "***************************************************************\n";
        String[] lines = banner.split("\n");
        StringBuilder coloredBanner = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            int red = (int) (Math.sin(0.3 * i + 0) * 127 + 128);
            int green = (int) (Math.sin(0.1 * i + 2) * 127 + 128);
            int blue = (int) (Math.sin(0.3 * i + 4) * 127 + 128);
            coloredBanner.append(String.format("\u001B[38;2;%d;%d;%dm%s\u001B[0m\n", red, green, blue, lines[i]));
        }
//        System.out.println(coloredBanner.toString());
        Logger.raw(coloredBanner.toString());
    }

    public QuestManager getQuestManager() {
        return questManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}