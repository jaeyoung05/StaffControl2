package org.lastdice.staffcontrol.mobControl;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.PolarBear;
import org.lastdice.staffcontrol.StaffControl;
import org.lastdice.staffcontrol.mob.PolarBearSpawn;

import java.util.List;

public class Attack implements MobControl {

    private List<PolarBear> mob = PolarBearSpawn.list;

    public static int attackId;

    @Override
    public void action(Player player) {
//        Bukkit.getScheduler().cancelTask(attackId);
//        stop();
        Entity entity = player.getTargetEntity(30, true);
        for (PolarBear polarBear : mob) {
            polarBear.setAI(true);
            if (entity == polarBear || entity == player) {
                player.sendMessage("불가능");
            } else {
                polarBear.setTarget((LivingEntity) entity);
            }
        }
        attackId = Bukkit.getScheduler().scheduleSyncRepeatingTask(StaffControl.instance, ()->{
            player.sendMessage("공격");
            for (PolarBear polarBear : mob){
                if (entity.isDead()){
                    polarBear.setAI(false);
                }
            }
        },0,20);
    }
    


    public void stop(){
        for (PolarBear polarBear : mob){
            polarBear.setTarget(null);
            polarBear.setAI(false);
        }
    }


}

