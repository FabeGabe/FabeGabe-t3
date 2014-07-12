package com.tenjava.entries.FabeGabe.t3.Listeners;

/**
 * Created by phoenixros on 7/12/14.
 */

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import java.util.Random;

public class InteractListener implements Listener {

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();
        Random r = new Random();
        int s = r.nextInt(20);
        int x = r.nextInt(40);
        ItemStack hand = p.getItemInHand();
        Location shpSp = new Location(p.getWorld(),
                p.getLocation().getBlockX() + s,
                p.getLocation().getBlockY() + 20,
                p.getLocation().getBlockZ() + x);
        Location fwSp = new Location(p.getWorld(),
                p.getLocation().getBlockX() + r.nextInt(22),
                p.getLocation().getBlockY() + r.nextInt(5),
                p.getLocation().getBlockZ() + x);
        switch(a) {
            case PHYSICAL:
                Sheep shp = (Sheep) p.getWorld().spawnEntity(shpSp, EntityType.SHEEP);
                shp.setSheared(true);
                shp.setCustomNameVisible(true);
                shp.setCustomName(ChatColor.LIGHT_PURPLE + "Swag");
                Firework fw = (Firework) p.getWorld().spawnEntity(fwSp, EntityType.FIREWORK);
                FireworkMeta fwm = fw.getFireworkMeta();
                fwm.addEffect(FireworkEffect.builder().
                        flicker(Boolean.valueOf(r.nextBoolean()))
                        .with(FireworkEffect.Type.STAR).
                                withColor(Color.GREEN).build());
                fw.setFireworkMeta(fwm);
                p.getWorld().createExplosion(fwSp, (float) s);

                break;
            case RIGHT_CLICK_BLOCK:
                if(e.getClickedBlock().getType() == Material.TNT){
                    e.getClickedBlock().setType(Material.AIR);
                    p.sendMessage(ChatColor.RED + "Creating swag bomb...");
                    e.getClickedBlock().getWorld().spawnEntity(
                            e.getClickedBlock().getLocation(),
                            EntityType.PRIMED_TNT);
                } else return;
                break;
            case LEFT_CLICK_AIR:
                if(hand.getType() == Material.APPLE) {
                    Item item = (Item) p.getWorld().spawnEntity(p.getLocation(), EntityType.DROPPED_ITEM);
                    item.setItemStack(new ItemStack(Material.APPLE, 1));
                    item.setVelocity(new Vector(p.getLocation().getBlockX() + 20,
                            p.getLocation().getBlockY(), p.getLocation().getBlockZ() + 20));
                    item.setPickupDelay(20);
                    Projectile projectile = p.launchProjectile(Arrow.class,
                            new Vector(p.getLocation().getBlockX() + 20,
                                    p.getLocation().getBlockY(),
                                    p.getLocation().getBlockZ() + 20));
                    projectile.setBounce(true);
                    projectile.setShooter(p);
                    projectile.setPassenger(item);
                    projectile.getWorld().createExplosion(projectile.getLocation().getBlockX(),
                            projectile.getLocation().getBlockY(),
                            projectile.getLocation().getBlockZ(),
                            5,
                            true,
                            true);
                } else
                    return;
                break;
            case RIGHT_CLICK_AIR:
                if(hand.getType() == Material.BONE) {
                    Wolf wolf = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
                    wolf.setCustomNameVisible(true);
                    wolf.setCustomName("Yo dawg.");
                    wolf.playEffect(EntityEffect.FIREWORK_EXPLODE);
                    p.playEffect(wolf.getLocation(), Effect.EXTINGUISH, 20);
                } else
                    return;
            default:
                break;
        }
    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        Entity ent = e.getRightClicked();
        ItemStack hand = p.getItemInHand();
        if(ent instanceof IronGolem) {
            IronGolem golem = (IronGolem) ent;
            if(hand.getType() == Material.ARROW) {
                golem.setPlayerCreated(true);
                golem.setCustomNameVisible(true);
                golem.setCustomName(ChatColor.RED + p.getName());
                p.playEffect(golem.getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
            } else
                return;
        }
    }

}