package gatoartstudio.qailyquest.listeners;

import gatoartstudio.qailyquest.DailyQuest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private DailyQuest plugin;

    public PlayerJoinListener(DailyQuest plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Verifica si el jugador es nulo
        if (event.getPlayer() == null) {
            return;
        }
        // Asigna una Quest diaria a un jugador
        plugin.getQuestManager().assignDailyQuest(event.getPlayer());
    }
}
