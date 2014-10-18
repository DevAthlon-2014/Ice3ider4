package de.ice3ider4.listeners;

import de.ice3ider4.effects.LineEffect;
import de.ice3ider4.main.Main;
import de.ice3ider4.utils.Strings;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
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
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage(Strings.PREFIX + ChatColor.GOLD + player.getName() + " left the server!");
    }

    @EventHandler
    public void onKick(PlayerKickEvent event){
        Player player = event.getPlayer();
        event.setLeaveMessage(Strings.PREFIX + ChatColor.GOLD + player.getName() + " left the server!");
    }

    @EventHandler
    public void onMove(PlayerRealMoveEvent event){
        Player player = event.getPlayer();

        for(LineEffect lineEffect : Main.getEffectManager().getLineEffects()){
            if(lineEffect.checkPlayer(player)){
                player.sendMessage("You walked over a line");
            }
        }
    }
}
