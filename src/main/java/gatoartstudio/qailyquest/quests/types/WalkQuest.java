package gatoartstudio.qailyquest.quests.types;

import gatoartstudio.qailyquest.quests.Quest;
import gatoartstudio.qailyquest.quests.QuestType;
import org.bukkit.entity.Player;

public class WalkQuest extends Quest {
    public WalkQuest(int requiredPoints) {

        super(QuestType.WALK, requiredPoints);
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
