/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ai;

import com.dogshitempire.cos.cats.Cat;
import com.dogshitempire.cos.cats.CatBrain;

/**
 *
 * @author Super-Aapo
 */
public abstract class Goal {
    private CatBrain brain;
    public CatBrain getBrain() {
        return brain;
    }
    
    protected boolean aborted;
    protected boolean done;
    
    private Task currentTask;
    
    /**
     * 
     * @param brain the CatBrain that has this goal
     */
    public Goal(CatBrain brain) {
        this.brain = brain;
        
        aborted = false;
        done = false;
    }
    
    public void setTask(Task t) {
        currentTask = t;
        currentTask.onBegin();
    }
    public Task getCurrentTask() {
        return currentTask;
    }
    
    public boolean aborted() {
        return aborted;
    }
    public void abort() {
        aborted = true;
        if(currentTask != null) {
            currentTask.onAbort();
        }
    }
    public boolean done() {
        return done;
    }
    public void getDone() {
        done = true;
    }
    
    public void onBegin() {
        
    }
    public void onFinish() {
        
    }
    
    public void update(float deltaSeconds) {
        if(done || aborted || currentTask == null) {
            return;
        }
        
        currentTask.update(deltaSeconds);
        
        //Gdx.app.log("GOAL", currentTask.aborted() + " vs " + currentTask.done());
        
        if(currentTask.aborted()) {
            currentTask.onAbort();
            abort();
        }
        else if(currentTask.done()) {
            //Gdx.app.log("GOAL", "GOAL IS DONNNEEEEEE");
            currentTask.onDone();
            currentTask = currentTask.getNextTask();
            if(currentTask == null) {
                getDone();
            }
            else {
                currentTask.onBegin();
            }
        }
    }
}
