package br.com.west.moneytopv2;

import br.com.west.moneytopv2.managers.MoneyTopManager;
import br.com.west.moneytopv2.runnables.UpdateMoneyTop;
import br.com.west.moneytopv2.utils.ClassGeter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    private MoneyTopManager manager;
    private Economy econ = null;

    public void onEnable() {
        instance = this;
        manager = new MoneyTopManager();
        new ClassGeter().setupListeners();
        saveDefaultConfig();
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new UpdateMoneyTop(), 0, 20 * 60 * getConfig().getInt("tempo-update"));

        if (!setupEconomy()) {
            Bukkit.getConsoleSender().sendMessage("§cVault não encontrado, desligando o plugin.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    public void onDisable() {

    }

    public String format(Double value) {
        String[] suffix = {
                "K", "M", "B", "T", "Q", "QQ", "S", "SS", "OC", "N",
                "D", "UN", "DD",
                "TR", "QT", "QN", "SD", "SSD", "OD", "ND",
                "VG", "UVG", "DVG", "TVG", "QVG", "QVN", "SEV", "SPV", "OVG",
                "NVG",
                "TG"};
        int size = (value.intValue() != 0) ? (int) Math.log10(value) : 0;
        if (size >= 3)
            while (size % 3 != 0)
                size--;
        double notation = Math.pow(10.0D, size);
        return (size >= 3) ? (
                String.valueOf(Math.round(value / notation * 100.0D) / 100.0D) + suffix[size / 3 - 1]) : String.valueOf(value.doubleValue());
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public Economy getEconomy() {
        return econ;
    }

    public MoneyTopManager getManager() {
        return manager;
    }

    public static Main getInstance() {
        return instance;
    }
}
