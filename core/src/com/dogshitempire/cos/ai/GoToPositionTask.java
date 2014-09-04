/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ai;

import com.badlogic.gdx.math.Vector2;
import com.dogshitempire.cos.cats.Cat;

/**
 *
 * @author Super-Aapo
 */
public class GoToPositionTask extends Task {
    private Vector2 position;
    
    public GoToPositionTask(Goal goal) {
        super(goal);   
        position = new Vector2();
    }
    
    public GoToPositionTask(Goal goal, Vector2 pos) {
        this(goal);
        
        position.x = pos.x;
        position.y = pos.y;
    }
    
    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }
    
    @Override
    public void update(float deltaSeconds) {
        Cat cat = goal.getBrain().getCat();
        cat.getMover().setTarget(position.x, position.y);
    }
    
    @Override
    public boolean done() {
        Cat cat = goal.getBrain().getCat();
        return position.dst(cat.getX(), cat.getY()) <= 15;
    }
}
