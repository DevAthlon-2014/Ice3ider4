package de.ice3ider4.effects;

import de.ice3ider4.main.Main;
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
    private Location location;
    private int schedulerID;

    public EffectSpawner(long ticks, final Location location){
        this.ticks = ticks;
        this.location = location.clone().add(0.5,0,0.5);

        schedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                playEffect(location);
            }
        },20L,ticks);
    }

    public abstract void playEffect(Location loc);

    public long getTicks(){
        return this.ticks;
    }

    public Location getLocation(){
        return this.location;
    }

    public void disable(){
        Bukkit.getScheduler().cancelTask(schedulerID);
    }

}
