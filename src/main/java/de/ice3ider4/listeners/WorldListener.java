package de.ice3ider4.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 11:27
 */
public class WorldListener implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event){
        if(event.toWeatherState()){
            event.setCancelled(true);
        }
    }
}
