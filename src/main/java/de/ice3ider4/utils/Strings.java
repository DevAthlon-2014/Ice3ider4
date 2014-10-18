package de.ice3ider4.utils;

import org.bukkit.ChatColor;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 11:10
 */
public class Strings {
    public static final String PREFIX = ChatColor.RED + "[" + ChatColor.GRAY + "Devathlon" + ChatColor.RED + "] " + ChatColor.RESET;
    public static final String TIMER_DISABLED = ChatColor.DARK_RED + "Your timer has been disabled!";
    public static final String PLAYER_JOIN = ChatColor.GOLD + "%player% joined the server!";
    public static final String PLAYER_LEAVE = ChatColor.GOLD + "%player% left the server!";
    public static final String ALREADY_RUNNING = ChatColor.DARK_RED + "You are already running!";
    public static final String TIMER_STARTED = ChatColor.GREEN + "Timer started!";
    public static final String TIMER_ENDED = ChatColor.GOLD + "Your time was: %time%.";
    public static final String NOT_STARTED = ChatColor.DARK_RED + "You haven't started your timer!";
    public static final String NO_PLAYER = ChatColor.DARK_RED + "You must be a player to use this command.";
    public static final String SPAWN_SET = ChatColor.GREEN + "You have set the spawnpint.";
    public static final String LINE_SET = ChatColor.GREEN + "You have set the linepoint. " + ChatColor.DARK_RED + "You have to reload the server!";
    public static final String SETLINE_ARGS = ChatColor.DARK_RED + "Please use /setline <start/end> <from/to>.";

}
