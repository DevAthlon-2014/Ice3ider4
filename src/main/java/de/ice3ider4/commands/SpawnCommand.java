package de.ice3ider4.commands;

import de.ice3ider4.main.Main;
import de.ice3ider4.utils.Strings;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 17:01
 */
public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(!(cs instanceof Player)){
            cs.sendMessage(Strings.PREFIX + Strings.NO_PLAYER);
        }
        else{
            Player player = (Player)cs;
            saveLocation(player.getLocation().clone(),"spawn");
            player.sendMessage(Strings.PREFIX + Strings.SPAWN_SET);
        }

        return true;
    }

    /**
     * Saves the location to the config file
     *
     * @param loc   The location that should be saved
     * @param typ   The name under which the location should be saved
     */
    private void saveLocation(Location loc, String typ){
        Main main = Main.getInstance();
        FileConfiguration config = main.getConfig();
        config.set(typ + ".x",loc.getX());
        config.set(typ + ".y",loc.getY());
        config.set(typ + ".z",loc.getZ());
        config.set(typ + ".yaw",loc.getYaw());
        config.set(typ + ".pitch",loc.getPitch());
        config.set(typ + ".world",loc.getWorld().getName());
        main.saveConfig();
    }

}
