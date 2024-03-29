package br.com.west.moneytopv2.listeners;

import br.com.west.moneytopv2.utils.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class InventoryClick implements Listener {

    @EventHandler
    void onClickInventory(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof Menu) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) return;
            Menu menu = (Menu) holder;
            menu.handleMenu(event);
        }

    }
}
