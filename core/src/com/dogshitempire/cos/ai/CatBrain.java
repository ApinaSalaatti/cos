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

/**
 *
 * @author Super-Aapo
 */
public class CatBrain {
    private final Cat cat;
    private final PerceptionCenter perceptions;
    private final Pathfinder pathfinder;
    private final GoalChooser chooser;
    //private final FSMachine stateMachine;
    
    public CatBrain(Cat cat) {
        this.cat = cat;
        perceptions = new PerceptionCenter(this);
        //stateMachine = new FSMachine(FSMState.STATE_BASE_MACHINE, this);
        //stateMachine.addState(new WanderState());
        //stateMachine.addState(new SatisfyNeedsState(this));
        //stateMachine.setGlobalState(new GlobalCatState());
        
        pathfinder = new Pathfinder(this);
        chooser = new GoalChooser(this);
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
        //stateMachine.update(deltaSeconds);
        chooser.update(deltaSeconds);
    }
    
    public void sendMessage(AIMessage m) {
        
    }
}
