package de.ice3ider4.effects;

import de.ice3ider4.main.Main;
import de.ice3ider4.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 11:43
 */
public abstract class EffectSpawner {

    private long ticks;
    private Location from;
    private Location to;
    private int schedulerID;

    /**
     * Class that defines the effects
     * @param ticks     How many ticks between the spawnings
     * @param from      Starting point of the line
     * @param to        Ending point of the line
     */
    public EffectSpawner(long ticks, final Location from, final Location to){
        this.ticks = ticks;
        this.from = LocationUtil.centerLocation(from);
        this.to = LocationUtil.centerLocation(to);

        schedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                playEffect(from, to);
            }
        }, 20L, ticks);
    }

    /**
     * Actually spawns the effects
     * @param from      Starting point of the line
     * @param to        Ending point of the line
     */
    public abstract void playEffect( Location from, Location to);

    public long getTicks(){
        return this.ticks;
    }

    public Location getFrom(){
        return this.from;
    }

    public Location getTo(){
        return this.to;
    }

    /**
     * Disables the repeating task
     */
    public void disable(){
        Bukkit.getScheduler().cancelTask(schedulerID);
    }

}
