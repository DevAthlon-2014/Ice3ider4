package de.ice3ider4.listeners;

import de.ice3ider4.main.Main;
import de.ice3ider4.utils.Effects;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 10:14
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent event){
        Bukkit.getScheduler().runTaskLater(Main.getInstance(),new Runnable() {
            @Override
            public void run() {
                Player player = event.getPlayer();
                Location location = player.getLocation();

                Effects.CLOUD.playEffect(location,2,2,2,2,40);
            }
        },4 * 20L);
    }
}
