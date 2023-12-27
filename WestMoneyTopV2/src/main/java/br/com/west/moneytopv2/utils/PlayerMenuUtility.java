package br.com.west.moneytopv2.utils;

import org.bukkit.entity.Player;

public class PlayerMenuUtility {

    private final Player viewer;

    public PlayerMenuUtility(Player player) {
        this.viewer = player;
    }

    public Player getViewer() {
        return viewer;
    }
}
