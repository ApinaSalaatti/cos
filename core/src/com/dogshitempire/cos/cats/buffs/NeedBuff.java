package com.dogshitempire.cos.cats.buffs;

import com.badlogic.gdx.Gdx;
import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.cats.CatStats;

/**
 *
 * @author Merioksan Mikko
 */
public class NeedBuff extends CatBuff {
    private final int affectedNeed;
    private final float amount;
    
    public NeedBuff(BuffType type, float length, int need, float amount) {
        super(type, length);
        
        affectedNeed = need;
        this.amount = amount;
    }
    
    @Override
    public void onAdd(Cat cat) {
        super.onAdd(cat);
        if(type == BuffType.INSTANT || type == BuffType.TEMPORARY) {
            takeEffect(amount);
        }
    }
    
    @Override
    public void onRemove() {
        if(type == BuffType.TEMPORARY) {
            takeEffect(-amount);
        }
    }
    
    @Override
    public void update(float deltaSeconds) {
        super.update(deltaSeconds);
        
        if(type == BuffType.OVER_TIME) {
            takeEffect(amount * deltaSeconds);
        }
    }
    
    private void takeEffect(float a) {
        switch(affectedNeed) {
            case CatStats.NEED_CLEANLINESS:
                cat.getStats().setCleanliness(cat.getStats().getCleanliness() + a);
                break;
            case CatStats.NEED_HAPPINESS:
                cat.getStats().setHappiness(cat.getStats().getHappiness() + a);
                break;
            case CatStats.NEED_HEALTH:
                cat.getStats().setHealth(cat.getStats().getHealth() + a);
                break;
            case CatStats.NEED_HUNGER:
                cat.getStats().setHunger(cat.getStats().getHunger() + a);
                break;
        }
    }
}
