package de.ice3ider4.listeners;

import de.ice3ider4.effects.LineEffect;
import de.ice3ider4.utils.Strings;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 10:14
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage(Strings.PREFIX + ChatColor.GOLD +player.getName() + " joined the server!");

        LineEffect lineEffect = new LineEffect(player.getLocation(), player.getLocation().clone().add(0,3,0));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage(Strings.PREFIX + ChatColor.GOLD + player.getName() + " left the server!");
    }
}
