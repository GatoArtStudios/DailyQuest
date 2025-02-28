package gatoartstudio.qailyquest.quests.types;

import gatoartstudio.qailyquest.quests.Quest;
import gatoartstudio.qailyquest.quests.QuestType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class KillEntityQuest extends Quest {
    private EntityType entityType;

    public KillEntityQuest(EntityType entityType, int requiredPoints) {
        super(QuestType.KILL_ENTITY, requiredPoints);
        this.entityType = entityType;
    }

    @Override
    public void addCurrentPoints(int points) {
        this.currentPoints += points;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    @Override
    public void questCompletedMessage(Player player) {

    }

    @Override
    public void questStartedMessage(Player player) {

    }
}
