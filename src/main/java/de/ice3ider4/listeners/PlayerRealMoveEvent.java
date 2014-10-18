package de.ice3ider4.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 13:20
 */
public class PlayerRealMoveEvent extends PlayerEvent implements Cancellable {

    private boolean cancelled;
    private static HandlerList handlers = new HandlerList();
    private Location from;
    private Location to;

    /**
     * This event gets called if the player really moves on the X or Z axis
     *
     * @param player    The Player that moved
     * @param from      Location from where the player moved
     * @param to        Location to which the player moved
     */
    public PlayerRealMoveEvent(Player player, Location from, Location to) {
        super(player);

        this.from = from;
        this.to = to;
    }

    public Location getTo() {
        return to;
    }

    public Location getFrom() {
        return from;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}
