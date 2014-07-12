package com.tenjava.entries.FabeGabe.t3.Listeners;

import com.sun.webpane.webkit.unicode.TextNormalizer;
import com.tenjava.entries.FabeGabe.t3.TenJava;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DamageListener implements Listener {

    TenJava plugin;
    public DamageListener(TenJava plugin) {
        this.plugin = plugin;
    }

    public String getName(Entity ent) {
        return ent.getType().name().toLowerCase().substring(0).toUpperCase();
    }

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player))
            return;
        final Player p = (Player) e.getDamager();
        if(e.getEntity() instanceof Player) {
            Player dmgp = (Player) e.getEntity();
            String dmgn = dmgp.getName();
            p.sendMessage("[" + dmgn + "]" + ChatColor.RED + "Why did you that for?! Feel my wrath!");
            dmgp.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 20));
        } else {
            final Entity dmg = e.getEntity();
            String dmgn = getName(dmg);
            p.sendMessage("[" + dmgn + "]" + ChatColor.RED + "Why did you that for?! Feel my wrath!");
            p.playEffect(dmg.getLocation(), Effect.EXTINGUISH, 20);
            dmg.teleport(p);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new BukkitRunnable() {

                @Override
                public void run() {
                    int num = 3;
                    if(num != 0)
                        num--;
                    if(num == 0) {
                        p.damage(e.getDamage());
                        p.sendMessage(ChatColor.GRAY + "Slender got you!");
                        return;
                    }
                    if(num == 3) {
                        double distance = dmg.getLocation().distance(p.getLocation());
                        Location tpLoc = new Location(dmg.getWorld(), distance - 3, distance, distance - 3);
                        dmg.teleport(tpLoc);
                    }
                    if(num == 2) {
                        double distance = dmg.getLocation().distance(p.getLocation());
                        Location tpLoc = new Location(dmg.getWorld(), distance - 2, distance, distance - 2);
                        dmg.teleport(tpLoc);
                    }
                    if(num == 1) {
                        double distance = dmg.getLocation().distance(p.getLocation());
                        Location tpLoc = new Location(dmg.getWorld(), distance - 2, distance, distance - 2);
                        dmg.teleport(tpLoc);
                        TNTPrimed tnt = (TNTPrimed) p.getWorld().spawnEntity(
                                new Location(p.getWorld(),
                                        p.getLocation().getBlockX(),
                                        p.getLocation().getBlockY() - 2,
                                        p.getLocation().getBlockZ()),
                                EntityType.PRIMED_TNT);
                    }
                }
            }, 0L, 20L);
        }
    }

}