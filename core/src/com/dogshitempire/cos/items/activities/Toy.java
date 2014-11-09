package com.dogshitempire.cos.items.activities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.cats.CatStats;
import com.dogshitempire.cos.cats.Interest;
import com.dogshitempire.cos.cats.buffs.CatBuff.BuffType;
import com.dogshitempire.cos.cats.buffs.NeedBuff;
import com.dogshitempire.cos.items.Item.ItemCategory;
import com.dogshitempire.cos.items.Item.Place;

/**
 *
 * @author Merioksan Mikko
 */
public class Toy extends ActivityModifier {
    private final Array<Interest> satisfiedInterests;

    private int width;
    private int height;
    private Place place;
    
    private float price;
    
    private Texture tex;
    
    public Toy(int w, int h, Place place, int maxUsers, float price, Texture tex, Interest ... interestsSatisfied) {
        super(maxUsers, 10f);
        
        satisfiedInterests = new Array<Interest>();
        satisfiedInterests.addAll(interestsSatisfied);
        
        width = w;
        height = h;
        this.place = place;
        
        this.price = price;
        
        this.tex = tex;
    }
    
    @Override
    public void onAttach(Activity a) {
        a.init(width, height, place, price, "toy", ItemCategory.ACTIVITY);
        
        a.setTexture(tex);
        
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                a.getTile(x, y).take();
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
    public void activate(Cat cat) {
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
        
        s += ")";
        return s;
    }
}
