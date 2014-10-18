package de.ice3ider4.listeners;

import de.ice3ider4.effects.LineEffect;
import de.ice3ider4.effects.LineTyp;
import de.ice3ider4.main.Main;
import de.ice3ider4.time.TimeManager;
import de.ice3ider4.time.TimePlayer;
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

                TimeManager timeManager = Main.getTimeManager();

                if(lineEffect.getLineTyp().equals(LineTyp.STARTLINE)){
                   if(timeManager.isPlayerAlreadyRunning(player)){
                       player.sendMessage(Strings.PREFIX + ChatColor.DARK_RED + "You are already running!");
                   }
                    else{
                       player.sendMessage(Strings.PREFIX + ChatColor.GREEN + "Timer started!");
                       timeManager.addTimePlayer(new TimePlayer(player));
                   }
                }
                else if(lineEffect.getLineTyp().equals(LineTyp.ENDLINE)){
                    if(!(timeManager.isPlayerAlreadyRunning(player))){
                        player.sendMessage(Strings.PREFIX + ChatColor.DARK_RED + "You haven't started your timer!");
                    }
                    else{
                        TimePlayer timePlayer = timeManager.getTimePlayer(player);
                        timePlayer.setEndTime(System.currentTimeMillis());
                        player.sendMessage(Strings.PREFIX + ChatColor.GOLD + "Your time was: " + timePlayer.getRunnedTimeString());
                        timeManager.removeTimePlayer(timePlayer);
                    }
                }
                break;
            }
        }
    }
}
