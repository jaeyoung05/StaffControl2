package org.lastdice.staffcontrol.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.lastdice.staffcontrol.customItem.Circle;
import org.lastdice.staffcontrol.customItem.Staff;
import org.lastdice.staffcontrol.mob.MobSpawn;
import org.lastdice.staffcontrol.mobControl.Attack;
import org.lastdice.staffcontrol.mobControl.MobControl;
import org.lastdice.staffcontrol.mobControl.Siege;
import org.lastdice.staffcontrol.sound.SoundAdapter;

public class Command extends BukkitCommand {

    public Command(@NotNull String name) {
        super(name);
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)){
            return false;
        }

        if (strings.length == 0){
            return false;
        }

        switch (strings[0].toLowerCase()){

            case "staff" -> {
                Staff.staff(player);
            }

            case "d" -> {
                Attack attack = new Attack();
                attack.stop();

            }


        }

        return false;
    }
}
