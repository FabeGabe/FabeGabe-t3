package com.tenjava.entries.FabeGabe.t3.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Random;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Random r = new Random();
        ItemStack head = new ItemStack(Material.SKULL, 1);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        skullMeta.setOwner(p.getName());
        skullMeta.setDisplayName(ChatColor.GOLD + p.getName() + "'s Head");
        head.setItemMeta(skullMeta);
        switch (r.nextInt(10)) {
            case 10:
                e.getDrops().add(head);
                break;
            default:
                break;
        }
    }

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onEntityDeath(EntityDeathEvent e) {
        Entity entity = e.getEntity();
        Random r = new Random();
        ItemStack witherSkull = new ItemStack(Material.SKULL_ITEM, 1);
        witherSkull.setTypeId(1);
        switch(r.nextInt(20)) {
            case 20:
                switch(entity.getType()) {
                    case COW:
                        e.getDrops().add(new ItemStack(Material.COOKED_BEEF, r.nextInt(4)));
                        break;
                    case MUSHROOM_COW:
                        e.getDrops().add(new ItemStack(Material.MUSHROOM_SOUP, r.nextInt(3)));
                        break;
                    case ENDERMAN:
                        e.getDrops().add(new ItemStack(Material.EYE_OF_ENDER, 1));
                        break;
                    case ENDER_DRAGON:
                        e.getDrops().add(new ItemStack(Material.DRAGON_EGG, 1));
                        break;
                    case SPIDER:
                        e.getDrops().add(new ItemStack(Material.FERMENTED_SPIDER_EYE, 1));
                        break;
                    case PIG:
                        e.getDrops().add(new ItemStack(Material.GRILLED_PORK, r.nextInt(3)));
                        break;
                    case PIG_ZOMBIE:
                        e.getDrops().add(new ItemStack(Material.GOLD_INGOT, r.nextInt(3)));
                        break;
                    case CREEPER:
                        e.getDrops().add(new ItemStack(Material.TNT, 1));
                        break;
                    case WITHER:
                        e.getDrops().add(witherSkull);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        switch(r.nextInt(100)) {
            case 100:
                switch(entity.getType()) {
                    case HORSE:
                        e.getDrops().add(new ItemStack(Material.SADDLE, 1));
                        break;
                    default:
                        break;
                }
        }
    }

}
