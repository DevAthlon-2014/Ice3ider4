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
    private Effects effect = Effects.FLAME;

    public LineEffect(Location from, Location to) {
        super(15L, from,to);
    }

    @Override
    public void playEffect(Location from, Location to){
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
        boolean check = false;
        Location location = player.getLocation();
      // n x >= min.x && x <= max.x && y >= min.y && y <= max.y && z >= min.z && z <= max.z;
    }

}
