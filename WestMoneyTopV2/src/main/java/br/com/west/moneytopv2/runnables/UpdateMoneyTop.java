package br.com.west.moneytopv2.runnables;

import br.com.west.moneytopv2.Main;
import br.com.west.moneytopv2.managers.MoneyTopManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UpdateMoneyTop extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§b§lMONEY TOP!");
        Bukkit.broadcastMessage("§fO ranking de jogadores mais ricos acabou de ser atualizado.");
        Bukkit.broadcastMessage("");
        Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F));


        int count = 1;
        MoneyTopManager manager = Main.getInstance().getManager();


        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
            double money = Main.getInstance().getEconomy().getBalance(offlinePlayer);
            manager.getTopMoney().put(offlinePlayer.getName(), money);
            count++;

            if (count > 10) break;

        }

        Stream<Map.Entry<String, Double>> stream = manager.getTopMoney().entrySet().stream()
                .sorted((x, y) -> y.getValue().compareTo(x.getValue()));

        manager.topMoneyOrdened = stream.collect(Collectors.toList());

    }
}
