package org.lastdice.staffcontrol.sound;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SoundAdapter  {
    final ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
    final PacketContainer packetContainer = protocolManager.createPacket(PacketType.Play.Server.NAMED_SOUND_EFFECT);

    public void sound(Player player, Location location){
        packetContainer.getSoundCategories()
                .write(0, EnumWrappers.SoundCategory.MASTER);

        packetContainer.getSoundEffects()
                .write(0, Sound.ENTITY_GOAT_SCREAMING_PREPARE_RAM);

        packetContainer.getIntegers()
                        .write(0, (int) (location.getX() * 8.0))
                        .write(1, (int) (location.getY() * 8.0))
                        .write(2, (int) (location.getZ() * 8.0));

        packetContainer.getFloat()
                .write(0,1.0f)
                .write(1,1.0f);

        packetContainer.getLongs()
                .write(0, 0L);

        protocolManager.sendServerPacket(player, packetContainer);

    }



}
