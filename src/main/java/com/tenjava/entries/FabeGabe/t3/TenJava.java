package com.tenjava.entries.FabeGabe.t3;

import com.tenjava.entries.FabeGabe.t3.Commands.CreateCommand;
import com.tenjava.entries.FabeGabe.t3.Listeners.DeathListener;
import com.tenjava.entries.FabeGabe.t3.Listeners.ExplodeListener;
import com.tenjava.entries.FabeGabe.t3.Listeners.InteractListener;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {

	public void onEnable() {
        System.out.println("Plugin enabled!");
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getServer().getPluginManager().registerEvents(new ExplodeListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getCommand("swagexplosion").setExecutor(new CreateCommand(this));
	}

    public void onDisable() {
        System.out.println("Plugin disabled!");
    }

}