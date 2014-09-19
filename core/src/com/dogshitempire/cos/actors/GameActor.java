package com.dogshitempire.cos.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * This class represents agents in the game world (that is, not stuff like ui widgets)
 * 
 * @author Merioksan Mikko
 */
public class GameActor extends Actor {
    private final int id;
    public int getID() {
        return id;
    }
    
    public GameActor(int id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object other) {
        if(other instanceof GameActor) {
            GameActor a = (GameActor)other;
            if(a.getID() == getID()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return getID();
    }
}
