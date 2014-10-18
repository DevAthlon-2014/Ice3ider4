package de.ice3ider4.listeners;

import de.ice3ider4.effects.LineEffect;
import de.ice3ider4.effects.LineTyp;
import de.ice3ider4.main.Main;
import de.ice3ider4.time.TimeManager;
import de.ice3ider4.time.TimePlayer;
import de.ice3ider4.utils.Strings;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
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

    private TimeManager timeManager;

    public PlayerListener(TimeManager timeManager){
        this.timeManager = timeManager;
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage(Strings.PREFIX + Strings.PLAYER_JOIN.replace("%player%",player.getName()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage(Strings.PREFIX + Strings.PLAYER_LEAVE.replace("%player%",player.getName()));
        if(timeManager.isPlayerRunning(player)){
            timeManager.removePlayer(player);
        }
    }

    @EventHandler
    public void onKick(PlayerKickEvent event){
        Player player = event.getPlayer();
        event.setLeaveMessage(Strings.PREFIX + Strings.PLAYER_LEAVE.replace("%player%",player.getName()));
        if(timeManager.isPlayerRunning(player)){
            timeManager.removePlayer(player);
        }
    }

    @EventHandler
    public void onMove(PlayerRealMoveEvent event){
        Player player = event.getPlayer();

        for(LineEffect lineEffect : Main.getEffectManager().getLineEffects()){

            //Checks if the player walked over a line
            if(lineEffect.checkPlayer(player)){

                if(lineEffect.getLineTyp().equals(LineTyp.STARTLINE)){
                   //Checks if the player is already running
                   if(timeManager.isPlayerRunning(player)){
                       player.sendMessage(Strings.PREFIX + Strings.ALREADY_RUNNING);
                   }
                    else{
                       player.sendMessage(Strings.PREFIX + Strings.TIMER_STARTED);
                       player.setGameMode(GameMode.SURVIVAL);
                       //Start a new Timer
                       timeManager.addTimePlayer(new TimePlayer(player));
                   }
                }
                else if(lineEffect.getLineTyp().equals(LineTyp.ENDLINE)){
                    //Checks if the player is already running
                    if(!(timeManager.isPlayerRunning(player))){
                        player.sendMessage(Strings.PREFIX + Strings.NOT_STARTED);
                    }
                    else{
                        TimePlayer timePlayer = timeManager.getTimePlayer(player);

                        //Sets the ending time and prints the time needed to walk from the start to the end
                        timePlayer.setEndTime(System.currentTimeMillis());
                        player.sendMessage(Strings.PREFIX + Strings.TIMER_ENDED.replace("%time%",timePlayer.getRunnedTimeString()));

                        timeManager.removeTimePlayer(timePlayer);
                    }
                }

                break;
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        if(timeManager.isPlayerRunning(player)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        if(timeManager.isPlayerRunning(player)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        Entity entity = event.getEntity();
        if(entity instanceof Player){
            Player player = (Player) entity;
            if(timeManager.isPlayerRunning(player)){
                event.setCancelled(true);
            }
        }
    }

}
