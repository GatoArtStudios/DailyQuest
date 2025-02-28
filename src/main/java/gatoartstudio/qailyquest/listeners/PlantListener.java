package gatoartstudio.qailyquest.listeners;

import gatoartstudio.qailyquest.DailyQuest;
import gatoartstudio.qailyquest.quests.Quest;
import gatoartstudio.qailyquest.quests.QuestManager;
import gatoartstudio.qailyquest.quests.QuestType;
import gatoartstudio.qailyquest.quests.types.PlantQuest;
import gatoartstudio.qailyquest.utils.Logger;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlantListener implements Listener {
    private QuestManager questManager;
    private DailyQuest plugin;

    public PlantListener(DailyQuest plugin) {
        this.plugin = plugin;
        this.questManager = plugin.getQuestManager();
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Material placedBlock = event.getBlock().getType();

        Logger.info("Player " + player.getName() + " placed: " + placedBlock.name() + " type: " + placedBlock);

        Quest quest = questManager.getQuest(player);

        if (quest == null) {
            Logger.info("Player " + player.getName() + " has no quest");
            return;
        } else {
            Logger.info("Player " + player.getName() + " has a quest: " + quest.getQuestName());
        }

        if (quest instanceof PlantQuest) {

            Logger.info("Player " + player.getName() + " has a PlantQuest");

            // Verificamos si es la misma semilla
            if (placedBlock == ((PlantQuest) quest).getSeed()) {
                Logger.info("Player " + player.getName() + " planted: " + placedBlock.name());
                questManager.addPointsToPlayer(player, 1, QuestType.PLANT);
            } else {
                Logger.info("Player " + player.getName() + " did not plant: " + placedBlock.name() + " type quest: " + ((PlantQuest) quest).getSeed());
            }
        }
    }
}
