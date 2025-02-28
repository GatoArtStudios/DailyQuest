package gatoartstudio.qailyquest.quests.types;

import gatoartstudio.qailyquest.quests.Quest;
import gatoartstudio.qailyquest.quests.QuestType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PlantQuest extends Quest {
    private Material seed;

    public PlantQuest(Material seed, int requiredPoints) {
        super(QuestType.PLANT, requiredPoints);
        this.seed = seed;
    }

    public void addCurrentPoints(int points) {
        // logic
        this.currentPoints += points;
    }

    public Material getSeed() {
        return seed;
    }

    @Override
    public void questCompletedMessage(Player player) {
        player.sendMessage("Has completado la mision de semillas.");
    }

    @Override
    public void questStartedMessage(Player player) {
        Component message = Component.text("Has empezado la mision de semillas, siembra ")
                .append(Component.text(this.requiredPoints))
                .append(Component.text(" de "))
                .append(Component.translatable(seed.translationKey()));

        player.sendMessage(message);
    }
}
