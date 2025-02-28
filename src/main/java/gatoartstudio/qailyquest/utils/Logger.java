package gatoartstudio.qailyquest.utils;

import org.bukkit.Bukkit;

public class Logger {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[0;91m";
    private static final String ANSI_GREEN = "\u001B[0;92m";
    private static final String ANSI_YELLOW = "\u001B[0;93m";
    private static final String ANSI_BLUE = "\u001B[0;94m";
    private static final String ANSI_CYAN = "\u001B[0;96m";
    private static final String ANSI_PURPLE = "\u001B[0;95m";
    private static final String prefix = ANSI_PURPLE + "[DailyQuest] " + ANSI_RESET;
    private static final String errorPrefix = ANSI_RED + "[ERROR] ";
    private static final String warningPrefix = ANSI_YELLOW + "[WARNING] ";
    private static final String infoPrefix = ANSI_CYAN + "[INFO] ";
    private static final String debugPrefix = ANSI_BLUE + "[DEBUG] ";
    private static final String successPrefix = ANSI_GREEN + "[SUCCESS] " + ANSI_RESET;

    public static void info(String message) {
        Bukkit.getLogger().info(prefix + infoPrefix + message + ANSI_RESET);
    }

    public static void warning(String message) {
        Bukkit.getLogger().warning(prefix + warningPrefix + message + ANSI_RESET);
    }

    public static void error(String message) {
        Bukkit.getLogger().severe(prefix + errorPrefix + message + ANSI_RESET);
    }

    public static void debug(String message) {
        Bukkit.getLogger().info(prefix + debugPrefix + message + ANSI_RESET);
    }

    public static void success(String message) {
        Bukkit.getLogger().info(prefix + successPrefix + message + ANSI_RESET);
    }

    public static void raw(String message) {
        Bukkit.getLogger().info(message);
    }
}
