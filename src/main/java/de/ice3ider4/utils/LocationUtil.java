package de.ice3ider4.utils;

import org.bukkit.Location;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 12:32
 */
public class LocationUtil {
    public static Location centerLocation(Location loc){
        return loc.add(0.5,0.5,0.5);
    }
}
