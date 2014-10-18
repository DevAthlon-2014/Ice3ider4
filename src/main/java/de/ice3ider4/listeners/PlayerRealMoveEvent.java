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
    private HandlerList handlers = new HandlerList();
    private Location from;
    private Location to;

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
}
