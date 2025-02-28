package gatoartstudio.qailyquest.quests.types;

import gatoartstudio.qailyquest.quests.Quest;
import gatoartstudio.qailyquest.quests.QuestType;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CraftQuest extends Quest {
    private Material item;

    public CraftQuest(Material item, int requiredPoints) {
        super(QuestType.CRAFT, requiredPoints);
    }

    @Override
    public void addCurrentPoints(int points) {
        this.currentPoints += points;
    }

    public Material getItem() {
        return item;
    }

    public void setItem(Material item) {
        this.item = item;
    }

    @Override
    public void questCompletedMessage(Player player) {

    }

    @Override
    public void questStartedMessage(Player player) {

    }
}
