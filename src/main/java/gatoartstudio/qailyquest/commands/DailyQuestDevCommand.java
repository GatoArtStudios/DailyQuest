package gatoartstudio.qailyquest.commands;

import gatoartstudio.qailyquest.DailyQuest;
import gatoartstudio.qailyquest.quests.Quest;
import gatoartstudio.qailyquest.quests.QuestManager;
import gatoartstudio.qailyquest.quests.QuestType;
import gatoartstudio.qailyquest.quests.types.*;
import gatoartstudio.qailyquest.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BiConsumer;

public class DailyQuestDevCommand implements CommandExecutor {
    private final DailyQuest plugin;
    private final QuestManager questManager;
    private final Map<String, BiConsumer<CommandSender, String[]>> subCommands = new HashMap<>();

    public DailyQuestDevCommand(DailyQuest plugin) {
        this.plugin = plugin;
        this.questManager = plugin.getQuestManager();

        // Agregar los subcomandos
//        subCommands.put("assign", this::assignDailyQuest);
//        subCommands.put("stats", this::questStats);
//        subCommands.put("complete", this::completeDailyQuest);
        subCommands.put("assign", this::assignSpecificQuest);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (strings.length == 0 || !subCommands.containsKey(strings[0])) {
            commandSender.sendMessage("Usage: /dailyquestdev <assign|stats|complete>");
            return true;
        }

        // Ejecutar el subcomando correspondiente
        subCommands.get(strings[0].toLowerCase()).accept(commandSender, strings);
        return true;
    }

    private void assignSpecificQuest(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // Mostrar permisos en consola para debug
        Logger.info("Verificando permisos de " + player.getName());
        player.getEffectivePermissions().forEach(perm -> Logger.info(" - " + perm.getPermission()));

        if (!player.hasPermission("dailyquest.dev")) {
            player.sendMessage("No tienes permiso para usar este comando.");
            player.sendMessage("Permisos detectados: " + player.getEffectivePermissions() + " , permiso requerido: {}.dev");
            return;
        }
        if (args.length < 3) {
            player.sendMessage("Usage: /dailyquestdev assign <player> <quest>");
            return;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            player.sendMessage("El jugador especificado no existe.");
            return;
        }
        String questName = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
        Logger.info("Asignando " + questName + " a " + target.getName());

        List<Quest> quests = questManager.getQuests();
        QuestType questType = null;
        for (Quest quest : quests) {
            if (quest.getQuestName().equalsIgnoreCase(questName)) {
                questType = quest.getQuestType();
                break;
            }
        }
        if (questType == null) {
            player.sendMessage("&cQuest no encontrada.");
            return;
        }

        switch (questType) {
            case PLANT:
                questManager.assignSpecificQuest(target, new PlantQuest(Material.WHEAT, 5));
                break;
            case KILL_ENTITY:
                questManager.assignSpecificQuest(target, new KillEntityQuest(EntityType.ZOMBIE, 5));
                break;
            case CRAFT:
                questManager.assignSpecificQuest(target, new CraftQuest(Material.WHEAT, 5));
                break;
            case WALK:
                questManager.assignSpecificQuest(target, new WalkQuest(5));
                break;
            case MQUEST_COMPLETATION:
                questManager.assignSpecificQuest(target, new MQuestCompletation(5));
                break;
            default:
                player.sendMessage("&cQuest no encontrada.");
                break;
        }
    }
}
