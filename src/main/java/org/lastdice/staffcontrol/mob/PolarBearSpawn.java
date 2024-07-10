package org.lastdice.staffcontrol.mob;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.PolarBear;
import org.lastdice.staffcontrol.sound.SoundAdapter;

import java.util.ArrayList;
import java.util.List;

public class PolarBearSpawn implements MobSpawn {

    public static List<PolarBear> list = new ArrayList<>();

    @Override
    public void mobSpawn(Player player) {
        World world = player.getWorld();
        Location location = player.getLocation().add(player.getLocation().getDirection().multiply(3));

        org.bukkit.entity.PolarBear polarBear = world.spawn(location, org.bukkit.entity.PolarBear.class, entity -> {
            SoundAdapter soundAdapter = new SoundAdapter();
            soundAdapter.sound(player, location);
            entity.setAI(false);
            list.add(entity);
        });
        player.sendMessage(String.valueOf(list.size()));
    }


//    public static void aa(){
//        for (org.bukkit.entity.PolarBearSpawn polarBear : list){
//            polarBear.getPathfinder().moveTo()
//        }
//    }

}
