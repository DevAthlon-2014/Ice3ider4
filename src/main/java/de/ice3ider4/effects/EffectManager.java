package de.ice3ider4.effects;

import java.util.HashSet;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 13:25
 */
public class EffectManager {
    HashSet<LineEffect> lineEffects = new HashSet<LineEffect>();

    public EffectManager(){

    }

    /**
     * Adds a line effect to the effect manager
     * @param effect    Effect that should be added
     */
    public void addLineEffect(LineEffect effect){
        lineEffects.add(effect);
    }

    /**
     * Gets all the currently saved effects
     * @return          All saved effects
     */
    public HashSet<LineEffect> getLineEffects(){
        return lineEffects;
    }

}
