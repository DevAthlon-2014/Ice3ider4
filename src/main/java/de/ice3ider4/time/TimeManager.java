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

    /**
     * Adds a TimePlayer to the list of TimePalyers
     *
     * @param timePlayer    The TimePlayer that should be added
     */
    public void addTimePlayer(TimePlayer timePlayer){
        timePlayers.add(timePlayer);
    }

    /**
     * Removes a TimePlayer if it really exists
     *
     * @param timePlayer    The TimePlayer that should be removed
     */
    public void removeTimePlayer(TimePlayer timePlayer){
        if(timePlayers.contains(timePlayer)){
            timePlayer.endTimer();
            timePlayers.remove(timePlayer);
        }
    }

    /**
     * Removes a player if he really exists
     * @see removeTimePlayer
     * @see getTimePlayer
     *
     * @param player    The player that should be removed
     */
    public void removePlayer(Player player){
        removeTimePlayer(getTimePlayer(player));
    }

    /**
     * Checks if the Player is running
     *
     * @param player    The player that should be checked
     * @return          True if the player is running and false if not
     */
    public boolean isPlayerRunning(Player player){
        for(TimePlayer timePlayer : timePlayers){
            if(timePlayer.getUuid().equals(player.getUniqueId())){
                return true;
            }
        }

        return false;
    }

    /**
     * Gets the TimePlayer to the specific player
     *
     * @param player        The player that we want to get the TimePlayer object from
     * @return TimePlayer   Returns the TimePlayer of null if not found
     */
    public TimePlayer getTimePlayer(Player player){
        for(TimePlayer timePlayer : timePlayers){
            if(timePlayer.getUuid().equals(player.getUniqueId())){
                return timePlayer;
            }
        }

        return null;
    }

    /**
     * Gets the TimePlayer by the UUID of a player
     *
     * @param uuid          UUID of the player we want to find
     * @return TimePlayer   Returns the TimePlayer of null if not found
     */
    public TimePlayer getTimePlayer(UUID uuid){
        for(TimePlayer timePlayer : timePlayers){
            if(timePlayer.getUuid().equals(uuid)){
                return timePlayer;
            }
        }

        return null;
    }
}
