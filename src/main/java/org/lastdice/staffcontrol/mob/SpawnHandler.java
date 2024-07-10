package org.lastdice.staffcontrol.mob;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.lastdice.staffcontrol.customItem.Circle;
import org.lastdice.staffcontrol.sound.SoundAdapter;

public class SpawnHandler implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void spawn(PlayerInteractEvent event){
        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();

        if (event.getAction() == Action.RIGHT_CLICK_AIR & itemStack.getType() == Material.STICK){
            if (event.getPlayer().isSneaking()){
                Circle.circle(event.getPlayer());
//                SoundAdapter soundAdapter = new SoundAdapter();
//                soundAdapter.sound(event.getPlayer());
                MobSpawn mobSpawn = new PolarBearSpawn();
                mobSpawn.mobSpawn(event.getPlayer());
            }
        }

    }

}
