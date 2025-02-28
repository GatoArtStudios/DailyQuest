package gatoartstudio.qailyquest.commands.tabCompleter;

import gatoartstudio.qailyquest.DailyQuest;
import gatoartstudio.qailyquest.quests.Quest;
import gatoartstudio.qailyquest.quests.QuestManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DailyQuestDevTabCompleter implements TabCompleter {
    private final DailyQuest plugin;
    private final QuestManager questManager;

    public DailyQuestDevTabCompleter(DailyQuest plugin) {
        this.plugin = plugin;
        this.questManager = plugin.getQuestManager();
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 1) {
            return Arrays.asList("reload", "stats", "complete", "assign");
        }

        if (strings.length == 2) {
            if (strings[0].equalsIgnoreCase("stats") || strings[0].equalsIgnoreCase("assign")) {
                return Bukkit.getOnlinePlayers().stream()
                        .map(player -> player.getName())
                        .collect(Collectors.toList());
            }
        }

        if (strings.length == 3) {
            if (strings[0].equalsIgnoreCase("assign")) {
                List<Quest> quests = questManager.getQuests();
                List<String> questNames = new ArrayList<>();
                for (Quest quest : quests) {
                    questNames.add(quest.getQuestName());
                }
                return questNames; // Devuelve una lista de misiones disponibles
            }
        }

        return new ArrayList<>();
    }
}
