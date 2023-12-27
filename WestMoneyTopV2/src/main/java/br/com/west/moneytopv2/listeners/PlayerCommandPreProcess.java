package br.com.west.moneytopv2.listeners;

import br.com.west.moneytopv2.Main;
import br.com.west.moneytopv2.managers.MoneyTopManager;
import br.com.west.moneytopv2.menus.MoneyTopMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreProcess implements Listener {

    @EventHandler
    void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        MoneyTopManager manager = Main.getInstance().getManager();
        if (event.getMessage().startsWith("/money top")) {
            event.setCancelled(true);
            new MoneyTopMenu(manager.getPlayerMenuUtility(event.getPlayer())).open();


        }
    }
}
