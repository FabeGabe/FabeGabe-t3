package com.tenjava.entries.FabeGabe.t3;

import com.tenjava.entries.FabeGabe.t3.Commands.CreateCommand;
import com.tenjava.entries.FabeGabe.t3.Listeners.DamageListener;
import com.tenjava.entries.FabeGabe.t3.Listeners.ExplodeListener;
import com.tenjava.entries.FabeGabe.t3.Listeners.InteractListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class TenJava extends JavaPlugin {

    private static List<String> playersOnline = new ArrayList<String>();

    public static List<String> getPlayersOnline() {
        return playersOnline;
    }

	public void onEnable() {
        System.out.println("Plugin enabled!");
        getServer().getPluginManager().registerEvents(new DamageListener(this), this);
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getServer().getPluginManager().registerEvents(new ExplodeListener(), this);
        getCommand("swagexplosion").setExecutor(new CreateCommand(this));
	}

    public void onDisable() {
        System.out.println("Plugin disabled!");
    }

}