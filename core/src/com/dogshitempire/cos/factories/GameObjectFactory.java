/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.GameApplication;
import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.cats.Interest;
import com.dogshitempire.cos.cats.Skill;
import com.dogshitempire.cos.items.Item;
import com.dogshitempire.cos.items.activities.Activity;
import com.dogshitempire.cos.items.activities.Bowl;
import com.dogshitempire.cos.items.activities.Toy;
import com.dogshitempire.cos.items.furniture.Chair;
import com.dogshitempire.cos.lady.Lady;

/**
 *
 * @author Super-Aapo
 */
public class GameObjectFactory {
    private int latestID = 0;
    
    private Array<String> catNames;
    
    public GameObjectFactory() {
        catNames = new Array<String>();
        catNames.addAll("Whiskers", "Blob", "Stripes", "Elizabeth", "Molly", "Gennaro", "Robert", "Spanker", "Flute");
    }
    
    public Lady createLady() {
        return new Lady(latestID++);
    }
    
    public Activity createActivity(String type) {
        Activity a = new Activity(latestID++);
        
        if(type.equals("bowl")) {
            a.setModifier(new Bowl());
        }
        else if(type.equals("toy")) {
            a.setModifier(new Toy(
                        2, 2,
                        Activity.Place.FLOOR, 1,
                        10,
                        GameApplication.getAssetManager().get("toy.png", Texture.class),
                        new Interest("Butts")
            ));
        }
        
        return a;
    }
    
    public Item createFurniture(String type) {
        if(type.equals("chair")) {
            return new Chair(latestID++);
        }
        
        return null;
    }
    
    public Cat createCat() {
        Cat cat = new Cat(latestID++);
        
        // TODO change these so they are generated based on some kind of HISTORY of the cat ^____^
        cat.setGender((int)(Math.random() * 2) + 1);
        cat.getStats().setCleanliness(50f);
        cat.getStats().setHappiness(50f);
        cat.getStats().setHealth(50f);
        cat.getStats().setHunger(50f);
        
        cat.getStats().addInterest(new Interest("Butts"));
        cat.getStats().addInterest(new Interest("Catnip"));
        
        cat.getStats().addSkill(new Skill("Jumping"));
        
        cat.setCatName(generateName(cat));
        
        return cat;
    }
    
    /**
     * A very wild cat name creation algorithm based on all sorts of cool calculations.
     * @param cat
     * @return 
     */
    private String generateName(Cat cat) {
        int r = (int)(Math.random() * catNames.size);
        
        String name = catNames.get(r);
        double rand = Math.random();
        if(cat.getGender() == Cat.GENDER_FEMALE) {
            if(rand > 0.5) {
                name = "Mrs. " + name;
            }
        }
        else {
            if(rand > 0.5) {
                name = "Mr. " + name;
            }
        }
        
        return name;
    }
}
