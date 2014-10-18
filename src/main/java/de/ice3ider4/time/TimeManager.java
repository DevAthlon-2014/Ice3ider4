package de.ice3ider4.time;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.UUID;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 14:38
 */
public class TimeManager {
    private HashSet<TimePlayer> timePlayers = new HashSet<TimePlayer>();

    public TimeManager(){
    }

    public void addTimePlayer(TimePlayer timePlayer){
        timePlayers.add(timePlayer);
    }

    public void removeTimePlayer(TimePlayer timePlayer){
        if(timePlayers.contains(timePlayer)){
            timePlayer.endTimer();
            timePlayers.remove(timePlayer);
        }
    }

    public boolean isPlayerAlreadyRunning(Player player){
        for(TimePlayer timePlayer : timePlayers){
            if(timePlayer.getUuid().equals(player.getUniqueId())){
                return true;
            }
        }

        return false;
    }

    public TimePlayer getTimePlayer(Player player){
        for(TimePlayer timePlayer : timePlayers){
            if(timePlayer.getUuid().equals(player.getUniqueId())){
                return timePlayer;
            }
        }

        return null;
    }

    public TimePlayer getTimePlayer(UUID uuid){
        for(TimePlayer timePlayer : timePlayers){
            if(timePlayer.getUuid().equals(uuid)){
                return timePlayer;
            }
        }

        return null;
    }
}
