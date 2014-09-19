package com.dogshitempire.cos.research;

import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Merioksan Mikko
 */
public class ResearchItem {
    private String name;
    private int cost;
    public boolean available;
    private Array<ResearchEffect> effects;
    
    public ResearchItem() {
        effects = new Array<ResearchEffect>();
        available = false;
    }
    
    public void setName(String n) {
        name = n;
    }
    public String getName() {
        return name;
    }
    
    public void setCost(int c) {
        cost = c;
    }
    public int getCost() {
        return cost;
    }
    
    public boolean isAvailable() {
        return available;
    }
    public void makeAvailable() {
        available = true;
    }
    
    public void addEffect(ResearchEffect effect) {
        effects.add(effect);
    }
    public Array<ResearchEffect> getEffects() {
        return effects;
    }
    
    public void research() {
        for(ResearchEffect re : effects) {
            re.execute();
        }
    }
}
