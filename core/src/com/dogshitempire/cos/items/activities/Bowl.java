/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.items.activities;

import com.badlogic.gdx.graphics.Texture;
import com.dogshitempire.cos.GameApplication;
import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.cats.buffs.CatBuff.BuffType;
import com.dogshitempire.cos.cats.CatStats;
import com.dogshitempire.cos.cats.buffs.NeedBuff;
import com.dogshitempire.cos.items.Item.ItemCategory;

/**
 *
 * @author Super-Aapo
 */
public class Bowl extends ActivityModifier {
    private enum Content { WATER, FOOD };
    private Content currentContent;
    private int amountOfContent;
    private int reservedContent;
    
    private final String foodFill = "Fill with food";
    private final String waterFill = "Fill with water";
    private final String emptyContents = "Empty contents";
    
    private Texture emptyTex;
    private Texture foodTex;
    private Texture waterTex;
    
    public Bowl() {
        super(2, 5f);
        
        currentContent = Content.FOOD;
        amountOfContent = 0;
        reservedContent = 0;
        
        addPossibleAction(foodFill);
        addPossibleAction(waterFill);
        
        emptyTex = GameApplication.getAssetManager().get("bowl.png", Texture.class);
        foodTex = GameApplication.getAssetManager().get("bowl_food.png", Texture.class);
        waterTex = GameApplication.getAssetManager().get("bowl_water.png", Texture.class);
    }
    
    @Override
    public void onAttach(Activity a) {
        a.init(2, 2, Activity.Place.FLOOR, 5, "bowl", ItemCategory.ACTIVITY);
        
        a.setTexture(emptyTex);
        
        for(int y = 0; y < 2; y++) {
            for(int x = 0; x < 2; x++) {
                a.getTile(x, y).take();
            }
        }
    }
    
    @Override
    public boolean reserveSlot(Cat cat) {
        if(freeContentLeft() > 0) {
            if(super.reserveSlot(cat)) {
                reservedContent += 1;
                return true;
            }
        }
        return false;
    }
    
    public int freeContentLeft() {
        return amountOfContent - reservedContent;
    }
    
    @Override
    public void activate(Cat cat) {
        if(!isBeingUsedBy(cat)) {
            return;
        }
        
        amountOfContent--;
        reservedContent--;
        freeSlot(cat);
        
        if(amountOfContent == 0) {
            removeAllPossibleActions();
            addPossibleAction(foodFill);
            addPossibleAction(waterFill);
            activity.setTexture(emptyTex);
        }
        
        if(currentContent == Content.FOOD) {
            cat.getStats().setHunger(100f);
            cat.addBuff(new NeedBuff(BuffType.OVER_TIME, 10f, CatStats.NEED_HUNGER, 1f));
        }
        else {
            cat.getStats().setHealth(cat.getStats().getHealth() + 10);
        }
    }
    
    @Override
    public boolean satisfiesNeed(int need, Cat cat) {
        if(freeContentLeft() > 0) {
            if(currentContent == Content.FOOD && need == CatStats.NEED_HUNGER) {
                return true;
            }
            else if(currentContent == Content.WATER && need == CatStats.NEED_HEALTH) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void receiveAction(String action) {
        if(action.equals(foodFill)) {
            if(currentContent != Content.WATER || amountOfContent == 0) {
                removeAllPossibleActions();
                addPossibleAction(foodFill);
                addPossibleAction(emptyContents);
                amountOfContent = 5;
                activity.setTexture(foodTex);
                currentContent = Content.FOOD;
            }
        }
        else if(action.equals(waterFill)) {
            if(currentContent != Content.FOOD || amountOfContent == 0) {
                removeAllPossibleActions();
                addPossibleAction(waterFill);
                addPossibleAction(emptyContents);
                amountOfContent = 5;
                activity.setTexture(waterTex);
                currentContent = Content.WATER;
            }
        }
        else if(action.equals(emptyContents)) {
            amountOfContent = 0;
            removeAllPossibleActions();
            addPossibleAction(foodFill);
            addPossibleAction(waterFill);
            activity.setTexture(emptyTex);
        }
    }
    
    @Override
    public String getInfo() {
        if(amountOfContent > 0) {
            return "Filled with " + getContentAsString() + ", has " + amountOfContent + " portions left (" + reservedContent + " reserved)";
        }
        else {
            return "Empty";
        }
    }
    
    private String getContentAsString() {
        if(currentContent == Content.FOOD) {
            return "food";
        }
        else {
            return "water";
        }
    }
}
