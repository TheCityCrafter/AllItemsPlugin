package de.thecitycrafter.allItems.commands;

import de.thecitycrafter.allItems.inventories.ItemsInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class MenuCommand implements CommandExecutor , TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player player){
            List<Inventory> inventories = ItemsInventory.getItemsInventory();
            if (args.length == 0){
                player.openInventory(inventories.get(0));
                return true;
            }
            try {
                Integer.parseInt(args[0]);
            } catch (Exception e) {
                player.sendMessage("§7[§6AllItems§7] §cEingabe ist keine Zahl.");
                return true;
            }
            if (Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[0]) <= inventories.size()) {
                player.openInventory(inventories.get(Integer.parseInt(args[0])-1));
                return true;
            }
            player.sendMessage("§7[§6AllItems§7] §cUngültige Seite.");

        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> tabCompleter = new ArrayList<>();
        if (args.length == 1){
            tabCompleter.add("Seite");
        }
        return tabCompleter;
    }
}
