package de.thecitycrafter.allItems.Listener;

import de.thecitycrafter.allItems.Utils.Bossbar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent e){
        Bossbar.addPlayers();
    }
}
