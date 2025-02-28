package gatoartstudio.qailyquest.quests;

import java.util.Date;

public class PlayerQuestData {
    private Quest currentQuest;
    private Date lastCompletedDate;
    private boolean CompletedQuests = false;

    /**
     * Asigna una Quest a un jugador
     * @param quest
     */
    public void setQuest(Quest quest) {
        this.currentQuest = quest;
    }

    /**
     * Verifica si el jugador ha completado la Quest diaria
     * @return true, si el jugador ha completado la Quest diaria
     */
    public boolean hasCompletedToday() {
        return false;
    }

    /**
     * Devuelve la Quest actual asignada a un jugador
     * @return currentQuest, la Quest actual asignada a un jugador
     */
    public Quest getCurrentQuest() {
        return currentQuest;
    }

    /**
     * Obtiene la fecha de la ultima vez que el jugador ha completado una Quest
     * @return lastCompletedDate, la fecha de la ultima vez que el jugador ha completado una Quest
     */
    public Date getLastCompletedDate() {
        return lastCompletedDate;
    }

    /**
     * Establece la fecha de la ultima vez que el jugador ha completado una Quest
     * @param lastCompletedDate
     */
    public void setLastCompletedDate(Date lastCompletedDate) {
        this.lastCompletedDate = lastCompletedDate;
    }

    /**
     * Verifica si el jugador ha completado todas las Quests
     * @return true, si el jugador ha completado todas las Quests
     */
    public boolean isCompletedQuests() {
        return CompletedQuests;
    }

    /**
     * Establece si el jugador ha completado todas las Quests
     * @param completedQuests
     */
    public void setCompletedQuests(boolean completedQuests) {
        CompletedQuests = completedQuests;
    }
}
