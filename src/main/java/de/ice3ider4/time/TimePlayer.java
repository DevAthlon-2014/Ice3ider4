package de.ice3ider4.time;

import de.ice3ider4.main.Main;
import de.ice3ider4.utils.Strings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

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
    private long maxTime = 72000; //max Time should be 1 hour
    private BukkitTask bukkitTask;

    public TimePlayer(Player player){
        uuid = player.getUniqueId();
        startTime = System.currentTimeMillis();
        bukkitTask = Bukkit.getScheduler().runTaskLater(Main.getInstance(),new Runnable() {
            @Override
            public void run() {
                TimeManager timeManager = Main.getTimeManager();
                TimePlayer timePlayer = timeManager.getTimePlayer(uuid);
                Bukkit.getPlayer(uuid).sendMessage(Strings.PREFIX + ChatColor.DARK_RED + "Your timer has been disabled!");
                timeManager.removeTimePlayer(timePlayer);
            }
        },maxTime);
    }

    public void endTimer(){
        bukkitTask.cancel();
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
        return  String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(runnedTime),
                TimeUnit.MILLISECONDS.toSeconds(runnedTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(runnedTime)));
    }

    public UUID getUuid(){
        return uuid;
    }
}
