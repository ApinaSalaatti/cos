/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ai;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.dogshitempire.cos.ai.goals.GoalChooser;
import com.dogshitempire.cos.ai.messaging.AIMessage;
import com.dogshitempire.cos.ai.pathfinding.Pathfinder;
import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.cats.CatStats;

/**
 *
 * @author Super-Aapo
 */
public class CatBrain {
    private final Cat cat;
    private final PerceptionCenter perceptions;
    private final Pathfinder pathfinder;
    private final GoalChooser chooser;
    
    // Weights for the different needs, i.e. what need this cat wants to satisfy most
    private float cleanlinessWeight;
    private float happinessWeight;
    private float healthWeight;
    private float hungerWeight;
    
    public CatBrain(Cat cat) {
        this.cat = cat;
        perceptions = new PerceptionCenter(this);
        pathfinder = new Pathfinder(this);
        chooser = new GoalChooser(this);
        
        cleanlinessWeight = 0.8f;
        happinessWeight = 1f;
        healthWeight = 1.5f;
        hungerWeight = 1.2f;
    }
    
    public float getWeight(int need) {
        switch(need) {
            case CatStats.NEED_CLEANLINESS:
                return cleanlinessWeight;
            case CatStats.NEED_HAPPINESS:
                return happinessWeight;
            case CatStats.NEED_HEALTH:
                return healthWeight;
            case CatStats.NEED_HUNGER:
                return hungerWeight;
            default:
                return 0;
        }
    }
    
    public Cat getCat() {
        return cat;
    }
    public Pathfinder getPathfinder() {
        return pathfinder;
    }
    public PerceptionCenter getPerceptions() {
        return perceptions;
    }
    
    public void debugDraw(Batch batch) {
        //stateMachine.debugDraw(batch);
        chooser.debugDraw(batch);
    }
    
    public void act(float deltaSeconds) {
        perceptions.update(deltaSeconds);
        chooser.update(deltaSeconds);
    }
    
    public void sendMessage(AIMessage m) {
        
    }
}
