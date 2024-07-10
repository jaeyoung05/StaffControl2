package org.lastdice.staffcontrol.mobControl;

import com.destroystokyo.paper.entity.Pathfinder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.PolarBear;
import org.lastdice.staffcontrol.StaffControl;
import org.lastdice.staffcontrol.mob.MobSpawn;
import org.lastdice.staffcontrol.mob.PolarBearSpawn;

import java.util.List;

public class Move implements MobControl{

    private List<PolarBear> mob = PolarBearSpawn.list;
    public static int moveId;

    @Override
    public void action(Player player) {
        Location location = player.getLocation().add(player.getLocation().getDirection().multiply(13));
//        Location location = (Location) player.getTargetBlockExact(30);
//        Bukkit.getScheduler().cancelTask(moveId);
        moveId = Bukkit.getScheduler().scheduleSyncRepeatingTask(StaffControl.instance, () -> {
            player.sendMessage("이동");
            for (PolarBear polarBear : mob){
                polarBear.setAI(true);
                polarBear.getPathfinder().moveTo(location);
                if (polarBear.getLocation().distance(location) < 4 ){
                    polarBear.setAI(false);
//                    Bukkit.getScheduler().cancelTask(moveId);
                }

            }
        },0, 20);

    }
}
