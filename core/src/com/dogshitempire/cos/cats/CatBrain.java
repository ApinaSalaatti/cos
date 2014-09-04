/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.cats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.dogshitempire.cos.ai.Goal;
import com.dogshitempire.cos.ai.SatisfyNeedGoal;
import com.dogshitempire.cos.ai.WanderGoal;

/**
 *
 * @author Super-Aapo
 */
public class CatBrain {
    private final Cat cat;
    private float thinkTimer;
    private float thinkInterval;
    
    private Goal currentGoal;
    
    private SatisfyNeedGoal healthGoal;
    private SatisfyNeedGoal hungerGoal;
    private SatisfyNeedGoal happinessGoal;
    private SatisfyNeedGoal cleanlinessGoal;
    private WanderGoal wanderGoal;
    
    private Array<Integer> priorities;
    
    public CatBrain(Cat cat) {
        this.cat = cat;
        thinkTimer = 0f;
        thinkInterval = 1f;
        
        currentGoal = new WanderGoal(this);
        
        healthGoal = new SatisfyNeedGoal(this, CatStats.NEED_HEALTH, 30f);
        hungerGoal = new SatisfyNeedGoal(this, CatStats.NEED_HUNGER, 30f);
        happinessGoal = new SatisfyNeedGoal(this, CatStats.NEED_HAPPINESS, 30f);
        cleanlinessGoal = new SatisfyNeedGoal(this, CatStats.NEED_CLEANLINESS, 30f);
        wanderGoal = new WanderGoal(this);
        
        priorities = new Array<Integer>();
    }
    
    public void act(float deltaSeconds) {
        if(currentGoal != null) {
            currentGoal.update(deltaSeconds);
            if(currentGoal.aborted() || currentGoal.done()) thinkTimer = thinkInterval;
        }
        
        thinkTimer += deltaSeconds;
        if(thinkTimer >= thinkInterval) {
            thinkTimer = 0f;
            think();
        }
    }
    
    private void think() {
        CatStats s = cat.getStats();
        
        currentGoal = healthGoal;
        if(!healthGoal.aborted() && !healthGoal.done()) return;
        healthGoal = new SatisfyNeedGoal(this, CatStats.NEED_HEALTH, 30f);
        
        currentGoal = hungerGoal;
        if(!hungerGoal.aborted() && !hungerGoal.done()) return;
        hungerGoal = new SatisfyNeedGoal(this, CatStats.NEED_HUNGER, 30f);
        
        currentGoal = happinessGoal;
        if(!happinessGoal.aborted() && !happinessGoal.done()) return;
        happinessGoal = new SatisfyNeedGoal(this, CatStats.NEED_HAPPINESS, 30f);
        
        currentGoal = cleanlinessGoal;
        if(!cleanlinessGoal.aborted() && !cleanlinessGoal.done()) return;
        cleanlinessGoal = new SatisfyNeedGoal(this, CatStats.NEED_CLEANLINESS, 30f);
        
        currentGoal = wanderGoal;
    }
    
    public Cat getCat() {
        return cat;
    }
}
