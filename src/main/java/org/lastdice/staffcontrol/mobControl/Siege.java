package org.lastdice.staffcontrol.mobControl;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.PolarBear;
import org.lastdice.staffcontrol.StaffControl;
import org.lastdice.staffcontrol.mob.PolarBearSpawn;

import java.util.ArrayList;
import java.util.List;

public class Siege implements MobControl {

    public static int siegeId;

    private static int distance = 7;

    public static List<PolarBear> mob = PolarBearSpawn.list;

    @Override
    public void action(Player player) {
//        Bukkit.getScheduler().cancelTask(siegeId);
        Entity entity = player.getTargetEntity(20, true);
        for (PolarBear polarBear : mob) {
            polarBear.setAI(true);
        }


//        Location location = entity.getLocation().add(Math.PI)

        if (entity != null) {
            player.sendMessage(entity.getName());
        } else {
            player.sendMessage("없음");
        }
//        Location location = entity.getLocation();
//        Bukkit.getScheduler().cancelTask(siegeId);
        siegeId = Bukkit.getScheduler().scheduleSyncRepeatingTask(StaffControl.instance, () -> {
            player.sendMessage("포위");

            for (int i = 0; i < mob.size(); i++) {
                double angle = i * 2 * Math.PI / mob.size();
                int x = (int) (entity.getLocation().getX() + distance * Math.cos(angle));
                int z = (int) (entity.getLocation().getZ() + distance * Math.sin(angle));
                Location location = new Location(player.getWorld(), x, entity.getY(), z);
                mob.get(i).getPathfinder().moveTo(location);

                if (mob.get(i).getLocation().distance(location) < 2) {
                    mob.get(i).setTarget((LivingEntity) entity);
                    int finalI = i;
                    Bukkit.getScheduler().scheduleSyncDelayedTask(StaffControl.instance, () -> {
                        mob.get(finalI).setTarget(null);
                        mob.get(finalI).setAI(false);
                    }, 5);
                }
            }
        }, 0, 20);

    }

}


//    public static void location(Player player, Entity entity){
//
//        for (int i = 0; i < mob.size(); i++) {
//            double angle = i * 2 * Math.PI/mob.size();
//            int x = (int) (entity.getLocation().getX() + distance * Math.cos(angle));
//            int z = (int) (entity.getLocation().getZ() + distance * Math.sin(angle));
//            Location location = new Location(player.getWorld(), x,entity.getY(),z);
//            mob.get(i).getPathfinder().moveTo(location);
//
//            }
//        }
//
//    }


