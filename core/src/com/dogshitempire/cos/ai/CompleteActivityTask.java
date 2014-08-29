/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dogshitempire.cos.ai;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dogshitempire.cos.items.activities.Activity;

/**
 *
 * @author Super-Aapo
 */
public class CompleteActivityTask extends Task {
    private Activity activity;
    private int needToSatisfy;
    
    private float activityTimer = 0f;
    private float activityInterval = 2f;
    
    public CompleteActivityTask(Goal goal, float speed) {
        this(goal, speed, -1);
    }
    
    /**
     * 
     * @param goal
     * @param speed how many seconds should pass between each time progress is added on the activity
     * @param need the need we want to satisfy. If -1 this Task accepts any activity
     */
    public CompleteActivityTask(Goal goal, float speed, int need) {
        super(goal);
        
        activityInterval = speed;
        needToSatisfy = need;
    }
    
    public void setActivity(Activity a) {
        activity = a;
    }
    
    @Override
    public void onBegin() {
        if(activity.satisfiesNeed(needToSatisfy, goal.getBrain().getCat())) {
            if(!activity.reserveSlot(goal.getBrain().getCat())) {
                abort();
            }
        }
        else {
            abort();
        }
    }
    
    @Override
    public void onAbort() {
        activity.freeSlot(goal.getBrain().getCat());
    }
    
    @Override
    public void update(float deltaSeconds) {
        activityTimer += deltaSeconds;
        if(activityTimer >= activityInterval) {
            activityTimer = 0;
            if(activity.addProgress(goal.getBrain().getCat())) {
                getDone();
            }
        }
    }
}
