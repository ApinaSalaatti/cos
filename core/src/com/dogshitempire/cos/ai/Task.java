/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ai;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Super-Aapo
 */
public abstract class Task {
    protected boolean aborted;
    protected boolean done;
    
    private Task nextTask;
    
    protected Actor target;
    protected Goal goal;
    
    /**
     * 
     * @param goal the goal this task is part of
     */
    public Task(Goal goal) {
        aborted = false;
        done = false;
        
        this.goal = goal;
    }
    
    public abstract void update(float deltaSeconds);
    
    /**
     * Used for passing information between Tasks.
     * @param a Actor that the previous Task found or otherwise deemed important
     */
    public void setTarget(Actor a) {
        target = a;
    }
    
    public void setNextTask(Task t) {
        nextTask = t;
    }
    public Task getNextTask() {
        return nextTask;
    }
    
    public void onBegin() {
        
    }
    
    public boolean aborted() {
        return aborted;
    }
    public void abort() {
        aborted = true;
    }
    public void onAbort() {
        
    }
    public boolean done() {
        return done;
    }
    public void getDone() {
        done = true;
    }
    public void onDone() {
        
    }
}
