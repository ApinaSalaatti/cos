package com.dogshitempire.cos.activities;

import com.dogshitempire.cos.GameApplication;
import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.cats.CatStats;
import com.dogshitempire.cos.cats.buffs.CatBuff.BuffType;
import com.dogshitempire.cos.cats.buffs.NeedBuff;

/**
 *
 * @author Merioksan Mikko
 */
public class Toy extends Activity {

    public Toy() {
        super(1, 0.1f);
        
        tex = GameApplication.getAssetManager().get("toy.png");
    }
    
    @Override
    public boolean satisfiesNeed(int need) {
        if(need == CatStats.NEED_HAPPINESS) {
            return true;
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
        return "Such a wonderful toy.";
    }
}
