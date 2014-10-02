package com.dogshitempire.cos.items.activities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.cats.Cat;

/**
 *
 * @author Merioksan Mikko
 */
public abstract class ActivityModifier {
    protected Activity activity;
    
    protected Cat[] users;
    
    protected Array<String> possibleActions;
    
    private float useTime;
    public float getUseTime() {
        return useTime;
    }
    
    public ActivityModifier(int maxUsers, float useTime) {
        users = new Cat[maxUsers];
        possibleActions = new Array<String>();
        this.useTime = useTime;
    }
    
    public void beingAttached(Activity a) {
        activity = a;
        
        onAttach(a);
    }
    
    /**
     * 
     * @param cat
     * @return true if reserving is succesfull
     */
    public boolean reserveSlot(Cat cat) {
        int slot = findFreeSlot();
        if(slot == -1) {
            return false;
        }
        
        users[slot] = cat;
        return true;
    }
    
    public boolean freeSlot(Cat cat) {
        for(int i = 0; i < users.length; i++) {
            if(users[i] == cat) {
                users[i] = null;
                return true;
            }
        }
        
        return false;
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
    
    public Vector2 getActivationPosition() {
        return new Vector2(activity.getX(), activity.getY());
    }
    
    protected abstract void onAttach(Activity a);
    public abstract String getInfo();
    public abstract void activate(Cat cat);
    public abstract boolean satisfiesNeed(int need, Cat cat);
    public abstract void receiveAction(String action);
}
