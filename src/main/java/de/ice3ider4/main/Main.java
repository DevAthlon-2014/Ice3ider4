package de.ice3ider4.main;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import de.ice3ider4.listeners.PlayerListener;
import org.bukkit.Bukkit;
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

    @Override
    public void onEnable(){
        instance = this;
        protocolManager = ProtocolLibrary.getProtocolManager();
        System.out.println("Devathlon Plugin has been enabled");

        listeners.add(new PlayerListener());

        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable(){
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
     * @return the instance of the Main class
     */
    public static Main getInstance(){
        return instance;
    }

    public static ProtocolManager getProtocolManager(){
        return protocolManager;
    }

}