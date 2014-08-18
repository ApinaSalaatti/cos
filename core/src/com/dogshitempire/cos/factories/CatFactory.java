/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.factories;

import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.cats.Interest;
import com.dogshitempire.cos.cats.Skill;

/**
 *
 * @author Super-Aapo
 */
public class CatFactory {
    private int latestID = 0;
    
    private Array<String> catNames;
    
    public CatFactory() {
        catNames = new Array<String>();
        catNames.addAll("Mr. Whiskers", "Blob", "Stripes", "Molly", "Gennaro");
    }
    
    public Cat createCat() {
        Cat cat = new Cat(latestID++);
        
        // TODO change these so they are generated based on some kind of HISTORY of the cat ^____^
        cat.setCatName(generateName());
        cat.setGender((int)(Math.random() * 2) + 1);
        cat.getStats().setCleanliness(50f);
        cat.getStats().setHappiness(50f);
        cat.getStats().setHealth(50f);
        cat.getStats().setHunger(50f);
        
        cat.getStats().addInterest(new Interest("Butts"));
        cat.getStats().addInterest(new Interest("Catnip"));
        
        cat.getStats().addSkill(new Skill("Jumping"));
        
        return cat;
    }
    
    public String generateName() {
        int r = (int)(Math.random() * catNames.size);
        return catNames.get(r);
    }
}
