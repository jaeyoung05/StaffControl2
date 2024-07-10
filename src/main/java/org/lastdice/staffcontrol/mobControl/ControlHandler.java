package org.lastdice.staffcontrol.mobControl;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerUnleashEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.lastdice.staffcontrol.sound.SoundAdapter;

public class ControlHandler implements Listener {



    @EventHandler
    public void move(PlayerInteractEvent event){
        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();
        if (event.getAction() == Action.RIGHT_CLICK_AIR & itemStack.getType() == Material.STICK){
           if (!event.getPlayer().isSneaking()){
//                stop(Siege.siegeId);
//                stop(Attack.attackId);
               Attack attack = new Attack();
               attack.stop();
               stopId();
               MobControl mobControl  = new Move();
               mobControl.action(event.getPlayer());
           }
        }
    }

    @EventHandler
    public void attack(PlayerInteractEvent event){
        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();
        if (event.getAction() == Action.LEFT_CLICK_AIR & itemStack.getType() == Material.STICK){
            if (!event.getPlayer().isSneaking()){
//                stop(Siege.siegeId);
//                stop(Move.moveId);
                stopId();
                MobControl mobControl = new Attack();
                mobControl.action(event.getPlayer());
            }
        }
    }

    @EventHandler
    public void siege(PlayerInteractEvent event){
        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();
        if (event.getAction() == Action.LEFT_CLICK_AIR & itemStack.getType() == Material.STICK){
            if (event.getPlayer().isSneaking()){
//                stop(Move.moveId);
//                stop(Attack.attackId);
                stopId();
                MobControl mobControl = new Siege();
                mobControl.action(event.getPlayer());
            }
        }
    }

    @EventHandler
    public static void stop(PlayerInteractEvent event){
        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK ){
            stopId();
        }
    }

    public static void stopId(){
        Attack attack = new Attack();
        attack.stop();
        Bukkit.getScheduler().cancelTask(Attack.attackId);
        Bukkit.getScheduler().cancelTask(Move.moveId);
        Bukkit.getScheduler().cancelTask(Siege.siegeId);

    }

}
