package de.ice3ider4.utils;

import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 11:52
 */
public class VectorUtil {

    public static Vector createVector(Location from, Location to){
        return to.toVector().subtract(from.toVector());
    }
}
