/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.cats.CatStats;

/**
 *
 * @author Super-Aapo
 */
public class Bowl extends Activity {
    private enum Content { WATER, FOOD };
    private Content currentContent;
    private int amountOfContent;
    private int reservedContent;
    
    private String foodFill = "Fill with food";
    private String waterFill = "Fill with water";
    private String emptyContents = "Empty contents";
    
    private Texture emptyTex;
    private Texture foodTex;
    private Texture waterTex;
    
    public Bowl() {
        super(2, 0.2f);
        
        currentContent = Content.FOOD;
        amountOfContent = 0;
        reservedContent = 0;
        
        addPossibleAction(foodFill);
        addPossibleAction(waterFill);
        
        emptyTex = new Texture(Gdx.files.internal("bowl.png"));
        foodTex = new Texture(Gdx.files.internal("bowl_food.png"));
        waterTex = new Texture(Gdx.files.internal("bowl_water.png"));
        
        tex = emptyTex;
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
    public void progressDone(Cat cat) {
        //Gdx.app.log("BOWL", "DONE");
        amountOfContent--;
        reservedContent--;
        
        if(amountOfContent == 0) {
            tex = emptyTex;
        }
        
        if(currentContent == Content.FOOD) {
            cat.getStats().setHunger(100f);
        }
        else {
            cat.getStats().setHealth(cat.getStats().getHealth() + 10);
        }
    }
    
    @Override
    public boolean satisfiesNeed(int need) {
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
                tex = foodTex;
                currentContent = Content.FOOD;
            }
        }
        else if(action.equals(waterFill)) {
            if(currentContent != Content.FOOD || amountOfContent == 0) {
                removeAllPossibleActions();
                addPossibleAction(waterFill);
                addPossibleAction(emptyContents);
                amountOfContent = 5;
                tex = waterTex;
                currentContent = Content.WATER;
            }
        }
        else if(action.equals(emptyContents)) {
            if(amountOfContent > 0) {
                amountOfContent = 0;
                removeAllPossibleActions();
                addPossibleAction(foodFill);
                addPossibleAction(waterFill);
                tex = emptyTex;
            }
        }
    }
    
    @Override
    public String getInfo() {
        if(amountOfContent > 0) {
            return "Filled with " + getContentAsString() + ", has " + amountOfContent + " portions left";
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
