package de.ice3ider4.utils;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import de.ice3ider4.main.Main;
import org.bukkit.Location;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 10:25
 */
public enum Effects {

    FIREWORKS_SPARK("fireworksSpark"),
    CRIT("crit"),
    CLOUD("cloud");


    private String name;

    Effects(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    /**
     *
     * @param location  Location where the effect should be spawned
     * @param offsetX   The offset which will be added to the x coordinate of the location
     * @param offsetY   The offset which will be added to the y coordinate of the location
     * @param offsetZ   The offset which will be added to the z coordinate of the location
     * @param speed     The speed of the particles
     * @param amount    The amout of particles to be spawned
     */
    public void playEffect(Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount){
        PacketContainer packetContainer = new PacketContainer(PacketType.Play.Server.WORLD_PARTICLES);
        packetContainer.getStrings().write(0,getName());
        packetContainer.getFloat().write(0, (float) location.getX());
        packetContainer.getFloat().write(1, (float) location.getY());
        packetContainer.getFloat().write(2, (float) location.getZ());
        packetContainer.getFloat().write(3, offsetX);
        packetContainer.getFloat().write(4, offsetY);
        packetContainer.getFloat().write(5, offsetZ);
        packetContainer.getFloat().write(6, speed);
        packetContainer.getIntegers().write(0, amount);
        sendPacket(packetContainer,location,20);
    }

    /**
     *
     * @param packetContainer   The packet which should be broadcasted
     * @param location          The location from where it should calculate the distance
     * @param maxDistance       Max distance to broadcast (uses the location from above)
     */
    private void sendPacket(PacketContainer packetContainer, Location location, int maxDistance){
        Main.getProtocolManager().broadcastServerPacket(packetContainer,location,maxDistance);
    }
}
