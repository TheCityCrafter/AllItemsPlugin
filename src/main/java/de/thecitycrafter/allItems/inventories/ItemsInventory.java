package de.thecitycrafter.allItems.inventories;

import de.thecitycrafter.allItems.Utils.AllItemsItem;
import de.thecitycrafter.allItems.Utils.ItemBuilder;
import de.thecitycrafter.allItems.inventories.holders.ItemsInventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class ItemsInventory implements Listener {
    public static List<Inventory> getItemsInventory(){
        List<Inventory> inventories = new ArrayList<>();
        List<Material> items = AllItemsItem.getAllItems(false);
        int size = items.size();

        for (int i = 0; i < Math.ceil(size/45.0); i++) {
            Inventory inventory = Bukkit.createInventory(new ItemsInventoryHolder(), 9*6, "All Items " + (i+1));

            for (int j = 0; j < 45 && !items.isEmpty(); j++) {
                if (AllItemsItem.getFoundItems().contains(items.getFirst().name())){
                    inventory.addItem(new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setName("§a" + items.getFirst().name()).toItemStack());
                }else {
                    inventory.addItem(new ItemBuilder(items.getFirst()).toItemStack());
                }
                items.removeFirst();
            }



            for (int j = 45; j < inventory.getSize(); j++) {
                inventory.setItem(j, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).toItemStack());
            }

            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§rZurück").toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§rSchließen").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§rWeiter").toItemStack());
            inventories.add(inventory);
        }
        return inventories;
    }

    public static int getSize() {
        return ItemsInventory.getItemsInventory().size();
    }


    @EventHandler
    public static void onInventoryClick(InventoryClickEvent e){
        if (e.getClickedInventory().getHolder() instanceof  ItemsInventoryHolder){
            e.setCancelled(true);
            List<Inventory> inventories = ItemsInventory.getItemsInventory();
            switch (e.getCurrentItem().getItemMeta().getDisplayName()){
                case "Zurück":{
                    if (Integer.parseInt(e.getView().getTitle().replace("All Items ", "")) == 1){
                        return;
                    }
                    e.getWhoClicked().openInventory(inventories.get(Integer.parseInt(e.getView().getTitle().replace("All Items ", ""))-2));
                    break;
                }
                case "Schließen": {
                    e.getWhoClicked().closeInventory();
                    break;
                }
                case "Weiter":{
                    if (Integer.parseInt(e.getView().getTitle().replace("All Items ", "")) == inventories.size()){
                        return;
                    }
                    e.getWhoClicked().openInventory(inventories.get(Integer.parseInt(e.getView().getTitle().replace("All Items ", ""))));
                    break;
                }
            }
        }
    }

}
