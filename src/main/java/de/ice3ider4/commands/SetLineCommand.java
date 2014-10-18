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
 * Time: 17:31
 */
public class SetLineCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(!(cs instanceof Player)){
            cs.sendMessage(Strings.PREFIX + Strings.NO_PLAYER);
        }
        else{
            Player player = (Player)cs;

            if(args.length == 2){
                saveLocation(player.getLocation().clone(),args[0],args[1]);
                player.sendMessage(Strings.PREFIX + Strings.LINE_SET);

            }
            else{
                player.sendMessage(Strings.PREFIX + Strings.SETLINE_ARGS);
            }
        }

        return true;
    }

    /**
     * Loads the location from the file
     *
     * @param typ   The type of the location
     * @param name  The name of the location
     * @return      The loaded locaiton
     */
    public void saveLocation(Location loc, String typ, String name){
        Main main = Main.getInstance();
        FileConfiguration config = main.getConfig();
        config.set(typ + "." + name + ".x",loc.getX());
        config.set(typ + "." + name +  ".y",loc.getY());
        config.set(typ + "." + name +  ".z",loc.getZ());
        config.set(typ + "." + name +  ".world",loc.getWorld().getName());
        main.saveConfig();
    }
}
