package de.thecitycrafter.allItems.commands;

import de.thecitycrafter.allItems.Main;
import de.thecitycrafter.allItems.Utils.AllItemsItem;
import de.thecitycrafter.allItems.Utils.Bossbar;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkipCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        for (Player player: Bukkit.getOnlinePlayers()){
            player.sendMessage("§7[§6AllItems§7] §b" + AllItemsItem.getCurrentItem() + "§7 wurde übersprungen.");
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 10, 1);
        }
        AllItemsItem.addItem(AllItemsItem.getCurrentItem());
        AllItemsItem.generateCurrentItem();
        Bossbar.setItem();

        return true;
    }
}
