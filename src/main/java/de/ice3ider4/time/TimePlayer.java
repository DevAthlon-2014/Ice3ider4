package de.ice3ider4.time;

import de.ice3ider4.main.Main;
import de.ice3ider4.utils.Strings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.*;

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
    private Scoreboard scoreboard;
    private BukkitTask bukkitTask;
    private int taskID;

    private long startTime;
    private long endTime = -1;
    private long maxTime = 72000; //max Time should be 1 hour

    /**
     * Constructs a new TimePlayer from a player object
     *
     * @param player    The player that should be used for this TimePlayer object
     */
    public TimePlayer(Player player){
        uuid = player.getUniqueId();
        startTime = System.currentTimeMillis();

        //Sets the scoreboard for the player
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.RED + "Timer");
        Score score = objective.getScore(ChatColor.GREEN + "Time:");
        score.setScore(getAlreadyRunnedTime());
        player.setScoreboard(scoreboard);

        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(),new Runnable() {
            @Override
            public void run() {
                updateScoreboard();
            }
        }, 0L,20L);


        //Disables the timer if the max time has been reached
        bukkitTask = Bukkit.getScheduler().runTaskLater(Main.getInstance(),new Runnable() {
            @Override
            public void run() {
                TimeManager timeManager = Main.getTimeManager();
                TimePlayer timePlayer = timeManager.getTimePlayer(uuid);
                Bukkit.getPlayer(uuid).sendMessage(Strings.PREFIX + Strings.TIMER_DISABLED);
                timeManager.removeTimePlayer(timePlayer);
            }
        },maxTime);
    }

    /**
     * Ends the timer of this TimePlayer
     */
    public void endTimer(){
        bukkitTask.cancel();
        Bukkit.getScheduler().cancelTask(taskID);
        Bukkit.getPlayer(uuid).setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    /**
     * Sets the ending time to calculate the runned time
     *
     * @param endTime   Time when the player reached the finish line
     */
    public void setEndTime(long endTime){
        this.endTime = endTime;
    }

    /**
     * Gets the time which the player already runned
     *
     * @return      The time which the player already runned
     */
    private int getAlreadyRunnedTime(){
        long runnedTime = System.currentTimeMillis() - startTime;
        return (int) TimeUnit.MILLISECONDS.toSeconds(runnedTime);
    }

    /**
     * Updates the scoreboard for the player
     */
    private void updateScoreboard(){
        Objective objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);
        Score score = objective.getScore(ChatColor.GREEN + "Time:");
        score.setScore(getAlreadyRunnedTime());
        Bukkit.getPlayer(uuid).setScoreboard(scoreboard);
    }

    /**
     * Gets the final time which the player runned
     *
     * @return  The runned time
     */
    public long getRunnedTime(){
        if (endTime == -1) {
            return 0;
        }
        return endTime - startTime;
    }

    /**
     * Gets the final time converted to a String
     *
     * @return  The runned time as a String
     */
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
