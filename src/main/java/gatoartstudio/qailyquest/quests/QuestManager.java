package gatoartstudio.qailyquest.quests;

import gatoartstudio.qailyquest.DailyQuest;
import gatoartstudio.qailyquest.api.MQuestAPI;
import gatoartstudio.qailyquest.config.Config;
import gatoartstudio.qailyquest.config.ConfigManager;
import gatoartstudio.qailyquest.utils.Logger;
import org.bukkit.entity.Player;

import java.util.*;

// Se encargara de asignarle de forma random una Quest a un jugador
public class QuestManager {
    private List<Quest> quests = new ArrayList<>(); // Lista de quests disponibles
    private Map<Player, PlayerQuestData> playerData = new HashMap<>(); // Datos de los jugadores
    private MQuestAPI mQuestAPI;
    private DailyQuest plugin; // Referencia al plugin
    private ConfigManager configManager; // Referencia al manejador de configuraciones

    public void addQuest(Quest quest) {
        quests.add(quest);
    }

    public QuestManager(MQuestAPI mQuestAPI, DailyQuest plugin, ConfigManager configManager) {
        this.mQuestAPI = mQuestAPI;
        this.plugin = plugin;
        this.configManager = configManager;

        this.quests = configManager.getAvailableQuests();
    }

    // Asigna una Quest diaria a un jugador
    public void assignDailyQuest(Player player) {
        if (!quests.isEmpty()) {
            Random random = new Random();
            Quest quest = quests.get(random.nextInt(quests.size())); // Selecciona una Quest random
            PlayerQuestData data = playerData.getOrDefault(player, new PlayerQuestData());

            data.setQuest(quest);
            playerData.put(player, data);

            quest.questStartedMessage(player);
        }
    }

    public boolean assignSpecificQuest(Player player, Quest quest) {
        PlayerQuestData data = playerData.getOrDefault(player, new PlayerQuestData());
        data.setQuest(quest);
        playerData.put(player, data);

        quest.questStartedMessage(player);

        return true;
    }


    /**
     * Completa la Quest diaria asignada a un jugador.
     * Si el jugador tiene una Quest asignada, marca la Quest como completada
     * y notifica al jugador que ha completado su misión diaria.
     *
     * @param player El jugador cuya Quest será completada
     */
    public void completeQuest(Player player) {

        PlayerQuestData data = playerData.get(player);
        if (data != null && data.getCurrentQuest() != null) {

            data.setCompletedQuests(true);
            data.getCurrentQuest().questCompletedMessage(player);
        }
    }

    /**
     * Obtiene la Quest actual asignada a un jugador
     * @param player
     * @return Quest
     */
    public Quest getQuest(Player player) {
        PlayerQuestData data = playerData.get(player);
        return data != null ? data.getCurrentQuest() : null;
    }

    /**
     * Returns the current quest assigned to a player, if the quest is of the given type.
     *
     * @param questType the type of quest to retrieve
     * @param player the player whose quest is to be retrieved
     * @return the current quest of the given type, or null if no such quest is assigned
     */
    public Quest getQuestForTypeForPlayer(QuestType questType, Player player) {
        PlayerQuestData data = playerData.get(player);
        if (data != null && data.getCurrentQuest() != null) {
            if (data.getCurrentQuest().getQuestType() == questType) {
                return data.getCurrentQuest();
            }
        }
        return null;
    }

    /**
     * Suma puntos a la Quest diaria de un jugador
     * @param player
     * @param points
     * @param questType
     */
    public void addPointsToPlayer(Player player, int points, QuestType questType) {
        // Obtiene los datos del jugador
        PlayerQuestData data = playerData.get(player);

        // Verifica si el jugador tiene una Quest asignada
        if (data != null && data.getCurrentQuest() != null) {

            // Verifica si la Quest ha sido completada
            if (data.isCompletedQuests()) return;

            // Verifica si el tipo de Quest es correcto
            if (data.getCurrentQuest().getQuestType() != questType) return;

            int newPoints = data.getCurrentQuest().getCurrentPoints() + points;

            // Verifica si el jugador ha completado la Quest
            if (newPoints >= data.getCurrentQuest().getRequiredPoints()) {
                completeQuest(player);
                data.getCurrentQuest().questCompletedMessage(player);
            } else {
                data.getCurrentQuest().addCurrentPoints(points);
                Logger.info("Player " + player.getName() + "puntos: " + data.getCurrentQuest().getCurrentPoints() + "/" + data.getCurrentQuest().getRequiredPoints());
            }
        }
    }

    public List<Quest> getQuests() {
        return quests;
    }
}
