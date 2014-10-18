package de.ice3ider4.time;

import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 14:39
 */
public class TimePlayer {

    private UUID uuid;
    private long startTime;
    private long endTime = -1;

    public TimePlayer(Player player){
        uuid = player.getUniqueId();
        startTime = System.currentTimeMillis();
    }

    public void setEndTime(long endTime){
        this.endTime = endTime;
    }

    public long getRunnedTime(){
        if (endTime == -1) {
            return 0;
        }
        return endTime - startTime;
    }

    public String getRunnedTimeString(){
        if (endTime == -1) {
            return "Error";
        }
        long runnedTime = endTime - startTime;
        return  String.format("%02d:%02d:%02d",
        TimeUnit.MILLISECONDS.toHours(runnedTime),
        TimeUnit.MILLISECONDS.toMinutes(runnedTime) -
        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(runnedTime)), // The change is in this line
        TimeUnit.MILLISECONDS.toSeconds(runnedTime) -
        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(runnedTime)));
    }

    public UUID getUuid(){
        return uuid;
    }
}
