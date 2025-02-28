package gatoartstudio.qailyquest.quests;

import org.bukkit.entity.Player;

public abstract class Quest {
    protected int requiredPoints; // Cantidad de puntos para completar la mision
    protected int currentPoints = 0; // Cantidad actual de puntos del jugador
    protected QuestType questType; // Tipo de mision
    protected String questName;

    public Quest(QuestType questType, int requiredPoints) {
        this.questType = questType;
        this.requiredPoints = requiredPoints;

        this.questName = switch (questType) {
            case PLANT -> "Plant Quest";
            case KILL_ENTITY -> "Kill Entity Quest";
            case CRAFT -> "Craft Quest";
            case WALK -> "Walk Quest";
            case MQUEST_COMPLETATION -> "MQuest Completation Quest";
        };
    }

    /**
     * Returns the amount of points required to complete the quest
     *
     * @return the required points
    */
    public int getRequiredPoints() {
        return requiredPoints;
    }

    /**
     * Sets the amount of points required to complete the quest
     *
     * @param requiredPoints the required points
     */
    public void setRequiredPoints(int requiredPoints) {
        this.requiredPoints = requiredPoints;
    }

    /**
     * Returns the current amount of points the player has towards completing the quest.
     *
     * @return the current points
     */
    public int getCurrentPoints() {
        return currentPoints;
    }

    /**
     * Sets the current amount of points the player has towards completing the quest.
     * @param currentPoints the current points
     */
    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    /**
     * Returns the type of the quest.
     *
     * @return the type of the quest
     */
    public QuestType getQuestType() {
        return questType;
    }

    /**
     * Sets the type of the quest.
     * @param questType the type of the quest
     */
    public void setQuestType(QuestType questType) {
        this.questType = questType;
    }


    /**
     * Returns the name of the quest.
     * @return the name of the quest
     */
    public String getQuestName() {
        return questName;
    }

    /**
     * Sets the name of the quest.
     *
     * @param questName the new name of the quest
     */
    public void setQuestName(String questName) {
        this.questName = questName;
    }

    /**
     * Adds the given amount to the current points of the quest.
     * @param points the amount to add
     */



    public abstract void addCurrentPoints(int points);

    public abstract void questCompletedMessage(Player player);

    public abstract void questStartedMessage(Player player);

}
