package br.com.west.moneytopv2.managers;

import br.com.west.moneytopv2.Main;
import br.com.west.moneytopv2.utils.PlayerMenuUtility;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoneyTopManager {


    private final HashMap<String, Double> topMoney = new HashMap<>();
    public List<Map.Entry<String, Double>> topMoneyOrdened;
    private final HashMap<Player, PlayerMenuUtility> playerMenuUtility = new HashMap<>();

    public PlayerMenuUtility getPlayerMenuUtility(Player player) {
        PlayerMenuUtility playerMenuUtility;
        if (!(getPlayerMenuUtility().containsKey(player))) {
            playerMenuUtility = new PlayerMenuUtility(player);
            getPlayerMenuUtility().put(player, playerMenuUtility);
            return playerMenuUtility;
        } else {
            return getPlayerMenuUtility().get(player);
        }
    }

    public String getPlayerFormattedMoney(double money) {
        return Main.getInstance().format(money);
    }

    public String getPlayerFormattedMoney(Player player) {
        return Main.getInstance().format(Main.getInstance().getEconomy().getBalance(player));
    }

    public HashMap<String, Double> getTopMoney() {
        return topMoney;
    }

    public List<Map.Entry<String, Double>> getTopMoneyOrdened() {
        return topMoneyOrdened;
    }

    public HashMap<Player, PlayerMenuUtility> getPlayerMenuUtility() {
        return playerMenuUtility;
    }
}
