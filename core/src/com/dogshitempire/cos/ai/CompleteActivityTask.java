/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ai;

import com.badlogic.gdx.Gdx;
import com.dogshitempire.cos.GameApplication;
import com.dogshitempire.cos.items.activities.Activity;
import com.dogshitempire.cos.processes.DelayProcess;

/**
 *
 * @author Super-Aapo
 */
public class CompleteActivityTask extends Task {
    private Activity activity;
    private int needToSatisfy;
    
    private DelayProcess delay;
    
    private float activityTimer = 0f;
    private float activationSpeed = 2f;
    
    public CompleteActivityTask(Goal goal, float speed) {
        this(goal, speed, -1);
    }
    
    /**
     * 
     * @param goal
     * @param speed how many seconds should pass before the activity is done
     * @param need the need we want to satisfy. If -1 this Task accepts any activity
     */
    public CompleteActivityTask(Goal goal, float speed, int need) {
        super(goal);
        
        activationSpeed = speed;
        needToSatisfy = need;
    }
    
    public void setActivity(Activity a) {
        activity = a;
    }
    
    @Override
    public void onBegin() {
        //Gdx.app.log("CAT", "BEGINNN");
        if(needToSatisfy == -1 || activity.satisfiesNeed(needToSatisfy, goal.getBrain().getCat())) {
            if(!activity.reserveSlot(goal.getBrain().getCat())) {
                abort();
            }
            else {
                delay = new DelayProcess(activationSpeed);
                GameApplication.getProcessManager().addProcess(delay);
            }
        }
        else {
            abort();
        }
    }
    
    @Override
    public void onAbort() {
        activity.freeSlot(goal.getBrain().getCat());
        delay.abort();
    }
    
    @Override
    public void update(float deltaSeconds) {
        //Gdx.app.log("CAT", "" + delay.getElapsed());
        if(delay.succeeded()) {
            //Gdx.app.log("CAT", "YAY!");
            activity.activate(goal.getBrain().getCat());
            getDone();
        }
    }
}
