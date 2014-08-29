package com.dogshitempire.cos.items.activities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.cats.CatStats;
import com.dogshitempire.cos.cats.Interest;
import com.dogshitempire.cos.cats.buffs.CatBuff.BuffType;
import com.dogshitempire.cos.cats.buffs.NeedBuff;

/**
 *
 * @author Merioksan Mikko
 */
public class Toy extends Activity {
    private final Array<Interest> satisfiedInterests;

    public Toy(int width, int height, Activity.Place place, Texture tex, Interest ... interestsSatisfied) {
        super(width, height, place, 1, 0.1f);
        
        satisfiedInterests = new Array<Interest>();
        satisfiedInterests.addAll(interestsSatisfied);
        
        this.tex = tex;
        
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                getTile(x, y).take();
            }
        }
    }
    
    @Override
    public boolean satisfiesNeed(int need, Cat cat) {
        if(need == CatStats.NEED_HAPPINESS) {
            for(int i = 0; i < satisfiedInterests.size; i++) {
                if(cat.getStats().getInterest(satisfiedInterests.get(i).getName()) != null) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    @Override
    public void receiveAction(String action) {
        
    }
    
    @Override
    public void progressDone(Cat cat) {
        cat.getStats().setHappiness(100f);
        cat.addBuff(new NeedBuff(BuffType.OVER_TIME, 10f, CatStats.NEED_HAPPINESS, 1f));
    }
    
    @Override
    public String getInfo() {
        String s = "Such a wonderful toy. (";
        
        for(int i = 0; i < satisfiedInterests.size; i++) {
            s += satisfiedInterests.get(i).getName();
            if(i < satisfiedInterests.size-1) {
                s += " ";
            }
        }
        
        return s;
    }
}
