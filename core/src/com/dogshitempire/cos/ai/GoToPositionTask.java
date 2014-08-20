/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ai;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
    public void setTarget(Actor a) {
        super.setTarget(a);
        setPosition(a.getX(), a.getY());
    }
    
    @Override
    public void onDone() {
        getNextTask().setTarget(target);
    }
    
    @Override
    public void update(float deltaSeconds) {
        Cat cat = goal.getBrain().getCat();
        
        if(position.dst(cat.getX(), cat.getY()) <= 2) {
            getDone();
        }
        
        cat.getMover().setTarget(position.x, position.y);
    }
}
