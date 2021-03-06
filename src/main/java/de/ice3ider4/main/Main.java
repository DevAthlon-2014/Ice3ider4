package de.ice3ider4.main;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import de.ice3ider4.commands.SetLineCommand;
import de.ice3ider4.commands.SpawnCommand;
import de.ice3ider4.effects.EffectManager;
import de.ice3ider4.effects.Effects;
import de.ice3ider4.effects.LineEffect;
import de.ice3ider4.effects.LineTyp;
import de.ice3ider4.listeners.EventCaller;
import de.ice3ider4.listeners.PlayerListener;
import de.ice3ider4.listeners.WorldListener;
import de.ice3ider4.time.TimeManager;
import de.ice3ider4.time.TimePlayer;
import de.ice3ider4.utils.LogHelper;
import org.bukkit.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.HashSet;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 10:02
 */
public class Main extends JavaPlugin {

    private HashMap<String,CommandExecutor> commands = new HashMap<String,CommandExecutor>();
    private HashSet<Listener> listeners = new HashSet<Listener>();
    private static Main instance;
    private static ProtocolManager protocolManager;
    private static LogHelper logHelper;
    private static EffectManager effectManager;
    private static TimeManager timeManager;

    @Override
    public void onEnable(){
        System.out.println("Devathlon Plugin has been enabled");

        instance = this;
        effectManager = new EffectManager();
        protocolManager = ProtocolLibrary.getProtocolManager();
        logHelper = new LogHelper(getLogger());
        timeManager = new TimeManager();

        listeners.add(new EventCaller());
        listeners.add(new PlayerListener(timeManager));
        listeners.add(new WorldListener());

        commands.put("setspawn",new SpawnCommand());
        commands.put("setline", new SetLineCommand());

        registerCommands();
        registerListeners();
        prepareWorlds();
        loadConfiguration();
    }

    @Override
    public void onDisable(){

        for(TimePlayer timePlayer : timeManager.getTimePlayers()){
            timeManager.removeTimePlayer(timePlayer);
        }

        System.out.println("Devathlon Plugin has been disabled");
    }

    /**
     * Registers all the commands using a hashmap
     */
    private void registerCommands(){
        for(String cmd : commands.keySet()){
            getCommand(cmd).setExecutor(commands.get(cmd));
        }
    }

    /**
     * Registers all the listeners using a hashset
     */
    private void registerListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        for(Listener listener : listeners){
            pluginManager.registerEvents(listener, this);
        }
    }

    /**
     * Prepares the worlds to be clean
     */
    private void prepareWorlds(){
        for(World w : Bukkit.getWorlds()){
            w.setDifficulty(Difficulty.PEACEFUL);
            w.setTime(100L);
            w.setGameRuleValue("doDaylightCycle","false");
        }
    }

    /**
     * Converts the locations to line effects
     */
    private void loadConfiguration(){
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        Location fromStart = loadLocation("start","from");
        Location toStart = loadLocation("start","to");
        LineEffect startLine = new LineEffect(LineTyp.STARTLINE,Effects.FLAME,fromStart,toStart);

        Location fromEnd = loadLocation("end","from");
        Location toEnd = loadLocation("end","to");
        LineEffect endLine = new LineEffect(LineTyp.ENDLINE,Effects.CLOUD,fromEnd,toEnd);

        Location fromLaser1 = loadLocation("laser1","from");
        Location toLaser1 = loadLocation("laser1","to");
        LineEffect laser1Line = new LineEffect(LineTyp.LASERLINE,Effects.RED_DUST,fromLaser1,toLaser1);

        Location fromLaser2 = loadLocation("laser2","from");
        Location toLaser2 = loadLocation("laser2","to");
        LineEffect laser2Line = new LineEffect(LineTyp.LASERLINE,Effects.RED_DUST,fromLaser2,toLaser2);

        Location fromLaser3 = loadLocation("laser3","from");
        Location toLaser3 = loadLocation("laser3","to");
        LineEffect laser3Line = new LineEffect(LineTyp.LASERLINE,Effects.RED_DUST,fromLaser3,toLaser3);

        effectManager.addLineEffect(startLine);
        effectManager.addLineEffect(endLine);
        effectManager.addLineEffect(laser1Line);
        effectManager.addLineEffect(laser2Line);
        effectManager.addLineEffect(laser3Line);
    }

    /**
     * Loads the location from the file
     *
     * @param typ   The type of the location
     * @param name  The name of the location
     * @return      The loaded locaiton
     */
    public Location loadLocation(String typ, String name){
        double x = getConfig().getDouble(typ + "." + name + ".x");
        double y = getConfig().getDouble(typ + "." + name + ".y");
        double z = getConfig().getDouble(typ + "." + name + ".z");
        World w =  Bukkit.getWorld(getConfig().getString(typ + "." + name + ".world"));
        return new Location(w,x,y,z);
    }

    /**
     * Loads the location from the file
     *
     * @param name  The name of the location
     * @return      The loaded locaiton
     */
    public Location loadLocation(String name){
        double x = getConfig().getDouble(name + ".x");
        double y = getConfig().getDouble(name + ".y");
        double z = getConfig().getDouble(name + ".z");
        World w =  Bukkit.getWorld(getConfig().getString(name + ".world"));
        float yaw = (float) getConfig().getDouble(name + ".yaw");
        float pitch = (float) getConfig().getDouble(name + ".pitch");
        return new Location(w,x,y,z,yaw,pitch);
    }

    /**
     * Singleton instance
     *
     * @return the instance of the Main class
     */
    public static Main getInstance(){
        return instance;
    }

    /**
     * Gets the protocolmanager
     * @return the protocolmanager (ProtocolLib)
     */
    public static ProtocolManager getProtocolManager(){
        return protocolManager;
    }

    /**
     * Gets the log helper
     * @return the loghelper
     */
    public static LogHelper getLogHelper(){
        return logHelper;
    }

    /**
     * Gets the EffectManager
     * @return the effectManager
     */
    public static EffectManager getEffectManager(){ return effectManager;}

    /**
     * Gets the TimeManager
     * @return the timeManager
     */
    public static TimeManager getTimeManager(){return  timeManager;}
}
