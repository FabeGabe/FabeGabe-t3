package com.tenjava.entries.FabeGabe.t3.Listeners;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Random;

public class SpawnListener implements Listener {

    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent e) {
        Entity ent = e.getEntity();
        Random r = new Random();
        if(!(ent instanceof Spider))
            return;
        Spider spider = (Spider) ent;
        switch(r.nextInt(10)) {
            case 10:
                Creeper creeper = (Creeper) spider.getWorld().spawnCreature(spider.getLocation(), EntityType.CREEPER);
                Bat bat = (Bat) spider.getWorld().spawnCreature(spider.getLocation(), EntityType.BAT);
                spider.setPassenger(creeper);
                bat.setPassenger(spider);
                break;
            default:
                break;
        }
    }

}
