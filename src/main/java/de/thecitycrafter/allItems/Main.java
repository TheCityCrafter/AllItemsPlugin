package de.thecitycrafter.allItems;

import de.thecitycrafter.allItems.Listener.ItemCollectedListener;
import de.thecitycrafter.allItems.Listener.PlayerJoinListener;
import de.thecitycrafter.allItems.Utils.AllItemsItem;
import de.thecitycrafter.allItems.Utils.Bossbar;
import de.thecitycrafter.allItems.commands.MenuCommand;
import de.thecitycrafter.allItems.commands.SkipCommand;
import de.thecitycrafter.allItems.inventories.ItemsInventory;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;


public final class Main extends JavaPlugin {


    private static File dataFile;
    private static FileConfiguration data;

    @Override
    public void onEnable() {
        createDataConfig();

        getCommand("menu").setExecutor(new MenuCommand());
        getCommand("skip").setExecutor(new SkipCommand());
        getServer().getPluginManager().registerEvents(new ItemsInventory(),this );
        getServer().getPluginManager().registerEvents(new ItemCollectedListener(),this );
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(),this );



        if (AllItemsItem.getCurrentItem() == null){
            AllItemsItem.generateCurrentItem();
        }
        Bossbar.setItem();



    }

    @Override
    public void onDisable() {
    }
    public static FileConfiguration getData() {
        return data;
    }

    private void createDataConfig() {
        dataFile = new File(getDataFolder(), "data.yml");
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            saveResource("data.yml", false);
        }

        data = new YamlConfiguration();
        try {
            data.load(dataFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void saveData() {
        try {
            data.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void addItem (Material item){
        List<String> test = data.getStringList("foundItems");
        test.add(item.name());
        data.set("foundItems", test);
        saveData();
    }


}

