package de.ice3ider4.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 13:18
 */
public class EventCaller implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onMove(PlayerMoveEvent event){
        //Checks if the player really moved on the X or Z axis
        if ((event.getFrom().getBlockX() != event.getTo().getBlockX()) || (event.getFrom().getBlockY() != event.getTo().getBlockY()) || (event.getFrom().getBlockZ() != event.getTo().getBlockZ()))
        {
            PlayerRealMoveEvent call = new PlayerRealMoveEvent(event.getPlayer(), event.getFrom(), event.getTo());
            Bukkit.getPluginManager().callEvent(call);
            if (call.isCancelled()) event.getPlayer().teleport(event.getFrom());
        }
    }
}
