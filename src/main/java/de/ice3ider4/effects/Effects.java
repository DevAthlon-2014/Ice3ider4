package de.ice3ider4.effects;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import de.ice3ider4.main.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 10:25
 */
public enum Effects {

    CLOUD("cloud"),
    WITCH_MAGIC("witchMagic"),
    NOTE("note"),
    PORTAL("portal"),
    FLAME("flame"),
    LAVA("lava"),
    SLIME("slime"),
    HEART("heart"),
    HUGE_EXPLOSION("hugeexplosion"),
    LARGE_EXPLODE("largeexplode"),
    FIREWORKS_SPARK("fireworksSpark"),
    BUBBLE("bubble"),
    SUSPEND("suspend"),
    DEPTH_SUSPEND("depthSuspend"),
    TOWN_AURA("townaura"),
    CRIT("crit"),
    MAGIC_CRIT("magicCrit"),
    MOB_SPELL("mobSpell"),
    MOB_SPELL_AMBIENT("mobSpellAmbient"),
    SPELL("spell"),
    INSTANT_SPELL("instantSpell"),
    ENCHANTMENT_TABLE("enchantmenttable"),
    EXPLODE("explode"),
    FOOTSTEP("footstep"),
    SPLASH("splash"),
    LARGE_SMOKE("largesmoke"),
    RED_DUST("reddust"),
    SNOWBALL_POOF("snowballpoof"),
    DRIP_WATER("dripWater"),
    DRIP_LAVA("dripLava"),
    SNOW_SHOVEL("snowshovel"),
    ANGRY_VILLAGER("angryVillager"),
    HAPPY_VILLAGER("happerVillager");

    private String name;

    Effects(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    /**
     * Constructs and broadcasts a world particles packet
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
        broadcastPacket(packetContainer,location,60);
    }

    /**
     * Sends a packet to all the players which are in the max allowed distance from the location
     *
     * @param packetContainer   The packet which should be broadcasted
     * @param location          The location from where it should calculate the distance
     * @param maxDistance       Max distance to broadcast (uses the location from above)
     */
    private void broadcastPacket(PacketContainer packetContainer, Location location, int maxDistance){
        Main.getProtocolManager().broadcastServerPacket(packetContainer,location,maxDistance);
    }

    /**
     * Sends a packet to a specific player
     *
     * @param player            The player that should receive the packet
     * @param packetContainer   The packet which should be sent to the player
     */
    private void sendPacket(Player player, PacketContainer packetContainer){
        try {
            Main.getProtocolManager().sendServerPacket(player,packetContainer);
        } catch (InvocationTargetException e) {
            Main.getLogHelper().logSevere("Couldn't send the packet: " + e.getMessage());
        }
    }
}
