package de.thecitycrafter.allItems.Listener;

import de.thecitycrafter.allItems.Utils.AllItemsItem;
import de.thecitycrafter.allItems.Utils.Bossbar;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;


public class ItemCollectedListener implements Listener {
    @EventHandler
    public void onItemCollected(EntityPickupItemEvent e){
        if (e.getEntity() instanceof Player){
            if (AllItemsItem.getCurrentItem().equals(e.getItem().getItemStack().getType().name())){
                for (Player player: Bukkit.getOnlinePlayers()){
                    player.sendMessage("§7[§6AllItems§7] §b" + AllItemsItem.getCurrentItem() + "§7 wurde gefunden.");
                    player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                }

                AllItemsItem.addItem(AllItemsItem.getCurrentItem());
                AllItemsItem.generateCurrentItem();
                Bossbar.setItem();

            }
        }
    }
}
