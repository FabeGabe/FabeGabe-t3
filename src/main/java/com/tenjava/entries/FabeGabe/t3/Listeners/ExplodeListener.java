package com.tenjava.entries.FabeGabe.t3.Listeners;

import org.bukkit.Color;
import org.bukkit.EntityEffect;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import java.util.List;

public class ExplodeListener implements Listener {

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        Entity ent = e.getEntity();
        List<Entity> entities = ent.getNearbyEntities(10, 10, 10);
        if(!(ent instanceof TNTPrimed))
            return;
        for(Entity entity : entities) {
            entity.setVelocity(new Vector(0, 2, 0));
            entity.setFallDistance(0);
            entity.setFireTicks(20);
            entity.playEffect(EntityEffect.FIREWORK_EXPLODE);
            Firework fw1 = (Firework) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.FIREWORK);
            Firework fw2 = (Firework) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.FIREWORK);
            FireworkMeta fw1m = fw1.getFireworkMeta();
            FireworkMeta fw2m = fw2.getFireworkMeta();
            fw1m.addEffect(FireworkEffect.builder().withColor(Color.GREEN).with(FireworkEffect.Type.BALL_LARGE)
                    .withFade(Color.NAVY)
                    .flicker(true).trail(true).build());
            fw2m.addEffect(FireworkEffect.builder().withColor(Color.RED).
                    with(FireworkEffect.Type.BURST).withFade(Color.AQUA).flicker(true).trail(false).build());
            fw1.setFireworkMeta(fw1m);
            fw2.setFireworkMeta(fw2m);
        }
    }
}