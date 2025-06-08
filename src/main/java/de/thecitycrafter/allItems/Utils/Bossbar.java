package de.thecitycrafter.allItems.Utils;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class Bossbar {
    private static BossBar bossBar = null;
    private static void createBossbar(){
        bossBar = Bukkit.createBossBar("ITEM", BarColor.RED, BarStyle.SOLID);
        bossBar.setVisible(true);
        addPlayers();
    }
    public static void addPlayers(){
        for (Player player: Bukkit.getOnlinePlayers()){
            bossBar.addPlayer(player);
        }
    }

    public static void setItem(){
        if (bossBar == null){
            createBossbar();
        }
        bossBar.setTitle(AllItemsItem.getCurrentItem());
    }


}
