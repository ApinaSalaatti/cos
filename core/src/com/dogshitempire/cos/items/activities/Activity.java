/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.items.activities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.items.Item;

/**
 *
 * @author Super-Aapo
 */
public class Activity extends Item {
    public Activity(int id) {
        super(id);
    }
    
    public Vector2 getActivationPosition() {
        return modifier.getActivationPosition();
    }
    
    public boolean reserveSlot(Cat cat) {
        return modifier.reserveSlot(cat);
    }
    
    public void freeSlot(Cat cat) {
        modifier.freeSlot(cat);
    }
    
    public boolean hasFreeSlot() {
        return modifier.hasFreeSlot();
    }
    
    public boolean hasUsers() {
        return modifier.hasUsers();
    }

    public boolean isBeingUsedBy(Cat cat) {
        return modifier.isBeingUsedBy(cat);
    }
    
    public float getUseTime() {
        return modifier.getUseTime();
    }
    
    public void addPossibleAction(String action) {
        modifier.addPossibleAction(action);
    }
    public void removePossibleAction(String action) {
        modifier.removePossibleAction(action);
    }
    public void removeAllPossibleActions() {
        modifier.removeAllPossibleActions();
    }
    public Array<String> getPossibleActions() {
        return modifier.getPossibleActions();
    }
    
    private ActivityModifier modifier;
    public void setModifier(ActivityModifier am) {
        modifier = am;
        am.beingAttached(this);
    }
    
    public void activate(Cat cat) {
        modifier.activate(cat);
    }
    public boolean satisfiesNeed(int need, Cat cat) {
        return modifier.satisfiesNeed(need, cat);
    }
    
    public void receiveAction(String action) {
        modifier.receiveAction(action);
    }
    
    @Override
    public String getInfo() {
        return modifier.getInfo();
    }
}
