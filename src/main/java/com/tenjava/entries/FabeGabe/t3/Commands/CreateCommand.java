package com.tenjava.entries.FabeGabe.t3.Commands;

import com.tenjava.entries.FabeGabe.t3.TenJava;
import org.bukkit.*;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.Random;

public class CreateCommand implements CommandExecutor {

    TenJava plugin;
    public CreateCommand(TenJava plugin) {
        this.plugin = plugin;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(!(s instanceof Player))
            return true;
        final Player p = (Player) s;
        if(args.length == 0) {
            p.sendMessage(ChatColor.RED + "Created swag explosion!");
            p.sendMessage(ChatColor.GREEN + "Go buy sum muntun duuu nub!");
            p.playSound(p.getLocation(), Sound.EXPLODE, 10, 10);
            p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 2);

            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new BukkitRunnable() {
                @Override
                public void run() {
                    int num = 20;
                    while(num != 0){
                        num--;
                        Random rs = new Random();
                        Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
                        FireworkMeta fwm = fw.getFireworkMeta();
                        fwm.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.STAR).
                                        withColor(Color.GREEN).
                                        withFade(Color.YELLOW).
                                        flicker(rs.nextBoolean()).
                                        trail(rs.nextBoolean()).
                                        build());
                        fw.setFireworkMeta(fwm);
                    }
                }
            }, 0L, 10L);
        } else
            return true;
        return false;
    }
}