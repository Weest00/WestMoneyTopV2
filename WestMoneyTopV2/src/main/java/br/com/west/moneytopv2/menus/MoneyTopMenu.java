package br.com.west.moneytopv2.menus;

import br.com.west.moneytopv2.Main;
import br.com.west.moneytopv2.managers.MoneyTopManager;
import br.com.west.moneytopv2.utils.ItemBuilder;
import br.com.west.moneytopv2.utils.Menu;
import br.com.west.moneytopv2.utils.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import java.util.Map;

public class MoneyTopMenu extends Menu {
    public MoneyTopMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "§8Ranking de Jogadores (Money)";
    }

    @Override
    public int getSlots() {
        return 45;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        if (event.getSlot() == 40) playerMenuUtility.getViewer().closeInventory();

    }

    @Override
    public void setMenuItems() {
        MoneyTopManager manager = Main.getInstance().getManager();
        int[] slots = {9, 10, 11, 12, 13, 14, 15, 16, 21, 22, 23};
        int i = 1;

        if (manager.getTopMoneyOrdened().isEmpty()) {
            inventory.setItem(22, new ItemBuilder(Material.WEB).name("§cNenhum jogador disponível...").build());
        } else {
            for (Map.Entry<String, Double> topMoney : manager.getTopMoneyOrdened()) {
                inventory.setItem(slots[i], new ItemBuilder(Material.SKULL_ITEM).durability(3).owner(topMoney.getKey())
                        .name("§7" + i + "§7º: §b" + topMoney.getKey())
                        .lore("", " §7Este jogador possui cerca de §2$§f" + manager.getPlayerFormattedMoney(topMoney.getValue()) + "§7 coins.", " §7Posição do jogador: §f" + i + "§fº")
                        .build());
                i++;

            }
        }

        inventory.setItem(39, new ItemBuilder(Material.SKULL_ITEM).durability(3).owner(playerMenuUtility.getViewer().getName()).
                name("§bSuas Informações").
                lore("§7Seu saldo de money atualmente é de §2$§f" + manager.getPlayerFormattedMoney(playerMenuUtility.getViewer()) + "§7.").
                build());

        inventory.setItem(40, new ItemBuilder(Material.ARROW).
                name("§bFechar Menu").
                lore("§7Clique para fechar o menu do ranking.").
                build());

        inventory.setItem(41, new ItemBuilder(Material.HOPPER).
                name("§bAtualização do Ranking").
                lore("§7O ranking de money é atualizado a cada 10 minutos.").
                build());
    }
}
