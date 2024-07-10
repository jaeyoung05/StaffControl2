package org.lastdice.staffcontrol.customItem;

import com.google.common.util.concurrent.AtomicDouble;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.checkerframework.checker.units.qual.A;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;
import org.lastdice.staffcontrol.StaffControl;
import org.lastdice.staffcontrol.sound.SoundAdapter;

public class Circle {

    public static CustomStack stack = CustomStack.getInstance("circle");

    public static void circle(Player player){
        World world = player.getWorld();
        ItemStack itemStack = stack.getItemStack();
        //setPitch
        Location location = player.getLocation().add(player.getEyeLocation().getDirection().multiply(3)).add(0,1,0);
        world.spawn(location, ItemDisplay.class, itemDisplay -> {
            SoundAdapter soundAdapter = new SoundAdapter();
            soundAdapter.sound(player, location);
            itemDisplay.setItemStack(itemStack);
            Transformation transformation = itemDisplay.getTransformation();
//            transformation.getScale().add(3,3,3);
//            itemDisplay.setTransformation(transformation);
            AtomicDouble atomicDouble = new AtomicDouble();
            Bukkit.getScheduler().scheduleSyncRepeatingTask(StaffControl.instance, () ->{

                itemDisplay.setTransformation(transformation);
                double angle = atomicDouble.getAndAdd(15);
                float ang = (float)Math.toRadians(angle);
                itemDisplay.setTransformation(
                        new Transformation(
                                new Vector3f(),
                                new AxisAngle4f(),
                                new Vector3f(5,5,5),
                                new AxisAngle4f(ang,0,0,1)
                        )
                );
                },0,1);

            Bukkit.getScheduler().scheduleSyncDelayedTask(StaffControl.instance, () -> {
                itemDisplay.remove();
            },20*1);

        } );
    }

}
