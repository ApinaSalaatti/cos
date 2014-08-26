package com.dogshitempire.cos.cats.buffs;

import com.badlogic.gdx.Gdx;
import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.cats.CatStats;

/**
 *
 * @author Merioksan Mikko
 */
public abstract class CatBuff {
    public enum BuffType { OVER_TIME, INSTANT, TEMPORARY, OTHER};
    protected BuffType type;
    public BuffType getType() {
        return type;
    }
    
    private float length;
    private float timer;
    
    protected Cat cat;
    
    public CatBuff() {
        this(BuffType.OTHER, 0);
    }
    public CatBuff(BuffType type) {
        this(type, 0);
    }
    public CatBuff(BuffType type, float length) {
        this.type = type;
        
        this.length = length;
        this.timer = 0;
    }
    
    public void onAdd(Cat cat) {
        this.cat = cat;
    }
    
    public void onRemove() {

    }
    
    public boolean done() {
        return timer >= length;
    }
    
    public void update(float deltaSeconds) {
        timer += deltaSeconds;
    }
}
