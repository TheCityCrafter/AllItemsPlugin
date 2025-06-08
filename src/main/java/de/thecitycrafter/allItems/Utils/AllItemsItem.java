package de.thecitycrafter.allItems.Utils;

import de.thecitycrafter.allItems.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllItemsItem {
    private static FileConfiguration data = Main.getData();

    public static void generateCurrentItem(){

        List<String> foundItems = data.getStringList("foundItems");
        int random = (int) (Math.random() * getAllItems(true).size());
        data.set("currentItem", getAllItems(true).get(random).name());
        Main.saveData();
        for (Player player: Bukkit.getOnlinePlayers()){
            player.sendMessage("§7[§6AllItems§7] Neues Item: §b" + AllItemsItem.getCurrentItem());
        }


    }
    public static String getCurrentItem(){
        return data.getString("currentItem");
    }


    public static List<Material> getAllItems(boolean onlyNotFound){
        List<Material> items = new ArrayList<>(Arrays.asList(Material.values()));
        items.removeIf(mat -> !mat.isItem() || forbiddenItems().contains(mat) || mat.name().endsWith("_SPAWN_EGG"));
        if (onlyNotFound){
            items.removeIf(item -> Main.getData().getStringList("foundItems").contains(item.name()));
        }
        return items;
    }

    public static List<String> getFoundItems(){
        return data.getStringList("foundItems");
    }

    public static void addItem (String item){
        List<String> foundItems = data.getStringList("foundItems");
        foundItems.add(item);
        data.set("foundItems", foundItems);
        Main.saveData();
    }

    public static List<Material> forbiddenItems(){
        List<Material> forbiddenItems = new ArrayList<>();
        forbiddenItems.add(Material.AIR);
        forbiddenItems.add(Material.BARRIER);
        forbiddenItems.add(Material.BEDROCK);
        forbiddenItems.add(Material.STRUCTURE_BLOCK);
        forbiddenItems.add(Material.STRUCTURE_VOID);
        forbiddenItems.add(Material.COMMAND_BLOCK);
        forbiddenItems.add(Material.COMMAND_BLOCK_MINECART);
        forbiddenItems.add(Material.REPEATING_COMMAND_BLOCK);
        forbiddenItems.add(Material.CHAIN_COMMAND_BLOCK);
        forbiddenItems.add(Material.JIGSAW);
        forbiddenItems.add(Material.DEBUG_STICK);
        forbiddenItems.add(Material.LIGHT);
        forbiddenItems.add(Material.REINFORCED_DEEPSLATE);
        forbiddenItems.add(Material.SPAWNER);
        forbiddenItems.add(Material.END_PORTAL_FRAME);
        forbiddenItems.add(Material.KNOWLEDGE_BOOK);
        forbiddenItems.add(Material.TRIAL_SPAWNER);
        forbiddenItems.add(Material.VAULT);
        forbiddenItems.add(Material.SUSPICIOUS_GRAVEL);
        forbiddenItems.add(Material.SUSPICIOUS_SAND);
        return forbiddenItems;
    }
}
