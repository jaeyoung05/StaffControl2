package org.lastdice.staffcontrol.customItem;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Staff {

    public static CustomStack stack = CustomStack.getInstance("staff");

    public static void staff(Player player){
        if (stack != null) {
            ItemStack itemStack = stack.getItemStack();
            player.getInventory().addItem(itemStack);
        }else {
            player.sendMessage("2");
        }
    }

}
