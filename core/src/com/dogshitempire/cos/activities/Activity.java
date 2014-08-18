/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.dogshitempire.cos.cats.Cat;

/**
 *
 * @author Super-Aapo
 */
public abstract class Activity extends Actor {
    protected Cat[] users;
    protected ObjectMap<Cat, Float> progresses;
    protected float progressSpeed;
    
    protected Texture tex;
    
    protected Array<String> possibleActions;
    
    public Activity(int maxUsers, float progressSpeed) {
        users = new Cat[maxUsers];
        possibleActions = new Array<String>();
        progresses = new ObjectMap<Cat, Float>();
        this.progressSpeed = progressSpeed;
    }
    
    @Override
    public void act(float deltaSeconds) {
        super.act(deltaSeconds);
        
        setBounds(getX(), getY(), tex.getWidth(), tex.getHeight());
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(tex, getX(), getY());
    }
    
    public boolean reserveSlot(Cat cat) {
        int slot = findFreeSlot();
        if(slot == -1) {
            return false;
        }
        
        users[slot] = cat;
        return true;
    }
    
    public void freeSlot(Cat cat) {
        for(int i = 0; i < users.length; i++) {
            if(users[i] == cat) {
                users[i] = null;
            }
        }
    }
    
    public boolean hasFreeSlot() {
        return findFreeSlot() != -1;
    }
    
    public boolean hasUsers() {
        for(int i = 0; i < users.length; i++) {
            if(users[i] != null) {
                return true;
            }
        }
        return false;
    }
    
    private int findFreeSlot() {
        for(int i = 0; i < users.length; i++) {
            if(users[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public boolean isBeingUsedBy(Cat cat) {
        for(int i = 0; i < users.length; i++) {
            if(users[i] == cat) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @param cat cat that's using the activity
     * @return true if the activity finished
     */
    public boolean addProgress(Cat cat) {
        if(isBeingUsedBy(cat)) {
            if(progresses.containsKey(cat)) {
                Float f = progresses.get(cat);
                f += progressSpeed;
                
                //Gdx.app.log("PROGRRR", "" + f);
                
                if(f >= 1f) {
                    progressDone(cat);
                    progresses.remove(cat);
                    freeSlot(cat);
                    return true;
                }
                else {
                    progresses.put(cat, f);
                }
            }
            else {
                progresses.put(cat, progressSpeed);
            }
        }
        else {
            // A silly cat is queuing? We'll try to find a slot for her.
            reserveSlot(cat);
            return false;
        }
        
        return false;
    }
    public float getProgress(Cat cat) {
        return progresses.get(cat);
    }
    
    public void addPossibleAction(String action) {
        possibleActions.add(action);
    }
    public void removePossibleAction(String action) {
        possibleActions.removeValue(action, false);
    }
    public void removeAllPossibleActions() {
        possibleActions.clear();
    }
    public Array<String> getPossibleActions() {
        return possibleActions;
    }
    
    public abstract void progressDone(Cat cat);
    public abstract boolean satisfiesNeed(int need);
    public abstract void receiveAction(String action);
    public abstract String getInfo();
}
