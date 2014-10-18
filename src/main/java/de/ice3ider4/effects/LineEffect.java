package de.ice3ider4.effects;

import de.ice3ider4.utils.VectorUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 11:50
 */
public class LineEffect extends EffectSpawner {

    private int particles = 50;
    private Vector link;
    private float lenght;
    private Effects effect;
    private Location from;
    private Location to;

    public LineEffect(Effects effect, Location from, Location to) {
        super(effect, 15L, from,to);
    }

    @Override
    public void playEffect(Effects effect, Location from, Location to){
        this.from = from;
        this.to = to;
        this.effect = effect;
        link = VectorUtil.createVector(from, to);
        lenght = (float) link.length();
        link.normalize();

        for(int i = 0;i<particles;i++){
            float ratio = (float) i * lenght / particles;
            Vector v = link.clone().multiply(ratio);
            from.add(v);
            effect.playEffect(from, 0.1F, 0, 0.1F, 0, 1);
            from.subtract(v);
        }

    }

    public boolean checkPlayer(Player player){
        Location location = player.getLocation();

        if(to == null || from == null){
            return false;
        }

        if(to.getWorld().getName() == from.getWorld().getName()){
            if(location.getWorld().getName() == to.getWorld().getName()){
                if((location.getBlockX() >= from.getBlockX() && location.getBlockX() <= to.getBlockX()) || (location.getBlockX() <= from.getBlockX() && location.getBlockX() >= to.getBlockX())){
                    if((location.getBlockZ() >= from.getBlockZ() && location.getBlockZ() <= to.getBlockZ()) || (location.getBlockZ() <= from.getBlockZ() && location.getBlockZ() >= to.getBlockZ())){
                        if((location.getBlockY() > to.getBlockY() && location.getBlockY() > from.getBlockY()) && (location.getBlockY() < (to.getBlockY() + 3) && location.getBlockY() < (from.getBlockY() + 3)))
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public Effects getEffect(){
        return this.effect;
    }

}
