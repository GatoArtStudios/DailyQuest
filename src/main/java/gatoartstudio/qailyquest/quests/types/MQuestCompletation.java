package gatoartstudio.qailyquest.quests.types;

import gatoartstudio.qailyquest.quests.Quest;
import gatoartstudio.qailyquest.quests.QuestType;
import org.bukkit.entity.Player;

public class MQuestCompletation extends Quest {
    public MQuestCompletation(int requiredPoints) {
        super(QuestType.MQUEST_COMPLETATION, requiredPoints);
    }

    @Override
    public void addCurrentPoints(int points) {
        this.currentPoints += points;
    }

    @Override
    public void questCompletedMessage(Player player) {

    }

    @Override
    public void questStartedMessage(Player player) {

    }
}
