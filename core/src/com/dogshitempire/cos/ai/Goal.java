/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ai;

import com.badlogic.gdx.Gdx;
import com.dogshitempire.cos.cats.Cat;

/**
 *
 * @author Super-Aapo
 */
public abstract class Goal {
    private Cat cat;
    public Cat getCat() {
        return cat;
    }
    
    private boolean aborted;
    private boolean done;
    
    private Task currentTask;
    
    /**
     * 
     * @param cat the cat that has this goal
     */
    public Goal(Cat cat) {
        this.cat = cat;
        
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
    }
    public boolean done() {
        return done;
    }
    public void getDone() {
        done = true;
    }
    
    public void onFinish() {
        
    }
    
    public void update(float deltaSeconds) {
        if(done || aborted) {
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
