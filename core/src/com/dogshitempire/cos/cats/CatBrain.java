/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.cats;

import com.dogshitempire.cos.ai.GetFoodGoal;
import com.dogshitempire.cos.ai.Goal;
import com.dogshitempire.cos.ai.WanderGoal;

/**
 *
 * @author Super-Aapo
 */
public class CatBrain {
    private Cat cat;
    private float thinkTimer;
    private float thinkInterval;
    
    private Goal currentGoal;
    
    public CatBrain(Cat cat) {
        this.cat = cat;
        thinkTimer = 0f;
        thinkInterval = 5f;
        
        currentGoal = new WanderGoal(cat);
    }
    
    public void act(float deltaSeconds) {
        if(currentGoal != null) {
            currentGoal.update(deltaSeconds);
            if(currentGoal.aborted() || currentGoal.done()) {
                currentGoal.onFinish();
                currentGoal = null;
            }  
        }
        
        thinkTimer += deltaSeconds;
        if(thinkTimer >= thinkInterval) {
            thinkTimer = 0f;
            think();
        }
    }
    
    private void think() {
        CatStats s = cat.getStats();
        
        if(s.getHunger() < 45f && !(currentGoal instanceof GetFoodGoal)) {
            if(currentGoal != null) {
                currentGoal.abort();
            }
            currentGoal = new GetFoodGoal(cat);
        }
        else if(currentGoal == null) {
            currentGoal = new WanderGoal(cat);
        }
    }
}
